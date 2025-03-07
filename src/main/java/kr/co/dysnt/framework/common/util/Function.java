package kr.co.dysnt.framework.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.*;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Function {

	public static boolean isNull(String str) {
		return (str == null || str.equals(""));
	}

	public static JSONObject convertJSON(String str) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static JSONArray convertJSONArray(String str) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) parser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public static String getRemoteAddr(HttpServletRequest request) throws Exception {
		String ip = null;
		ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
		/*
		 * String clientIp = request.getHeader("Proxy-Client-IP");
		 * if (clientIp == null) {
		 * clientIp = request.getHeader("WL-Proxy-Client-IP");
		 * if (clientIp == null) {
		 * clientIp = request.getHeader("X-Fowarded-For");
		 * if (clientIp == null) {
		 * clientIp = request.getRemoteAddr();
		 * }
		 * }
		 * }
		 * return clientIp;
		 */
	}

	public static String getRemoteAddr() throws Exception {
		HttpServletRequest request = getRequest();
		return getRemoteAddr(request);
	}

	public static String getDate(String pattern) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		String now = f.format(cal.getTime());

		return now;
	}

	public static String getDateSum(String pattern, int day) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		cal.add(Calendar.DAY_OF_WEEK, day);
		String now = f.format(cal.getTime());

		return now;
	}

	public static boolean patternFind(String str, String regexp) {
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	public static boolean isNumber(String str) {
		String regex = "\\D";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return !matcher.find();
	}

	public static String isMatch(String str, String pat, int groupNum) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(str);
		String result = "";
		if (matcher.find())
			result = matcher.group(groupNum);
		return result;
	}

	public static String readableFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

	public static File fileUpload(String path, MultipartFile file) {
		String randName = UUID.randomUUID().toString();
		String ext = getFileExt(file);
		File dir = new File(path);
		File resultFile = null;
		String uploadedName = dir.getAbsolutePath() + File.separator + randName;
		while (true) {
			if (!new File(uploadedName).exists())
				break;
			randName = UUID.randomUUID().toString();
			uploadedName = dir.getAbsolutePath() + File.separator + randName;
		}
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			resultFile = new File(uploadedName);
			file.transferTo(resultFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return resultFile;
	}

	public static File fileUpload(String path, MultipartFile file, boolean extFlag) {
		String randName = UUID.randomUUID().toString();
		String ext = getFileExt(file);
		File dir = new File(path);
		File resultFile = null;
		String uploadedName = dir.getAbsolutePath() + File.separator + randName + (extFlag ? "." + ext : "");
		while (true) {
			if (!new File(uploadedName).exists())
				break;
			randName = UUID.randomUUID().toString();
			uploadedName = dir.getAbsolutePath() + File.separator + randName + (extFlag ? "." + ext : "");
		}
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			resultFile = new File(uploadedName);
			file.transferTo(resultFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return resultFile;
	}

	public static File fileUpload(String path, MultipartFile file, String name) {
		String ext = getFileExt(file);
		File dir = new File(path);
		File resultFile = null;
		int count = 0;
		String uploadedName = dir.getAbsolutePath() + File.separator + name + ("_0" + (++count));
		while (true) {
			if (!new File(uploadedName).exists())
				break;

			uploadedName = dir.getAbsolutePath() + File.separator + name + ("_" + (count < 10 ? "0" : "") + (++count));
		}
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			resultFile = new File(uploadedName);
			file.transferTo(resultFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return resultFile;
	}

	public static String getUploadFileName(String path, String ext) {
		String randName = UUID.randomUUID().toString();
		File dir = new File(path);
		String uploadedName = dir.getAbsolutePath() + File.separator + randName + "." + ext;
		while (true) {
			if (!new File(uploadedName).exists())
				break;
			randName = UUID.randomUUID().toString();
			uploadedName = dir.getAbsolutePath() + File.separator + randName + "." + ext;
		}
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return uploadedName;
	}

	// 파일을 복사하는 메소드
	public static void fileCopy(String inFileName, String outFileName) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel channelIs = null;
		FileChannel channelOs = null;
		try {
			fis = new FileInputStream(inFileName);
			fos = new FileOutputStream(outFileName);

			channelIs = fis.getChannel();
			channelOs = fos.getChannel();

			long size = channelIs.size();
			channelIs.transferTo(0, size, channelOs);

			channelOs.close();
			channelIs.close();
			fis.close();
			fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (channelOs != null)
					channelOs.close();
				if (channelIs != null)
					channelIs.close();
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getFileExt(MultipartFile file) {
		String ext = "";
		if (file != null) {
			String fileName = file.getOriginalFilename();
			ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return ext;
	}

	public static String getFileExt(String fileName) {
		String ext = "";
		if (!isNull(fileName)) {
			ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return ext;
	}

	public static String removeExt(String fileName) {
		if (!isNull(fileName)) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		return fileName;
	}

	public static String stackTraceToString(Throwable e) {
		String result = "";
		if (e != null)
			try {
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				e.printStackTrace(new PrintStream(bout));
				bout.flush();
				result = new String(bout.toByteArray());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return result;
	}

	public static String parseNull(Object obj, String def) {
		if (obj == null)
			return def;
		if (obj instanceof String)
			return (String) obj;
		if (obj.getClass().getName().contains("JSONNull"))
			return def;

		String str = null;

		str = String.valueOf(obj);

		return str;
	}

	public static String parseNull(Object obj) {
		return parseNull(obj, "");
	}

	public static boolean createThumbnail(String orgPath, String savePath, double minWith, double minHeight) {
		boolean result = false;

		try {
			File destFile = new File(savePath);
			File orgFile = new File(orgPath);

			Image srcImg = ImageIO.read(orgFile);

			int scaledW = 0;
			int scaledH = 0;

			double thumbScale = Math.min((minWith / srcImg.getWidth(null)), (minHeight / srcImg.getHeight(null)));

			if (srcImg.getWidth(null) < minWith) {
				scaledW = srcImg.getWidth(null);
			} else {
				// scaledW = saveWith;
				scaledW = (int) (srcImg.getWidth(null) * thumbScale);
			}
			if (srcImg.getHeight(null) < minHeight) {
				scaledH = srcImg.getHeight(null);
			} else {
				// scaledH = saveHeight;
				scaledH = (int) (srcImg.getHeight(null) * thumbScale);
			}
			Image imgTarget = srcImg.getScaledInstance(scaledW, scaledH, Image.SCALE_SMOOTH);

			int pixels[] = new int[scaledW * scaledH];

			PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, scaledW, scaledH, pixels, 0, scaledW);
			pg.grabPixels();

			BufferedImage destImg = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB);
			destImg.setRGB(0, 0, scaledW, scaledH, pixels, 0, scaledW);

			result = ImageIO.write(destImg, getFileExt(destFile.getName()), destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String temporaryPassword(int size) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9"
				.split(",");

		for (int i = 0; i < size; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}

		return buffer.toString();

	}

	public static String getRandomNumStr(int iLen) {
		Random random = new Random();
		String rtnValue = "";

		for (int i = 0; i < iLen; i++) {
			rtnValue += parseNull(random.nextInt(9));
		}

		return rtnValue;
	}

	public static String getFileGrpCd() {
		String grpCd = getDate("yyyyMMddHHmmss") + getRandomNumStr(6);
		return grpCd;
	}

	public static Object convertRequestDataToObj(HttpServletRequest request) throws Exception {
		InputStream ins = null;
		InputStreamReader reader = null;
		BufferedReader br = null;
		Object rtnObj = null;

		try {
			ins = request.getInputStream();
			reader = new InputStreamReader(ins, "UTF-8");
			br = new BufferedReader(reader);
			String str = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				str += line;
			}

			br.close();
			reader.close();
			ins.close();

			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValueAsString(str);
			Map paramMap = mapper.readValue(str, Map.class);
			rtnObj = paramMap.get("data");
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (br != null)
				br.close();
			if (reader != null)
				reader.close();
			if (ins != null)
				ins.close();
		}
		return rtnObj;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Map<String, Object>> convertRequestDataToArray(HttpServletRequest request)
			throws Exception {
		ArrayList<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
		Object rtnObj = convertRequestDataToObj(request);
		if (rtnObj != null && parseNull(rtnObj.getClass().getName()).indexOf("List") >= 0)
			rtnList = (ArrayList<Map<String, Object>>) rtnObj;
		return rtnList;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> convertRequestDataToMap(HttpServletRequest request) throws Exception {
		HashMap<String, Object> rtnMap = new HashMap<String, Object>();
		Object rtnObj = convertRequestDataToObj(request);

		if (rtnObj != null && parseNull(rtnObj.getClass().getName()).indexOf("Map") >= 0)
			rtnMap = (HashMap<String, Object>) rtnObj;
		return rtnMap;
	}

	public static HttpServletRequest getRequest() throws Exception {
		ServletRequestAttributes servletContainer = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = servletContainer.getRequest();

		return request;
	}

	public static HttpSession getSession() throws Exception {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	public static Object getSessionAttribute(String key) throws Exception {
		HttpSession session = getSession();

		return session.getAttribute(key);
	}

	public static String sendMailProc(JSONObject parameter) throws Exception {
		String strRtnMsg = "";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "mail.kangwon.ac.kr");
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("portal", "rkddnjseo");
			}
		});
		Transport transport = session.getTransport("smtp");

		strRtnMsg = sendMailProc(parameter, session, transport);
		return strRtnMsg;
	}

	public static String sendMailProc(JSONObject parameter, Session session, Transport transport) throws Exception {
		String strRtnMsg = "";
		try {
			boolean bConvert = false;

			// 메일 제목
			String strTitle = parameter.get("TITLE") == null ? "" : parameter.get("TITLE").toString();

			// 관리자 메일주소
			String strMailFrom = "portal@kangwon.ac.kr";

			String strMailToOri = parameter.get("EMAIL").toString();
			String[] strMailTo = strMailToOri.split(",");
			// 메일 내용
			String strMessage = parameter.get("MESSAGE") == null ? "" : parameter.get("MESSAGE").toString();
			if (strMessage == null || "".equals(strMessage)) {
				strMessage = parameter.get("TEMPLATE").toString();
				bConvert = true;
			}

			if (bConvert) {
				for (Integer i = 0; i < 11; i++) {
					String strParamName = "PARAM" + i.toString();
					strMessage = StringUtils.replace(strMessage, "$" + i.toString() + "$",
							(parameter.get(strParamName) == null ? "" : parameter.get(strParamName).toString()));
				}
			}

			MimeMessage message = new MimeMessage(session);
			// 발신자
			message.setFrom(new InternetAddress(strMailFrom));
			// 수신자
			InternetAddress[] address = new InternetAddress[strMailTo.length];
			for (Integer j = 0; j < strMailTo.length; j++) {
				address[j] = new InternetAddress(strMailTo[j]);
			}
			message.setRecipients(Message.RecipientType.BCC, address);

			// 제목
			message.setSubject(strTitle, "UTF-8");
			// 발신시간
			message.setSentDate(new Date());
			// 내용
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(StringUtils.replace(strMessage, "", ""), "text/html;charset=UTF-8");
			mp.addBodyPart(mbp);
			message.setContent(mp);

			message.saveChanges();
			transport.send(message);
			strRtnMsg = "메일이 전송되었습니다.";
		} catch (Exception e) {
			strRtnMsg = "메일전송에 실패하였습니다.(" + e.getMessage() + ")";
		}

		return strRtnMsg;
	}

	public static String getReplaceInvalidChar(String str, String replaceStr) {
		String resultStr = "";
		try {
			str = parseNull(str);
			replaceStr = parseNull(replaceStr);
			resultStr = str.replaceAll("([\u0000-\u0009]|[\u000B-\u001F])", replaceStr);
		} catch (Exception e) {
			resultStr = "";
		}

		return resultStr;
	}

	public static String getReplaceInvalidChar(String str) {

		return getReplaceInvalidChar(str, "");
	}

	public static boolean isResultMapHasDBException(HashMap<String, Object> paramMap) throws Exception {
		boolean rtnBool = false;
		try {
			if (((ArrayList<HashMap<String, Object>>) paramMap.get("result")).get(0).containsKey("result")) {
				rtnBool = true;
			}
		} catch (Exception e) {
			rtnBool = true;
			log.error("DB 반환 맵 예외 발생 예외");
			log.error(e.getMessage());
		}
		return rtnBool;
	}

	public static JSONObject resultAsJSONObject(HashMap<String, Object> paramMap) throws Exception {
		JSONObject rtnJSONObject = new JSONObject();
		try {
			rtnJSONObject = new JSONObject(((ArrayList<Map<String, Object>>) paramMap.get("result")).get(0));
		} catch (Exception e) {
			log.error("DB 반환 맵 JSONObject 변환 예외 발생 예외");
			log.error(e.getMessage());
		}
		return rtnJSONObject;
	}

	public static String resultAsJSONObjectByLinkedHashMap(HashMap<String, Object> paramMap) throws Exception {

		String rtnString = "";

		try {
			LinkedHashMap<String, Object> cupMap = new LinkedHashMap<String, Object>();
			LowerCamelHashMap lcMap = new LowerCamelHashMap();
			if (((ArrayList) paramMap.get("result")).size() > 0) {
				lcMap = (LowerCamelHashMap) ((ArrayList) paramMap.get("result")).get(0);
				SortedSet<String> keys = new TreeSet<>(lcMap.keySet());
				for (String key : keys) {
					// rtnJSONObject.put(key, lcMap.get(key));
					cupMap.put(key, lcMap.get(key));
				}
			}

			ObjectMapper objectMapper = new ObjectMapper();
			rtnString = objectMapper.writeValueAsString(cupMap);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return rtnString;
	}

	public static List<Map<String, Object>> resultAsArrayListOfMap(HashMap<String, Object> paramMap) throws Exception {
		List<Map<String, Object>> rtnArrMap = new ArrayList<Map<String, Object>>();
		try {
			rtnArrMap = ((ArrayList<Map<String, Object>>) paramMap.get("result"));
		} catch (Exception e) {
			log.error("DB 반환 Map, ArrayList<Map> 변환 예외");
			log.error(e.getMessage());
		}
		return rtnArrMap;
	}

	/**
	 * JSONObject to Map 변환
	 *
	 * @param jsonObj
	 * @return
	 */
	public static Map<String, Object> convertJSONObjectToMap(JSONObject jsonObj) {
		Map<String, Object> map = null;

		try {
			map = new ObjectMapper().readValue(jsonObj.toString(), Map.class);
		} catch (JsonParseException e) {
			log.error("JSONObject to Map 변환 예외");
			log.error(e.getMessage());
		} catch (JsonMappingException e) {
			log.error("JSONObject to Map 변환 예외");
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error("JSONObject to Map 변환 예외");
			log.error(e.getMessage());
		}
		return map;
	}

	/**
	 * Map to JSONObject 변환
	 *
	 * @param map
	 * @return
	 */
	public static JSONObject convertMapToJSONObject(Map<String, Object> map) {
		JSONObject json = new JSONObject();
		String key = "";
		Object value = null;
		try {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				key = entry.getKey();
				value = entry.getValue();
				json.put(key, value);
			}
		} catch (Exception e) {
			log.error("Map to JSONObject 변환 예외");
			log.error(e.getMessage());
		}
		return json;
	}

	/**
	 * List<Map> 을 JSON 형태로 변환
	 *
	 */
	public static JSONArray convertListToJson(List<HashMap<String, Object>> map) {

		JSONArray jsonArray = new JSONArray();

		try {
			for (Map<String, Object> thisMap : map) {
				jsonArray.add(convertMapToJSONObject(thisMap));
			}
		} catch (Exception e) {
			log.error("List<Map> to JSONArray 변환 예외");
			log.error(e.getMessage());
		}

		return jsonArray;

	}

	/**
	 * JSONObject to Map 변환
	 *
	 * @param jsonObj
	 * @return
	 */
	public static Map<String, Object> convertJSONstringToMap(String json) {
		Map<String, Object> map = null;

		try {
			map = new ObjectMapper().readValue(json, Map.class);
		} catch (JsonParseException e) {
			log.error("JSONObject to Map 변환 예외");
			log.error(e.getMessage());
		} catch (JsonMappingException e) {
			log.error("JSONObject to Map 변환 예외");
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error("JSONObject to Map 변환 예외");
			log.error(e.getMessage());
		}
		return map;
	}

	public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

	public static double getDistanceBetweenTwoPoints(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2Rad(lat1)) * Math.sin(deg2Rad(lat2)) + Math.cos(deg2Rad(lat1))
				* Math.cos(deg2Rad(lat2)) * Math.cos(deg2Rad(theta));
		dist = Math.acos(dist);
		dist = rad2Deg(dist);
		dist = dist * 60 * 1.1515 * 1.609344 * 1000.0f;
		// dist = dist * 60 * 1.1515 * 1.609344;
		return dist;
	}

	private static double deg2Rad(double angle) {
		return angle * Math.PI / 180f;
	}

	public static double rad2Deg(double angle) {
		return angle * 180.0f / Math.PI;
	}

	public static String getPresentDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	public static boolean isBigDecimalNullOrEmpty(BigDecimal value) {
		boolean rtnBool = true;

		try {
			if (value == null || value.compareTo(BigDecimal.ZERO) == 0) {
				rtnBool = true;
			} else {
				rtnBool = false;
			}
		} catch (Exception e) {
			rtnBool = true;
		}

		return rtnBool;
	}
}

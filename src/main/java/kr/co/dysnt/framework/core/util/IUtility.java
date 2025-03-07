package kr.co.dysnt.framework.core.util;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.DigestUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.utl.cas.service.EgovSessionCookieUtil;

public class IUtility {
    private static final Logger LOG = LogManager.getLogger(IUtility.class.getName());

    public static String gs_ErrMsg = "처리 중 오류가 발생하였습니다.";

    /**
     * Object를 String으로 변환한다. String이 아니거나 null 일 경우 기본값을 반환한다.
     *
     * @Author :
     * @Create :
     * @Description : Object를 String으로 변환한다. String이 아니거나 null 일 경우 기본값을 반환한다.
     * @Title : Object를 String으로 변환
     * @stereotype UtilMethod
     */
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

    /**
     * Object를 String으로 변환한다. String이 아니거나 null 일 경우 ""을 반환한다.
     *
     * @Author :
     * @Create :
     * @Description : Object를 String으로 변환한다. String이 아니거나 null 일 경우 ""을 반환한다.
     * @Title : Object를 String으로 변환
     * @stereotype UtilMethod
     */
    public static String parseNull(Object obj) {
        return parseNull(obj, "");
    }

    /**
     * Object를 int로 변환한다. int가 아니거나 null 일 경우 기본값을 반환한다.
     *
     * @Author :
     * @Create :
     * @Description : Object를 String으로 변환한다. int가 아니거나 null 일 경우 기본값을 반환한다.
     * @Title : Object를 int로 변환
     * @stereotype UtilMethod
     */
    public static int parseInt(Object obj, int def) {
        if (obj == null)
            return def;
        if (obj instanceof Integer)
            return (Integer) obj;

        int number = 0;
        try {
            number = Integer.parseInt(String.valueOf(obj));
        } catch (NumberFormatException e) {
            number = def;
            LOG.error(e.getMessage());
        }

        return number;
    }

    /**
     * Object를 int로 변환한다. int가 아니거나 null 일 경우 0을 반환한다.
     *
     * @Author :
     * @Create :
     * @Description : Object를 String으로 변환한다. int가 아니거나 null 일 경우 0을 반환한다.
     * @Title : Object를 int로 변환
     * @stereotype UtilMethod
     */
    public static int parseInt(Object obj) {
        return parseInt(obj, 0);
    }

    /**
     * String이 비어있는지 체크 한다. 비어있을 경우 true를 반환한다.
     *
     * @Author :
     * @Create :
     * @Description : String이 비어있는지 체크 한다. 비어있을 경우 true를 반환한다.
     * @Title : String이 비어있는지 체크
     * @stereotype UtilMethod
     */
    public static boolean isEmpty(String str) {
        str = parseNull(str);
        return str.trim().length() < 1 ? true : false;
    }

    public static boolean isNumeric(Object i) {
        return StringUtils.isNumeric(IUtility.parseNull(i));
    }

    /**
     * 서버 업로드 위치를 리턴한다.
     *
     * @return (String) 업로드 기본주소
     */
    public static String getUploadPath() {
        return "/data/file"; // 실서버 용 업로드 주소
    }

    /**
     * 템프 폴더 위치를 리턴한다.
     *
     * @return (String) 업로드 기본주소
     */
    public static String getTempPath() {
        return "/data/temp"; // 실서버 용 업로드 주소
    }

    public static Object htmlTagFilter(Object value) {

        if (value == null) {
            return null;
        }

        StringBuffer strBuff = new StringBuffer();

        for (int i = 0; i < value.toString().length(); i++) {
            char c = value.toString().charAt(i);
            switch (c) {
                case '<':
                    strBuff.append("&lt;");
                    break;
                case '>':
                    strBuff.append("&gt;");
                    break;
                // case '&':
                // strBuff.append("&amp;");
                // break;
                // case '"':
                // strBuff.append("&quot;");
                // break;
                // case '\'':
                // strBuff.append("&apos;");
                // break;
                default:
                    strBuff.append(c);
                    break;
            }
        }
        return (Object) (strBuff.toString());
    }

    public static String getStrFromFile(File file) throws Exception {
        String rtnStr = FileUtils.readFileToString(file, "UTF-8");

        return rtnStr;
    }

    public static void setStrToFile(String filePath, String source) throws Exception {
        FileUtils.write(new File(filePath), source, "UTF-8");
    }

    public static WebApplicationContext getWebContext() throws Exception {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
        return applicationContext;
    }

    public static Map getPageInfo(Object vo, List list) throws Exception {
        // 페이지 정보 추가
        Map pageMap = new HashMap();

        Method methodPageIndex = vo.getClass().getMethod("getPageIndex");
        Method methodPageSize = vo.getClass().getMethod("getPageSize");

        // int iPageIndex = IUtility.parseInt(methodPageIndex.invoke(vo));
        // int iPageSize = IUtility.parseInt(methodPageSize.invoke(vo));
        int iPageIndex = IUtility.parseInt(methodPageIndex.invoke(vo));
        int iPageSize = IUtility.parseInt(methodPageSize.invoke(vo));
        int iTotPage = 1;
        int iTotCnt = 0;
        int iPageGrp = 1;

        if (iPageIndex > 0) {
            iPageGrp = (int) Math.ceil((double) (iPageIndex + 1) / (double) 10);
        }

        int firstIndex = (iPageIndex * iPageSize) + 1; // ROWNUM 은 1부터 시작
        int lastIndex = (iPageIndex * iPageSize) + iPageSize; // 해당 시작 ROWNUM 부터 한페이지에 표현할 레코드 수를 더함
        if (list.size() > 0) {
            Map listMap = (Map) list.get(0);
            iTotCnt = IUtility.parseInt(listMap.get("TOT_CNT"));
            iTotPage = (int) Math.ceil((double) iTotCnt / (double) iPageSize);
        }

        if (iTotCnt < iPageSize) {
            iPageIndex = 0;
            iPageGrp = 1;
        }

        pageMap.put("pageIndex", iPageIndex);
        pageMap.put("pageSize", iPageSize);
        pageMap.put("pageGrp", iPageGrp);
        pageMap.put("totCnt", iTotCnt);
        pageMap.put("totPage", iTotPage);
        pageMap.put("firstIndex", firstIndex);
        pageMap.put("lastIndex", lastIndex);
        return pageMap;
    }

    public static Map getPageInfo(Object vo, Object totCnt) throws Exception {
        // 페이지 정보 추가
        Map pageMap = new HashMap();

        Method methodPageIndex = vo.getClass().getMethod("getPageIndex");
        Method methodPageSize = vo.getClass().getMethod("getPageSize");

        int iPageIndex = IUtility.parseInt(methodPageIndex.invoke(vo));
        int iPageSize = IUtility.parseInt(methodPageSize.invoke(vo));
        int iTotPage = 1;
        int iTotCnt = 0;
        int iPageGrp = 1;

        if (iPageIndex > 0) {
            iPageGrp = (int) Math.ceil((double) (iPageIndex + 1) / (double) 10);
        }

        int firstIndex = (iPageIndex * iPageSize) + 1; // ROWNUM 은 1부터 시작
        int lastIndex = (iPageIndex * iPageSize) + iPageSize; // 해당 시작 ROWNUM 부터 한페이지에 표현할 레코드 수를 더함

        pageMap.put("pageIndex", iPageIndex);
        pageMap.put("pageSize", iPageSize);
        pageMap.put("pageGrp", iPageGrp);
        iTotCnt = parseInt(totCnt);
        iTotPage = (int) Math.ceil((double) iTotCnt / (double) iPageSize);
        pageMap.put("totCnt", iTotCnt);
        pageMap.put("totPage", iTotPage);
        pageMap.put("firstIndex", firstIndex);
        pageMap.put("lastIndex", lastIndex);

        return pageMap;
    }

    public static Map getPageInfo(Map map, List list) throws Exception {
        // 페이지 정보 추가
        Map pageMap = new HashMap();

        int iPageIndex = IUtility.parseInt(map.get("pageIndex"));
        int iPageSize = IUtility.parseInt(map.get("pageSize"));
        int iTotPage = 1;
        int iTotCnt = 0;
        int iPageGrp = 1;

        if (iPageIndex > 0) {
            iPageGrp = (int) Math.ceil((double) (iPageIndex + 1) / (double) 10);
        }

        int firstIndex = (iPageIndex * iPageSize) + 1; // ROWNUM 은 1부터 시작
        int lastIndex = (iPageIndex * iPageSize) + iPageSize; // 해당 시작 ROWNUM 부터 한페이지에 표현할 레코드 수를 더함
        if (list.size() > 0) {
            Map listMap = (Map) list.get(0);
            iTotCnt = IUtility.parseInt(listMap.get("TOT_CNT"));
            iTotPage = (int) Math.ceil((double) iTotCnt / (double) iPageSize);
        }

        if (iTotCnt < iPageSize) {
            iPageIndex = 0;
            iPageGrp = 1;
        }

        pageMap.put("pageIndex", iPageIndex);
        pageMap.put("pageSize", iPageSize);
        pageMap.put("pageGrp", iPageGrp);
        pageMap.put("totCnt", iTotCnt);
        pageMap.put("totPage", iTotPage);
        pageMap.put("firstIndex", firstIndex);
        pageMap.put("lastIndex", lastIndex);
        return pageMap;
    }

    public static List getPageInfoArr(Object vo, List list) throws Exception {
        ArrayList rtnList = new ArrayList();

        Map pageMap = getPageInfo(vo, list);

        return getPageInfoArr(pageMap);
    }

    public static List getPageInfoArr(Map pageMap) throws Exception {
        ArrayList rtnList = new ArrayList();

        int totPage = parseInt(pageMap.get("totPage"));
        int totCnt = parseInt(pageMap.get("TOT_CNT"));
        int pageSize = parseInt(pageMap.get("pageSize"));
        int pageIndex = parseInt(pageMap.get("pageIndex"));
        int pageGrp = parseInt(pageMap.get("pageGrp"));

        int startIdx = (pageGrp - 1) * 10;
        int endIdx = (pageGrp * 10) - 1;

        if (endIdx >= (totPage - 1))
            endIdx = totPage - 1;

        for (int i = startIdx; i <= endIdx; i++) {
            Map map = new HashMap();
            if (i == pageIndex) {
                map.put("idx", i);
                map.put("no", i + 1);
                map.put("on", "Y");
            } else {
                map.put("idx", i);
                map.put("no", i + 1);
                map.put("on", "N");
            }
            rtnList.add(map);
        }

        return rtnList;
    }

    public static File uploadFile(InputStream file, String uploadedName) throws Exception {
        File rtnFile = new File(uploadedName);

        FileUtils.copyInputStreamToFile(file, rtnFile);
        return rtnFile;
    }

    public static File uploadFile(MultipartFile file, String path, String fileName) throws Exception {
        return uploadFile(file.getInputStream(), path + File.separator + fileName);
    }

    public static File uploadFile(MultipartFile file, String path) throws Exception {
        String name = parseNull(file.getOriginalFilename());
        String ext = getFileExt(file);
        name = name.substring(0, name.lastIndexOf("."));
        int count = 0;
        String uploadedName = path + File.separator + name + ("_0" + (++count)) + "." + ext;
        File dir = new File(path);
        while (true) {
            if (!new File(uploadedName).exists())
                break;

            uploadedName = dir.getAbsolutePath() + File.separator + name + ("_" + (count < 10 ? "0" : "") + (++count))
                    + "." + ext;
        }

        return uploadFile(file.getInputStream(), uploadedName);
    }

    public static String getFileExt(MultipartFile file) throws Exception {
        String ext = "";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            ext = getFileExt(fileName);
        }

        return ext;
    }

    public static String getFileExt(String fileName) throws Exception {
        fileName = parseNull(fileName);
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

        return ext;
    }

    public static void deleteFile(String filePath, String fileNm) throws Exception {
        FileUtils.forceDelete(new File(filePath + File.separator + fileNm));
    }

    /**
     * 랜덤코드 생성
     *
     * @param int length
     * @return String
     * @throws Exception
     */
    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer();

        /** 시큐어코딩 랜덤 함수 사용 **/
        SecureRandom random = null;
        try {
            /** 강화된 난수 알고리즘 적용 **/
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage());
        }

        String chars[] = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
        };

        for (int i = 0; i < length; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }

        return buffer.toString();
    }

    /**
     * HttpURLConnection Data를 Stream으로 반환
     *
     * @param strUrl
     * @param paramMap : url 호출 시 파라메터
     * @return
     * @throws Exception
     */
    public static InputStream getUrlData(String strUrl, Map paramMap) throws Exception {
        HttpURLConnection conn = null;

        InputStream stream = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            String method = parseNull(paramMap.get("method"));
            if ("".equals(method))
                method = "GET";

            String contentType = parseNull(paramMap.get("contentType"));
            if ("".equals(contentType))
                contentType = "application/x-www-form-urlencoded;";

            String charset = parseNull(paramMap.get("charset"));
            if ("".equals(contentType))
                charset = "utf-8";

            conn.setRequestProperty("accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            conn.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
            conn.setRequestProperty("Accept-Charset", "utf-8,windows-949,euc-kr;q=0.7,*;q=0.3");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");

            int timeOut = parseInt(paramMap.get("timeOut"));
            if (timeOut == 0)
                timeOut = 10000;
            conn.setConnectTimeout(timeOut);

            conn.setReadTimeout(10000);

            conn.setRequestProperty("contentType",
                    (contentType.indexOf(";") > 0 ? contentType : contentType + ";") + " charset=" + charset);
            conn.setRequestMethod(method);

            conn.setFollowRedirects(true);
            int status = conn.getResponseCode();
            boolean reDir = true;
            int tryCnt = 0;
            while (tryCnt <= 4) {
                if (status == HttpURLConnection.HTTP_OK) {

                    // stream = conn.getInputStream();
                    if ("gzip".equals(IUtility.parseNull(conn.getContentEncoding()).toLowerCase())) {
                        stream = new GZIPInputStream(conn.getInputStream());
                    } else {
                        stream = conn.getInputStream();
                    }
                    return stream;
                } else if (status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_MOVED_TEMP) {
                    URL base = conn.getURL();
                    String loc = conn.getHeaderField("Location");
                    URL target = null;
                    if (loc != null) {
                        target = new URL(base, loc);
                    }
                    conn.disconnect();

                    conn = (HttpURLConnection) target.openConnection();
                    conn.setRequestProperty("contentType",
                            (contentType.indexOf(";") > 0 ? contentType : contentType + ";") + " charset=" + charset);
                    conn.setRequestMethod(method);
                    conn.setConnectTimeout(timeOut);
                    conn.setReadTimeout(10000);
                    conn.setRequestProperty("accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                    conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
                    conn.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
                    conn.setRequestProperty("Accept-Charset", "utf-8,windows-949,euc-kr;q=0.7,*;q=0.3");
                    conn.setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
                    conn.setFollowRedirects(true);
                    status = conn.getResponseCode();
                    tryCnt++;
                }
            }
            conn.disconnect();

        } catch (Exception e) {
            LOG.error(e.getMessage());
            if (stream != null)
                stream.close();
            if (conn != null)
                conn = null;
            throw e;
        }
        return stream;
    }

    /**
     * HttpURLConnection Data를 String으로 반환
     *
     * @param strUrl
     * @param paramMap : url 호출 시 파라메터
     * @return
     * @throws Exception
     */
    public static String getUrlStringData(String strUrl, Map paramMap) throws Exception {
        String rtnStr = "";

        HttpURLConnection conn = null;
        InputStream stream = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            String method = parseNull(paramMap.get("method"));
            if ("".equals(method))
                method = "GET";

            String contentType = parseNull(paramMap.get("contentType"));
            if ("".equals(contentType))
                contentType = "application/x-www-form-urlencoded;";

            String charset = parseNull(paramMap.get("charset"));
            if ("".equals(contentType))
                charset = "utf-8";

            conn.setRequestProperty("accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            conn.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
            conn.setRequestProperty("Accept-Charset", "utf-8,windows-949,euc-kr;q=0.7,*;q=0.3");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");

            conn.setDoInput(true);
            conn.setDoOutput(true);

            int timeOut = parseInt(paramMap.get("timeOut"));
            if (timeOut == 0)
                timeOut = 10000;
            conn.setConnectTimeout(timeOut);

            conn.setReadTimeout(10000);

            conn.setRequestProperty("contentType",
                    (contentType.indexOf(";") > 0 ? contentType : contentType + ";") + " charset=" + charset);
            conn.setRequestMethod(method);

            conn.setFollowRedirects(true);
            int status = conn.getResponseCode();
            boolean reDir = true;
            int tryCnt = 0;

            while (tryCnt <= 4) {
                if (status == HttpURLConnection.HTTP_OK) {

                    // stream = conn.getInputStream();
                    if ("gzip".equals(IUtility.parseNull(conn.getContentEncoding()).toLowerCase())) {
                        stream = new GZIPInputStream(conn.getInputStream());
                    } else {
                        stream = conn.getInputStream();
                    }
                    break;
                } else if (status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_MOVED_TEMP) {
                    URL base = conn.getURL();
                    String loc = conn.getHeaderField("Location");
                    URL target = null;
                    if (loc != null) {
                        target = new URL(base, loc);
                    }
                    conn.disconnect();

                    conn = (HttpURLConnection) target.openConnection();
                    conn.setRequestProperty("contentType",
                            (contentType.indexOf(";") > 0 ? contentType : contentType + ";") + " charset=" + charset);
                    conn.setRequestMethod(method);
                    conn.setConnectTimeout(timeOut);
                    conn.setReadTimeout(10000);
                    conn.setRequestProperty("accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                    conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
                    conn.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
                    conn.setRequestProperty("Accept-Charset", "utf-8,windows-949,euc-kr;q=0.7,*;q=0.3");
                    conn.setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
                    conn.setFollowRedirects(true);
                    status = conn.getResponseCode();
                    tryCnt++;
                }
            }
            rtnStr = getStringDataFromStream(stream, paramMap);
            conn.disconnect();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            if (stream != null)
                stream.close();
            if (conn != null)
                conn.disconnect();
            throw e;
        } finally {
            if (stream != null)
                stream.close();
            if (conn != null)
                conn.disconnect();
        }
        return rtnStr;
    }

    /**
     * InputStream Data를 String으로 반환
     *
     * @param stream
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String getStringDataFromStream(InputStream stream, Map paramMap) throws Exception {
        String rtnStr = "";
        String encoding = parseNull(paramMap.get("encoding"));
        if ("".equals(encoding))
            encoding = "UTF-8";
        rtnStr = StreamUtils.copyToString(stream, Charset.forName(encoding));
        if (stream != null) {
            stream.close();
        }
        return rtnStr;
    }

    /**
     * url data를 InputStream으로 반환
     *
     * @param strUrl
     * @return
     * @throws Exception
     */
    public static InputStream getUrlDataGetType(String strUrl) throws Exception {
        InputStream stream = getUrlData(strUrl, new HashMap());
        return stream;
    }

    /**
     * url data를 String으로 반환
     *
     * @param strUrl
     * @return
     * @throws Exception
     */
    public static String getStringUrlDataGetType(String strUrl) throws Exception {
        String str = parseNull(getUrlStringData(strUrl, new HashMap()));

        return str;
    }

    /**
     * html code String 변환 함수
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String getReplaceDecode(String str) throws Exception {
        String returnValue = parseNull(str);

        returnValue = returnValue.replaceAll("&quot;", "\\\"");
        returnValue = returnValue.replaceAll("&#35;", "#");
        returnValue = returnValue.replaceAll("&#40;", "(");
        returnValue = returnValue.replaceAll("&#41;", ")");
        returnValue = returnValue.replaceAll("''", "'");
        returnValue = returnValue.replaceAll("&apos;", "'");
        returnValue = returnValue.replaceAll("&#47;", "/");

        return returnValue;
    }

    /**
     * HttpServletRequest 반환함수
     *
     * @return
     * @throws Exception
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletContainer = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = servletContainer.getRequest();

        return request;
    }

    /**
     * 사용자 IP 주소 반환함수
     *
     * @param request
     * @return
     * @throws Exception
     */
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
    }

    /**
     * 사용자 IP 주소 반환함수
     *
     * @return
     * @throws Exception
     */
    public static String getRemoteAddr() throws Exception {
        HttpServletRequest request = getRequest();
        return getRemoteAddr(request);
    }

    /**
     * Session value 값 반환함수
     *
     * @param key : (String) 반환할 Session key
     * @return
     * @throws Exception
     */
    public static Object getSessionValue(String key) throws Exception {
        HttpServletRequest request = getRequest();
        return getSessionValue(request, key);
    }

    /**
     * Session value 값 반환함수
     *
     * @param key : (String) 반환할 Session key
     * @return
     * @throws Exception
     */
    public static Object getSessionValue(HttpServletRequest request, String key) throws Exception {
        return EgovSessionCookieUtil.getSessionAttribute(request, key);
    }

    /**
     * Session ID 반환함수
     *
     * @return
     * @throws Exception
     */
    public static String getSessionId() {
        HttpServletRequest request = getRequest();
        return getSessionId(request);
    }

    /**
     * Session ID 반환함수
     *
     * @return
     * @throws Exception
     */
    public static String getSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return getSessionId(session);
    }

    /**
     * Session ID 반환함수
     *
     * @return
     * @throws Exception
     */
    public static String getSessionId(HttpSession session) {
        return session.getId();
    }

    public static String getReplaceInvalidChar(String str, String replaceStr) throws Exception {
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

    public static String getReplaceInvalidChar(String str) throws Exception {

        return getReplaceInvalidChar(str, "");
    }

    /**
     * 공통 메시지 반환함수(locale 포함)
     *
     * @param messageSource : (MessageSource) messageSource bean
     * @param key           : (String)messageSource 구분키
     * @param objs          : (String) 메시지 변환 파라메터
     * @return
     * @throws Exception
     */
    public static String getCommonMessage(HttpServletRequest request, MessageSource messageSource, String key,
            Object... objs) throws Exception {
        Object[] args = null;
        if (objs != null) {
            int len = objs.length;
            args = new Object[len];
            for (int i = 0; i < len; i++) {
                args[i] = objs[i];
            }
        }

        String language = parseNull(getSessionValue(request, "SESSION_LOCALE"));
        if ("".equals(language)) {
            language = "ko";
        }
        Locale locale = new Locale(language);
        return messageSource.getMessage(key, args, locale);
    }

    /**
     * 공통 메시지 반환함수(locale 포함)
     *
     * @param messageSource : (MessageSource) messageSource bean
     * @param key           : (String)messageSource 구분키
     * @param objs          : (String) 메시지 변환 파라메터
     * @return
     * @throws Exception
     */
    public static String getCommonMessage(MessageSource messageSource, String key, Object... objs) throws Exception {
        HttpServletRequest request = getRequest();
        return getCommonMessage(request, messageSource, key, objs);
    }

    /**
     * AES256 key spec
     *
     * @param key : (String) 암호화 key
     * @return
     * @throws Exception
     */
    public static Key getAES256KeySpec(String key) throws Exception {
        String iv = key.substring(0, 16);

        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        return keySpec;
    }

    /**
     * AES256 암호화 함수
     *
     * @param source : (String) 암호화할 문자열
     * @param key    : (String) 암호화 key
     * @return
     * @throws Exception
     */
    public static String getEncStr(String source, String key) throws Exception {
        String strRtn = "";
        String iv = key.substring(0, 16);

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Key keySpec = getAES256KeySpec(key);
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] encrypted = c.doFinal(source.getBytes("UTF-8"));
        strRtn = new String(Base64.encodeBase64(encrypted));

        return strRtn;
    }

    /**
     * AES256 암호화 함수
     *
     * @param source : (String) 암호화할 문자열
     * @return
     * @throws Exception
     */
    public static String getEncStr(String source) throws Exception {
        return getEncStr(source, getSessionId());
    }

    /**
     * AES256 암호화 함수
     *
     * @param source : (Object) 암호화할 문자열
     * @return
     * @throws Exception
     */
    public static String getEncStr(Object source) throws Exception {
        return getEncStr(parseNull(source), getSessionId());
    }

    /**
     * AES256 복호화 함수
     *
     * @param encStr : (String) 암호화된 문자열
     * @param key    : (String) 암호화 key
     * @return
     * @throws Exception
     */
    public static String getDecStr(String encStr, String key) {
        String strRtn = "";
        String iv = key.substring(0, 16);
        try {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Key keySpec = getAES256KeySpec(key);
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));

            byte[] byteStr = Base64.decodeBase64(encStr.getBytes());

            strRtn = new String(c.doFinal(byteStr), "UTF-8");
        } catch (Exception e) {
            strRtn = "";
        }
        return strRtn;
    }

    /**
     * AES256 복호화 함수
     *
     * @param encStr : (String) 암호화된 문자열
     * @return
     * @throws Exception
     */
    public static String getDecStr(String encStr) {
        return getDecStr(encStr, getSessionId());
    }

    /**
     * AES256 복호화 함수
     *
     * @param encStr : (Object) 암호화된 문자열
     * @return
     * @throws Exception
     */
    public static String getDecStr(Object encStr) {
        return getDecStr(parseNull(encStr), getSessionId());
    }

    /**
     * DB 암호화(DBMS_CRYPTO.HASH(STR, 2) java function
     *
     * @param str : 패스워드
     * @return
     */
    public static String encryptPwdString(String str) {
        String encStr = "";
        try {
            byte[] bytesInput = str.getBytes("UTF-8");
            encStr = DigestUtils.md5DigestAsHex(bytesInput);
        } catch (Exception e) {
            encStr = "";
        }
        return encStr.toUpperCase();
    }

    /**
     * 그룹웨어에서 사용하는 비밀번호 암호롸
     *
     * @param input 비밀번호
     * @return 암화화에 필요한 byte;
     */
    public static byte[] getSHA256(String input) {
        String res = "";
        byte[] sha256Byte = null;
        try {
            java.security.MessageDigest sha256 = java.security.MessageDigest.getInstance("SHA-256");

            sha256.update(input.getBytes());
            sha256Byte = sha256.digest();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            return sha256Byte;
        }
    }
    //
    // public static String getGroupPwdHash(String password){
    // return new sun.misc.BASE64Encoder().encode(getSHA256(password));
    // }

    /**
     * 로그인한 사용자 정보를 리턴한다.
     *
     * @return (Object) 사용자정보
     * @throws Exception
     */
    public static Object getLoginInfo() throws Exception {
        Object principal = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            principal = "anonymousUser";
        } else {
            principal = authentication.getPrincipal();
        }
        return principal;
    }

    /**
     * Hibernate 사용 시 validation 체크(domain에 정의된 annotation을 통해 체크)
     *
     * @param validator (Validator) Validator 객체
     * @param domain    (Object) domain 객체
     * @param rtnMap    (Map<String, Object>) 결과 값을 받을 Map 객체
     * @return (boolean) validation 통과여부
     */
    public static boolean checkValidateHibernate(Validator validator, Object domain, Map<String, Object> rtnMap) {
        boolean rtnValue = true;

        Set<ConstraintViolation<Object>> violations = validator.validate(domain);
        Iterator<ConstraintViolation<Object>> iterator = violations.iterator();
        String oMsg = "";
        String oRslt = "";
        while (iterator.hasNext()) {
            ConstraintViolation<Object> violation = iterator.next();
            oMsg += violation.getMessage() + "\r\n";
        }

        if (!"".equals(oMsg)) {
            rtnMap.put("O_RSLT", "N");
            rtnMap.put("O_MSG", oMsg);
            rtnValue = false;
        }

        return rtnValue;
    }

    /**
     * 문자열의 byte 수를 리턴한다.(한글 3byte)
     *
     * @param txt (String) byte 수를 가져올 문자열
     * @return (int) byte 수
     * @throws Exception
     */
    public static int getStringByteLength(String txt) throws Exception {
        txt = IUtility.parseNull(txt);

        // 바이트 체크 (영문 1, 한글 2, 특문 1)
        int en = 0;
        int ko = 0;
        int etc = 0;

        char[] txtChar = txt.toCharArray();
        for (int j = 0; j < txtChar.length; j++) {
            if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
                en++;
            } else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
                ko++;
                ko++;
                ko++;
            } else {
                etc++;
            }
        }

        int txtByte = en + ko + etc;

        return txtByte;
    }

    /**
     * Access 권한 여부를 체크한다.
     *
     * @param authKey (String) 체크할 권한 String
     * @param data    (String) 체크할 객체
     * @return (boolean) 권한여부
     */
    public static boolean checkAccess(String authKey, Map<String, Object> data) {
        authKey = IUtility.parseNull(authKey).replaceAll(" ", "");
        if (data == null)
            data = new HashMap<String, Object>();

        boolean bReturn = true;
        String[] authKeys = authKey.split(",");

        for (int i = 0, x = authKeys.length; i < x; i++) {
            String key = "SESSION_" + getAccessKey(authKeys[i]);
            if (!key.contains("USER_AUTHOR_LEVEL") && !"Y".equals(data.get(key))) {
                bReturn = false;
                break;
            } else if (key.contains("USER_AUTHOR_LEVEL") && parseInt(authKeys[i]) > parseInt(data.get(key))) {
                bReturn = false;
                break;
            }
        }

        return bReturn;
    }

    public static String getAccessKey(String authKey) {
        String rtn = authKey;

        switch (authKey) {
            case "R":
                rtn = "INQ_YN";
                break;

            case "C":
                rtn = "NEW_YN";
                break;

            case "U":
                rtn = "SAVE_YN";
                break;

            case "D":
                rtn = "DEL_YN";
                break;

            case "P":
                rtn = "PRN_YN";
                break;

            case "E":
                rtn = "EXCEL_YN";
                break;

            default:
                if (isNumeric(authKey)) {
                    rtn = "USER_AUTHOR_LEVEL";
                }

                break;
        }

        return rtn;
    }

    /**
     * 파라메터 객체(Map)가 시스템 파라메터를 가지고 있는 지 여부를 체크한다.
     *
     * @param data (Map) 파라메터 객체
     * @return (boolean) 시스템 파라메터를 가지고 있는 지 여부
     */
    public static boolean containSystemAttr(Map<String, Object> data) {
        if (data.containsKey("SESSION_USER_ID"))
            return true;
        if (data.containsKey("SESSION_USER_IP"))
            return true;
        if (data.containsKey("EXEC_MENU_ID"))
            return true;
        if (data.containsKey("SESSION_INQ_YN"))
            return true;
        if (data.containsKey("SESSION_REG_YN"))
            return true;
        if (data.containsKey("SESSION_MOD_YN"))
            return true;
        if (data.containsKey("SESSION_DEL_YN"))
            return true;
        if (data.containsKey("SESSION_PRN_YN"))
            return true;
        if (data.containsKey("SESSION_PRI_YN"))
            return true;
        if (data.containsKey("SESSION_ADD1_YN"))
            return true;
        if (data.containsKey("SESSION_ADD2_YN"))
            return true;
        if (data.containsKey("SESSION_ADD3_YN"))
            return true;
        return false;
    }

    /**
     * 파라메터 객체가 시스템 파라메터를 가지고 있는 지 여부를 체크한다.
     *
     * @param data (Object) 파라메터 객체
     * @return (boolean) 시스템 파라메터를 가지고 있는 지 여부
     */
    public static boolean containSystemAttr(Object data) {
        if (data instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) data;
            return containSystemAttr(map);
        }
        return false;
    }

    /**
     * 파라메터 객체(Map)에서 시스템 파라메터를 제거한다
     *
     * @param data (Map) 파라메터 객체
     */
    public static void removeSystemAttrData(Map<String, Object> data) {
        data.remove("SESSION_USER_ID");
        data.remove("SESSION_USER_IP");
        data.remove("EXEC_MENU_ID");
        data.remove("SESSION_INQ_YN");
        data.remove("SESSION_REG_YN");
        data.remove("SESSION_MOD_YN");
        data.remove("SESSION_DEL_YN");
        data.remove("SESSION_PRN_YN");
        data.remove("SESSION_PRI_YN");
        data.remove("SESSION_ADD1_YN");
        data.remove("SESSION_ADD2_YN");
        data.remove("SESSION_ADD3_YN");
    }

    /**
     * 파라메터 객체(Map)에서 시스템 파라메터를 추가한다
     *
     * @param data   (Map) 파라메터 객체
     * @param sysMap (Map) 심스템 정보를 가진 객체
     * @return (Map<String, Object>)
     */
    public static Map<String, Object> copySystemAttrData(Map<String, Object> sysMap) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (sysMap == null)
            sysMap = new HashMap<String, Object>();

        data.put("SESSION_USER_ID", sysMap.get("SESSION_USER_ID"));
        data.put("SESSION_USER_IP", sysMap.get("SESSION_USER_IP"));
        data.put("SESSION_LOCALE", sysMap.get("SESSION_LOCALE"));
        data.put("EXEC_MENU_ID", sysMap.get("EXEC_MENU_ID"));
        data.put("SESSION_INQ_YN", sysMap.get("SESSION_INQ_YN"));
        data.put("SESSION_REG_YN", sysMap.get("SESSION_REG_YN"));
        data.put("SESSION_MOD_YN", sysMap.get("SESSION_MOD_YN"));
        data.put("SESSION_DEL_YN", sysMap.get("SESSION_DEL_YN"));
        data.put("SESSION_PRN_YN", sysMap.get("SESSION_PRN_YN"));
        data.put("SESSION_PRI_YN", sysMap.get("SESSION_PRI_YN"));
        data.put("SESSION_ADD1_YN", sysMap.get("SESSION_ADD1_YN"));
        data.put("SESSION_ADD2_YN", sysMap.get("SESSION_ADD2_YN"));
        data.put("SESSION_ADD3_YN", sysMap.get("SESSION_ADD3_YN"));

        return data;
    }

    /**
     * 파라메터 객체에서 시스템 파라메터를 제거한다
     *
     * @param data (Object) 파라메터 객체
     */
    public static void removeSystemAttrData(Object data) {
        if (data instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) data;
            removeSystemAttrData(map);
        }
    }

    /**
     * JSON String 여부를 검사한다.
     *
     * @param source
     * @return
     */
    public static boolean isJsonString(String source) {
        boolean rtn = true;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(source);
        } catch (Exception e) {
            rtn = false;
        }

        return rtn;
    }

    public static Map<String, Object> convertJsonStrToMap(String source) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(source, Map.class);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
            map = new HashMap<String, Object>();
        }

        return map;
    }

    /**
     * 시스템 구분 코드를 리턴한다.
     *
     * @return (String) 시스템 구분 코드
     */
    public static String getSysSeCode() {
        return "01";
    }

    public static List mapJsonToListMap(Map map) {
        JSONArray arrJson = (JSONArray) map.get("data");

        List<Map> list = new ArrayList<Map>();
        for (int i = 0; i < arrJson.size(); i++) {

            JSONObject obj = (JSONObject) arrJson.get(i);

            Map pMap = new HashMap();

            Iterator<String> keys = obj.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                pMap.put(key, obj.get(key));
            }

            list.add(pMap);
        }

        return list;
    }

    /**
     * @Title : Multipart Security 제외부분 권한체크
     * @Description : Multipart Security 제외부분 권한체크
     * @Author : 김홍집
     * @Create : 2020-07-24
     * @param headerAuthStr
     * @param auths
     * @return : boolean
     */
    public static boolean multipartCustomAnyMethod(String headerAuthStr, String... auths) {
        boolean rtnValue = false;

        if (headerAuthStr != null) {

            headerAuthStr = headerAuthStr.replace(" ", "+");

            String authStr = getDecStr(headerAuthStr);
            Map<String, Object> authMap = convertJsonStrToMap(authStr);

            for (String auth : auths) {
                if (checkAccess(auth, authMap)) {
                    rtnValue = true;
                    break;
                }
            }

        } else {
            System.out.println("multipartCustomAnyMethod headerAuthStr is null!!");
        }
        return rtnValue;
    }

    /**
     * 이미지파일의 크기를 조정하여 InputStream으로 리턴한다.
     *
     * @Author : jbhwang
     * @Create : 2020-07-30
     * @param item : (File) 이미지파일
     * @return
     * @throws Exception
     */
    public static InputStream convertSumNailFile(File item)
            throws Exception {
        InputStream inStream = null;

        BufferedImage inImage = ImageIO.read(new FileInputStream(item));

        Integer inWidth = inImage.getWidth();
        Integer inHeight = inImage.getHeight();

        Integer imgWidth = inWidth;
        Integer imgHeight = inHeight;
        if (inHeight > 272) {
            float fSize = (float) inWidth / inHeight;

            imgWidth = Math.round(fSize * 272);
            imgHeight = 272;
        }
        inStream = convertSumNailBufferedImage(inImage, imgWidth, imgHeight, imgWidth, imgHeight, 0, 0, imgWidth,
                imgHeight, 0);

        return inStream;
    }

    /**
     * 이미지파일의 크기를 조정하여 InputStream으로 리턴한다.
     *
     * @Author jbhwang
     * @Create 2020-07-30
     * @param item         : (File) 이미지파일
     * @param imgWidth
     * @param imgHeight
     * @param imgSumWidth
     * @param imgSumHeight
     * @param imgSumX
     * @param imgSumY
     * @param imgSumX2
     * @param imgSumY2
     * @param imgAngle
     * @return
     * @throws Exception
     */
    public static InputStream convertSumNailBufferedImage(BufferedImage item, int imgWidth, int imgHeight,
            int imgSumWidth, int imgSumHeight, int imgSumX, int imgSumY, int imgSumX2, int imgSumY2, int imgAngle)
            throws Exception {
        InputStream inStream = null;

        BufferedImage inImage = item;

        BufferedImage sumImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D inGraphic = sumImage.createGraphics();
        inGraphic.drawImage(inImage, 0, 0, imgWidth, imgHeight, null);

        int x = 0;
        int y = 0;
        int x2 = imgWidth;
        int y2 = imgHeight;
        int width = imgWidth;
        int height = imgHeight;

        if (imgSumHeight > 0 && imgSumWidth > 0) {
            x = imgSumX;
            y = imgSumY;
            x2 = imgSumX2;
            y2 = imgSumY2;
            width = imgSumWidth;
            height = imgSumHeight;
        }

        if (IUtility.parseInt(imgAngle) > 0) {
            sumImage = getRotateImage(sumImage, imgAngle);
            imgWidth = sumImage.getWidth();
            imgHeight = sumImage.getHeight();

            x = 0;
            y = 0;
            x2 = imgWidth;
            y2 = imgHeight;
            width = imgWidth;
            height = imgHeight;
            if (imgSumHeight > 0 && imgSumWidth > 0) {
                x = imgSumX;
                y = imgSumY;
                x2 = imgSumX2;
                y2 = imgSumY2;
                width = imgSumWidth;
                height = imgSumHeight;
            }
        }

        BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphic = outImage.createGraphics();

        graphic.drawImage(sumImage, 0, 0, width, height, x, y, x2, y2, null);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(outImage, "jpg", os);
        inStream = new ByteArrayInputStream(os.toByteArray());

        return inStream;
    }

    /**
     * 이미지 파일을 받아서 angle만큼 회전한 이미지를 리턴한다.
     *
     * @Author jbhwang
     * @Create 2020-07-30
     * @param image
     * @param angle
     * @return
     */
    static BufferedImage getRotateImage(BufferedImage image, double angle) {// angle : degree
        double _angle = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(_angle));
        double cos = Math.abs(Math.cos(_angle));
        double w = image.getWidth();
        double h = image.getHeight();
        int newW = (int) Math.floor(w * cos + h * sin);
        int newH = (int) Math.floor(w * sin + h * cos);
        Frame frame = new Frame();

        GraphicsConfiguration gc = frame.getGraphicsConfiguration();
        BufferedImage result = gc.createCompatibleImage(newW, newH, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();

        g.translate((newW - w) / 2, (newH - h) / 2);
        g.rotate(_angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();

        return result;
    }

    public static String getClientBrowser() throws Exception {
        HttpServletRequest request = getRequest();
        String browser = "";
        String userAgent = request.getHeader("User-Agent");

        if (userAgent == null) {
            userAgent = "";
        }

        if (userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0) {
            browser = "IE";
        } else if (userAgent.indexOf("Opera") > 0) {
            browser = "Opera";
        } else if (userAgent.indexOf("Firefox") > 0) {
            browser = "Firefox";
        } else if (userAgent.indexOf("Chrome") > 0) {
            browser = "Chrome";
        } else if (userAgent.indexOf("Safari") > 0) {
            browser = "Safari";
        }
        return browser;
    }

    public static String getUrl() throws Exception {
        HttpServletRequest request = getRequest();

        return request.getRequestURI();
    }

    public static String getClientOs() throws Exception {
        HttpServletRequest request = getRequest();
        String os = "";
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            userAgent = "";
        }
        if (userAgent.indexOf("Win16") > 0) {
            os = "Windows 3.11";
        } else if (userAgent.indexOf("Windows 95") > 0 || userAgent.indexOf("Win95") > 0
                || userAgent.indexOf("Windows_95") > 0) {
            os = "Windows 95";
        } else if (userAgent.indexOf("Win 9x 4.90") > 0 || userAgent.indexOf("Windows ME") > 0) {
            os = "Windows ME";
        } else if (userAgent.indexOf("Windows 98") > 0 || userAgent.indexOf("Win98") > 0) {
            os = "Windows 98";
        } else if (userAgent.indexOf("Windows CE") > 0) {
            os = "Windows CE";
        } else if (userAgent.indexOf("Windows NT 5.0") > 0 || userAgent.indexOf("Windows 2000") > 0) {
            os = "Windows 2000";
        } else if (userAgent.indexOf("Windows NT 5.1") > 0 || userAgent.indexOf("Windows XP") > 0) {
            os = "Windows XP";
        } else if (userAgent.indexOf("Windows NT 5.2") > 0) {
            os = "Windows Server 2003";
        } else if (userAgent.indexOf("Windows NT 6.0") > 0) {
            os = "Windows Vista";
        } else if (userAgent.indexOf("Windows 7") > 0 || userAgent.indexOf("Windows NT 6.1") > 0) {
            os = "Windows 7";
        } else if (userAgent.indexOf("Windows 8.1") > 0 || userAgent.indexOf("Windows NT 6.3") > 0) {
            os = "Windows 8.1";
        } else if (userAgent.indexOf("Windows 8") > 0 || userAgent.indexOf("Windows NT 6.2") > 0) {
            os = "Windows 8";
        } else if (userAgent.indexOf("Windows 10") > 0 || userAgent.indexOf("Windows NT 10.0") > 0) {
            os = "Windows 10";
        } else if (userAgent.indexOf("Windows NT 4.0") > 0 || userAgent.indexOf("WinNT4.0") > 0
                || userAgent.indexOf("WinNT") > 0 || userAgent.indexOf("Windows NT") > 0) {
            os = "Windows NT 4.0";
        } else if (userAgent.indexOf("Android") > 0) {
            os = "Android";
        } else if (userAgent.indexOf("OpenBSD") > 0) {
            os = "Open BSD";
        } else if (userAgent.indexOf("SunOS") > 0) {
            os = "Sun OS";
        } else if (userAgent.indexOf("Linux") > 0 || userAgent.indexOf("X11") > 0) {
            os = "Linux";
        } else if (userAgent.indexOf("iPhone") > 0 || userAgent.indexOf("iPad") > 0 || userAgent.indexOf("iPod") > 0) {
            os = "iOS";
        } else if (userAgent.indexOf("Mac OS X") > 0) {
            os = "Mac OS X";
        } else if (userAgent.indexOf("MacPPC") > 0 || userAgent.indexOf("MacIntel") > 0
                || userAgent.indexOf("Mac_PowerPC") > 0 || userAgent.indexOf("Macintosh") > 0) {
            os = "Mac OS";
        } else if (userAgent.indexOf("QNX") > 0) {
            os = "QNX";
        } else if (userAgent.indexOf("UNIX") > 0) {
            os = "UNIX";
        } else if (userAgent.indexOf("BeOS") > 0) {
            os = "BeOS";
        } else if (userAgent.indexOf("OS/2") > 0) {
            os = "OS/2";
        } else if (userAgent.indexOf("nuhk") > 0 || userAgent.indexOf("Googlebot") > 0
                || userAgent.indexOf("Yammybot") > 0 || userAgent.indexOf("Openbot") > 0
                || userAgent.indexOf("Slurp") > 0 || userAgent.indexOf("MSNBot") > 0
                || userAgent.indexOf("Ask Jeeves/Teoma") > 0 || userAgent.indexOf("ia_archiver") > 0) {
            os = "Search Bot";
        } else {
            os = "기타OS";
        }
        return os;
    }

    public static Map getClassToMap(Object obj) throws Exception {
        if (obj == null)
            return null;

        java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
        Map map = new HashMap();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            map.put(fields[i].getName(), fields[i].get(obj));
        }
        return map;
    }

    public static JSONObject mapToJsonObj(Map paramMap) throws Exception {
        JSONObject obj = new JSONObject();

        Iterator<String> iterator = paramMap.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = parseNull(paramMap.get(key));
            obj.put(key, value);
        }

        return obj;
    }

    public static String addDate(int y, int m, int d, String pattern) throws Exception {
        return LocalDate.now().plusDays(d).plusMonths(m).plusYears(y).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getDatetime() throws Exception {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getLocalServerIp() throws Exception {
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
            NetworkInterface intf = en.nextElement();
            for (Enumeration<InetAddress> enumlpAddr = intf.getInetAddresses(); enumlpAddr.hasMoreElements();) {
                InetAddress inetAddress = enumlpAddr.nextElement();
                if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                        && inetAddress.isSiteLocalAddress()) {
                    return inetAddress.getHostAddress().toString();
                }
            }
        }
        return "";
    }

    public static String encodeURLReplace(String filenm) {
        String result = null;
        try {
            result = (URLEncoder.encode(filenm, "UTF-8"))
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%20", " ")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            result = filenm;
        }
        return result;
    }
}

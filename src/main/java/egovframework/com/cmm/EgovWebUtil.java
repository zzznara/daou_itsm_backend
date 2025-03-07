package egovframework.com.cmm;

import java.util.regex.Pattern;

/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    	--------    ---------------------------
 *   2011.10.10  한성곤          최초 생성
 *
 * </pre>
 */
 
public class EgovWebUtil {
	
	/**
     * XSS 보안취약점 - Html 코드로 replace
     * @param  : Traget str
     * @return : replace str
     */ 
	public static String clearXSSMinimum(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}
		
		String returnValue = value;

		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
/*		returnValue = returnValue.replaceAll("\\\"", "&quot;");
		returnValue = returnValue.replaceAll("#", "&#35;");
		returnValue = returnValue.replaceAll("(", "&#40;");
		returnValue = returnValue.replaceAll(")", "&#41;");
		returnValue = returnValue.replaceAll("'", "''");*/
		return returnValue;
	}

	public static String clearXSSMaximum(String value) {
		String returnValue = value;
		returnValue = clearXSSMinimum(returnValue);

		returnValue = returnValue.replaceAll("%00", null);

		returnValue = returnValue.replaceAll("%", "&#37;");

		// \\. => .

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\
		returnValue = returnValue.replaceAll("\\./", ""); // ./
		returnValue = returnValue.replaceAll("%2F", "");

		return returnValue;
	}

	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\
		returnValue = returnValue.replaceAll("%", "");

		return returnValue;
	}

	/**
	 * 행안부 보안취약점 점검 조치 방안.
	 *
	 * @param value
	 * @return
	 */
	public static String filePathReplaceAll(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\", "");
		returnValue = returnValue.replaceAll("\\.\\.", ""); // ..
		returnValue = returnValue.replaceAll("&", "");

		return returnValue;
	}

	public static String filePathWhiteList(String value) {
		return value; // TODO
	}
	
	 public static boolean isIPAddress(String str) {
		Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
		
		return ipPattern.matcher(str).matches();
    }
	 
    /*
    public static void main(String[] args) {
	String test = null;
	
	test = "<script language='javascript' encoding=\"utf-8\">q&a</script>";
	log.debug("clearXSSMinimum() Test");
	log.debug(test);
	log.debug("=>");
	log.debug(clearXSSMinimum(test));
	log.debug();
	
	test = "/a/b/c../..\\";
	log.debug("clearXSSMaximum() Test");
	log.debug(test);
	log.debug(" =>");
	log.debug(clearXSSMaximum(test));
	log.debug();
	
	test = "/a/b/c/../../../..\\..\\";
	log.debug("filePathBlackList() Test");
	log.debug(test);
	log.debug("=>");
	log.debug(filePathBlackList(test));
	log.debug();
	
	test = "192.168.0.1";
	log.debug("isIPAddress() test");
	log.debug("IP : " + test + " => " + isIPAddress(test));
    }
    //*/
	 
	 
    /** 파일 확장자 차단 리스트 체크 **/
    public static Boolean blockExtChk(String fileExt) {
    	Boolean checkResult = true;
    	
    	String blockExt[] = {"jsp","jspx","exe","js","css","html","htm"};
    	
    	for( int i=0; i<blockExt.length; i++){
    		if( blockExt[i].equals(fileExt) ){
    			checkResult = false;
    			break;
    		}
    	}
    	
    	if(fileExt.length() == 0) {
    		checkResult = false;
    	}
    	
    	return checkResult;
    }

}
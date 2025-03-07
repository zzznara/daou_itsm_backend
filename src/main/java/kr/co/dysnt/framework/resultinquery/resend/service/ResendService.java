package kr.co.dysnt.framework.resultinquery.resend.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: ResendService.java
 * Description: 샘플 Service Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 *
 */
public interface ResendService {

	List<EgovMap> searchResendList(Map<String, Object> param) throws Exception;

	int saveResendList(List<Map<String, Object>> param) throws Exception;

}

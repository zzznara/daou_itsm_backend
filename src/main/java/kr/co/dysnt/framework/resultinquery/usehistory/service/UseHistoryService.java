package kr.co.dysnt.framework.resultinquery.usehistory.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: UserService.java
 * Description: 샘플 Service Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 *
 */
public interface UseHistoryService {

	List<EgovMap> searchUseHistoryList(Map<String, Object> param) throws Exception;

}

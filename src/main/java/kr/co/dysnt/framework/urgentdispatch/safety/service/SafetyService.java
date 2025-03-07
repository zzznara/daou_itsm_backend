package kr.co.dysnt.framework.urgentdispatch.safety.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: ScrapService.java
 * Description: 고철 Service Class
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 19.
 * @version 1.0
 * @see
 *
 */
public interface SafetyService {

	List<EgovMap> searchSafetyDispatchList(Map<String, Object> param) throws Exception;

	int saveSafetyDispatchList(List<Map<String, Object>> param) throws Exception;
}

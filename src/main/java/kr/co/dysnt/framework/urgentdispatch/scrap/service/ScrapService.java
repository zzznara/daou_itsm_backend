package kr.co.dysnt.framework.urgentdispatch.scrap.service;

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
public interface ScrapService {

	List<EgovMap> searchScrapDispatchList(Map<String, Object> param) throws Exception;

	int saveScrapDispatchList(List<Map<String, Object>> param) throws Exception;

	List<EgovMap> searchScrapERPGrade(Map<String, Object> param) throws Exception;
}

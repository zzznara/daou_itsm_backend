package kr.co.dysnt.framework.weightsetting.reduce.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: ReduceService.java
 * Description: 권한 Service Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 14.
 * @version 1.0
 * @see
 *
 */
public interface ReduceService {

	List<EgovMap> searchReduceList(Map<String, Object> param) throws Exception;

	int saveReduceList(List<Map<String, Object>> param) throws Exception;

}

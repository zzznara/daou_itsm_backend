package kr.co.dysnt.framework.weightsetting.dispatchOrder.service;

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
public interface DispatchOrderService {

	List<EgovMap> SearchCarList(Map<String, Object> param) throws Exception;

	List<EgovMap> SearchCarDispatchOrderList(Map<String, Object> param) throws Exception;

	int saveCarDispatchOrderList(List<Map<String, Object>> param) throws Exception;

	List<EgovMap> chkRecord(Map<String, Object> param) throws Exception;
}

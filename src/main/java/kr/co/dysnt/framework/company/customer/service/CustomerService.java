package kr.co.dysnt.framework.company.customer.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: CustomerService.java
 * Description: 샘플 Service Class
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 20.
 * @version 1.0
 * @see
 *
 */
public interface CustomerService {

	List<EgovMap> searchCustomerList(Map<String, Object> param) throws Exception;

	List<EgovMap> searchMidVendorList(Map<String, Object> param) throws Exception;
}

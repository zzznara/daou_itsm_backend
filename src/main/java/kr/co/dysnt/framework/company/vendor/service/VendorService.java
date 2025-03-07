package kr.co.dysnt.framework.company.vendor.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: VendorService.java
 * Description: 샘플 Service Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 04. 06.
 * @version 1.0
 * @see
 *
 */
public interface VendorService {

	List<EgovMap> searchVendorList(Map<String, Object> param) throws Exception;

	void deleteVendor(Map<String, Object> param) throws Exception;

	void saveVendor(List<Map<String, Object>> param) throws Exception;

	List<EgovMap> searchCompanyList(Map<String, Object> param) throws Exception;

	List<EgovMap> searchVendorInfo(Map<String, Object> param) throws Exception;

}

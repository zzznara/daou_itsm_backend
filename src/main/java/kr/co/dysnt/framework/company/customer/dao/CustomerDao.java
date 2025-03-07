
package kr.co.dysnt.framework.company.customer.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: CustomerDao.java
 * Description: 거래처 DAO Class
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 20.
 * @version 1.0
 * @see
 */
@Repository
public class CustomerDao extends CommonDao {

	private String sqlNameSpace = "company.customer.";

	public List<EgovMap> searchCustomerList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchCustomerList", param);
	}

	public List<EgovMap> searchMidVendorList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchMidVendorList", param);
	}
}

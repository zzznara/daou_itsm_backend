
package kr.co.dysnt.framework.company.vendor.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: VendorDao.java
 * Description: 샘플 DAO Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 04. 06.
 * @version 1.0
 * @see
 */
@Repository
public class VendorDao extends CommonDao {

	private String sqlNameSpace = "company.vendor.";

	public List<EgovMap> searchVendorList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchVendorList", param);
	}

	public void deleteVendor(Map<String, Object> param) throws Exception {
		selectOne(sqlNameSpace + "saveVendor", param);
	}

	public void saveVendor(Map<String, Object> param) throws Exception {
		selectOne(sqlNameSpace + "saveVendor", param);
	}

	public void createVendorUser(Map<String, Object> param) {
		selectOne(sqlNameSpace + "createVendorUser", param);
	}

	public List<EgovMap> searchCompanyList_V(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchCompanyList_V", param);
	}

	public List<EgovMap> searchCompanyList_R(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchCompanyList_R", param);
	}

	public List<EgovMap> searchCompanyList_FR(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchCompanyList_FR", param);
	}

	public List<EgovMap> searchCompanyList_L(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchCompanyList_L", param);
	}

	public List<EgovMap> searchVendorInfo(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchVendorInfo", param);
	}

}

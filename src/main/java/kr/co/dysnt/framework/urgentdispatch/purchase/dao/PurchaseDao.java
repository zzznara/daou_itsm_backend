package kr.co.dysnt.framework.urgentdispatch.purchase.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: ScrapDao.java
 * Description: 고철 DAO Class
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 19.
 * @version 1.0
 * @see
 */
@Repository
public class PurchaseDao extends CommonDao {

	private String sqlNameSpace = "urgentdispatch.purchase.";

	public List<EgovMap> searchPurchaseDispatchList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "searchPurchaseDispatchList", param);
	}

	public int insertPurchaseDispatch(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "insertPurchaseDispatch", param);
	}

	public int updatePurchaseDispatch(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "updatePurchaseDispatch", param);
	}

	public int deletePurchaseDispatch(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "deletePurchaseDispatch", param);
	}

	public List<EgovMap> selectDispatchOne(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "selectDispatchOne", param);
	}
}

package kr.co.dysnt.framework.common.auth.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: AuthDao.java
 * Description: 샘플 DAO Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 01. 26.
 * @version 1.0
 * @see
 */
@Repository
public class AuthDao extends CommonDao {

	private String sqlNameSpace = "common.auth.";

	public List<EgovMap> searchAuthList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchAuthList", param);
	}

	public int deleteAuthList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "saveAuthList", param);
		return delete(sqlNameSpace + "saveAuthList_D", param);
	}

	// public int saveAuthList(Map<String, Object> param) throws Exception {
	// return selectOne(sqlNameSpace + "saveAuthList", param);
	// }

	public int saveAuthList_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveAuthList_I", param);
	}

	public int saveAuthList_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveAuthList_U", param);
	}

	public int saveAuthList_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveAuthList_D", param);
	}

	public List<EgovMap> searchAuthMenuList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchAuthMenuList", param);
	}

	public int deleteAuthMenuList(Map<String, Object> param) throws Exception {
		return selectOne(sqlNameSpace + "saveAuthMenuList", param);
	}

	// public int saveAuthMenuList(Map<String, Object> param) throws Exception {
	// return selectOne(sqlNameSpace + "saveAuthMenuList", param);
	// }

	public int saveAuthMenuList_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveAuthMenuList_I", param);
	}

	public int saveAuthMenuList_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveAuthMenuList_U", param);
	}

	public int saveAuthMenuList_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveAuthMenuList_D", param);
	}

	public List<EgovMap> searchAuthGroupList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchAuthGroupList", param);
	}

	public int deleteAuthGroupList(Map<String, Object> param) throws Exception {
		// return selectOne(sqlNameSpace + "saveAuthGroupList", param);
		return delete(sqlNameSpace + "saveAuthGroupList_D", param);
	}

	// public int saveAuthGroupList(Map<String, Object> param) throws Exception {
	// return selectOne(sqlNameSpace + "saveAuthGroupList", param);
	// }

	public int saveAuthGroupList_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveAuthGroupList_I", param);
	}

	public int saveAuthGroupList_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveAuthGroupList_U", param);
	}

	public int saveAuthGroupList_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveAuthGroupList_D", param);
	}

	public int saveUserLog(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveUserLog", param);
	}

}

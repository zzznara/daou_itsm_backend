package kr.co.dysnt.framework.common.menu.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: MenuDao.java
 * Description: 샘플 DAO Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 01. 26.
 * @version 1.0
 * @see
 */
@Repository
public class MenuDao extends CommonDao {

	private String sqlNameSpace = "common.menu.";

	public List<EgovMap> searchMenuList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "searchMenuList", param);
	}

	public void deleteMenuList(Map<String, Object> param) throws Exception {
		selectOne(sqlNameSpace + "saveMenuList", param);
	}

	public void saveMenuList(Map<String, Object> param) throws Exception {
		selectOne(sqlNameSpace + "saveMenuList", param);
	}

	public int saveMenuList_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveMenuList_I", param);
	}

	public int saveMenuList_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveMenuList_U", param);
	}

	public int saveMenuList_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveMenuList_D", param);
	}

	public List<EgovMap> searchUserMenuList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchUserMenuList", param);
		return selectList(sqlNameSpace + "searchUserMenuList", param);
	}

	public List<EgovMap> searchMenuAuthInfo(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuAuthInfo", param);
		return selectList(sqlNameSpace + "searchMenuAuthInfo", param);
	}

}

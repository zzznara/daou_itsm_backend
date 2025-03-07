package kr.co.dysnt.framework.common.group.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: GroupDao.java
 * Description: 샘플 DAO Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 16.
 * @version 1.0
 * @see
 */
@Repository
public class GroupDao extends CommonDao {

	private String sqlNameSpace = "common.group.";

	public List<EgovMap> searchUserList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchUserList", param);
	}

	public List<EgovMap> searchGroupList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchGroupList", param);
	}

	// public int deleteGroupList(Map<String, Object> param) throws Exception {
	// return delete(sqlNameSpace + "saveGroupList", param);
	// }

	// public void saveGroupList(Map<String, Object> param) throws Exception {
	// selectOne(sqlNameSpace + "saveGroupList", param);
	// }

	public int saveGroupList_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveGroupList_I", param);
	}

	public int saveGroupList_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveGroupList_U", param);
	}

	public int saveGroupList_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveGroupList_D", param);
	}

	public List<EgovMap> searchGroupUserList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchGroupUserList", param);
	}

	// public void deleteGroupUserList(Map<String, Object> param) throws Exception {
	// selectOne(sqlNameSpace + "saveGroupUserList", param);
	// }
	//
	// public void saveGroupUserList(Map<String, Object> param) throws Exception {
	// selectOne(sqlNameSpace + "saveGroupUserList", param);
	// }

	public int saveGroupUserList_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveGroupUserList_I", param);
	}

	public int saveGroupUserList_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveGroupUserList_U", param);
	}

	public int saveGroupUserList_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveGroupUserList_D", param);
	}
}

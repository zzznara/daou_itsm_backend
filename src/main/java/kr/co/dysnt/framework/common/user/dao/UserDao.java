package kr.co.dysnt.framework.common.user.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: UserDao.java
 * Description: 샘플 DAO Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 */
@Repository
public class UserDao extends CommonDao {

	private String sqlNameSpace = "common.user.";

	public List<EgovMap> searchUserList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "searchUserList", param);
	}

	public int insertUser(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "insertUser", param);
	}

	public int updateUser(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "updateUser", param);
	}

	public int deleteUser(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "deleteUser", param);
	}

	public int changePwd(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "changePwd", param);
	}
}

package kr.co.dysnt.framework.core.security.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;
import kr.co.dysnt.framework.core.security.user.TmsUser;

@Repository
public class TmsUserDao extends CommonDao {
	private String sqlNameSpace = "core.security.auth.";

	/**
	 * 사용자정보 조회
	 *
	 * @param (String)userId
	 * @return (TmsUser)사용자정보
	 * @throws Exception
	 */
	public TmsUser getUserById(HashMap<String, Object> map) throws Exception {
		return selectOne(sqlNameSpace + "getUserById", map);
	}

}

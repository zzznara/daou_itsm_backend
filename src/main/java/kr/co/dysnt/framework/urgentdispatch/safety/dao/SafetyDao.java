package kr.co.dysnt.framework.urgentdispatch.safety.dao;

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
public class SafetyDao extends CommonDao {

	private String sqlNameSpace = "urgentdispatch.safety.";

	public List<EgovMap> searchSafetyDispatchList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "searchSafetyDispatchList", param);
	}

	public int insertSafetyDispatch(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "insertSafetyDispatch", param);
	}

	public int updateSafetyDispatch(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "updateSafetyDispatch", param);
	}

	public int deleteSafetyDispatch(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "deleteSafetyDispatch", param);
	}

	public List<EgovMap> selectDispatchOne(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "selectDispatchOne", param);
	}
}

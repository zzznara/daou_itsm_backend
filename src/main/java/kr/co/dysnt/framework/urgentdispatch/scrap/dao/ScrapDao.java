package kr.co.dysnt.framework.urgentdispatch.scrap.dao;

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
public class ScrapDao extends CommonDao {

	private String sqlNameSpace = "urgentdispatch.scrap.";

	public List<EgovMap> searchScrapDispatchList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "searchScrapDispatchList", param);
	}

	public int insertScrapDispatch(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "insertScrapDispatch", param);
	}

	public int updateScrapDispatch(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "updateScrapDispatch", param);
	}

	public int deleteScrapDispatch(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "deleteScrapDispatch", param);
	}

	public List<EgovMap> searchScrapERPGrade(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchScrapERPGrade", param);
	}

	public List<EgovMap> selectDispatchOne(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "selectDispatchOne", param);
	}

}

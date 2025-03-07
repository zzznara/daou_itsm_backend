package kr.co.dysnt.framework.resultinquery.result.dao;

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
public class ResultDao extends CommonDao {

	private String sqlNameSpace = "resultinquery.result.";

	public List<EgovMap> searchResultList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "searchResultList", param);
	}

}

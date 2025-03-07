package kr.co.dysnt.framework.weightsetting.reduce.dao;

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
public class ReduceDao extends CommonDao {

	private String sqlNameSpace = "weightsetting.reduce.";

	public List<EgovMap> searchReduceList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchReduceList", param);
	}

	public int saveReduceList_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveReduceList_I", param);
	}

	public int saveReduceList_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveReduceList_U", param);
	}

	public int saveReduceList_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveReduceList_D", param);
	}

}

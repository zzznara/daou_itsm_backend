package kr.co.dysnt.framework.weightsetting.inhousesetting.dao;

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
public class InhouseSettingDao extends CommonDao {

	private String sqlNameSpace = "weightsetting.inhousesetting.";

	public List<EgovMap> searchInhouseSettingList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchInhouseSettingList", param);
	}

	public int saveInhouseSettingList_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveInhouseSettingList_I", param);
	}

	public int saveInhouseSettingList_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveInhouseSettingList_U", param);
	}

	public int saveInhouseSettingList_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveInhouseSettingList_D", param);
	}

}

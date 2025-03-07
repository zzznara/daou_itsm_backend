package kr.co.dysnt.framework.weightsetting.inhouse.dao;

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
public class InHouseDao extends CommonDao {

	private String sqlNameSpace = "weightsetting.inhouse.";

	public List<EgovMap> searchInHouseList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchInHouseList", param);
	}

}

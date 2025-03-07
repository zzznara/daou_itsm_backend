package kr.co.dysnt.framework.resultinquery.usehistory.dao;

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
public class UseHistoryDao extends CommonDao {

	private String sqlNameSpace = "resultinquery.usehistory.";

	public List<EgovMap> searchUseHistoryList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchUseHistoryList", param);
	}

}

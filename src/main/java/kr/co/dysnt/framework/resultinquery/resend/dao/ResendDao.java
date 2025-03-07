package kr.co.dysnt.framework.resultinquery.resend.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: ResendDao.java
 * Description: 샘플 DAO Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 */
@Repository
public class ResendDao extends CommonDao {

	private String sqlNameSpace = "resultinquery.resend.";

	public List<EgovMap> searchResendList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "searchResendList", param);
	}

	public int insertResend(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "insertResend", param);
	}

	public int updateResend(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "updateResend", param);
	}

	public int deleteResend(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "deleteResend", param);
	}

}

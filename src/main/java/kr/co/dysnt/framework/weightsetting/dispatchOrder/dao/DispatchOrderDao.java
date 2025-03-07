package kr.co.dysnt.framework.weightsetting.dispatchOrder.dao;

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
public class DispatchOrderDao extends CommonDao {

	private String sqlNameSpace = "weightsetting.dispatchOrder.";

	public List<EgovMap> SearchCarList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "SearchCarList", param);
	}

	public List<EgovMap> SearchCarDispatchOrderList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "SearchCarDispatchOrderList", param);
	}

	public int updateCarDispatchOrderList(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "updateCarDispatchOrderList", param);
	}

	public List<EgovMap> chkRecord(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "chkRecord", param);
	}

}

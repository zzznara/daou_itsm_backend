package kr.co.dysnt.framework.urgentdispatch.shipmentAndEtc.dao;

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
public class ShipmentAndEtcDao extends CommonDao {

	private String sqlNameSpace = "urgentdispatch.shipmentAndEtc.";

	public List<EgovMap> searchShipmentAndEtcDispatchList(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "searchShipmentAndEtcDispatchList", param);
	}

	public int insertShipmentAndEtcDispatch(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "insertShipmentAndEtcDispatch", param);
	}

	public int updateShipmentAndEtcDispatch(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "updateShipmentAndEtcDispatch", param);
	}

	public int deleteShipmentAndEtcDispatch(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "deleteShipmentAndEtcDispatch", param);
	}

	public List<EgovMap> selectDispatchOne(Map<String, Object> param) throws Exception {
		// selectOne(sqlNameSpace + "searchMenuList", param);
		return selectList(sqlNameSpace + "selectDispatchOne", param);
	}

}

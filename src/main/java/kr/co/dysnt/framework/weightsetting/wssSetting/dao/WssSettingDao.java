package kr.co.dysnt.framework.weightsetting.wssSetting.dao;

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
public class WssSettingDao extends CommonDao {

	private String sqlNameSpace = "weightsetting.wssSetting.";

	public List<EgovMap> SearchCommonSettings(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "SearchCommonSettings", param);
	}

	public List<EgovMap> SearchWeighbridgeDirections(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "SearchWeighbridgeDirection", param);
	}

	public List<EgovMap> SearchWeightBrigdeAllowErrRangeSettings(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "SearchWeightBrigdeErrRangeSetting", param);
	}

	public List<EgovMap> SearchWeightBrigdeBilletAllowErrRangeSettings(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "SearchWeightBrigdeBilletAllowErrRangeSetting", param);
	}

	public int saveSingleSetting(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveSingleSetting", param);
	}

	public int saveCommonSettings(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveCommonSettings", param);
	}

	public int saveWeighbridgeDirections(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveWeighbridgeDirection", param);
	}

	public int saveWeightBrigdeAllowErrRangeSettings(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveWeightBrigdeAllowErrRangeSetting", param);
	}

	public int saveWeightBrigdeBilletAllowErrRangeSettings(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveWeightBrigdeBilletAllowErrRangeSetting", param);
	}

}

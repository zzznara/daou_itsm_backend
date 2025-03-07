package kr.co.dysnt.framework.weightsetting.wssSetting.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.weightsetting.wssSetting.dao.WssSettingDao;
import kr.co.dysnt.framework.weightsetting.wssSetting.service.WssSettingService;

/**
 * <pre>
 * Class Name: ScrapServiceImpl.java
 * Description: 긴급배차 고철 ServiceImpl
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 19
 * @version 1.0
 * @see
 */
@Service("WssSettingService")
public class WssSettingServiceImpl extends CommonServiceImpl implements WssSettingService {

	@Resource(name = "wssSettingDao")
	private WssSettingDao wssSettingDao;

	@Override
	public List<EgovMap> SearchCommonSettings(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<EgovMap> rtnList = wssSettingDao.SearchCommonSettings(param);
		return rtnList;
	}

	@Override
	public List<EgovMap> SearchWeighbridgeDirections(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<EgovMap> rtnList = wssSettingDao.SearchWeighbridgeDirections(param);
		return rtnList;
	}

	@Override
	public List<EgovMap> SearchWeightBrigdeAllowErrRangeSettings(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<EgovMap> rtnList = wssSettingDao.SearchWeightBrigdeAllowErrRangeSettings(param);
		return rtnList;
	}

	@Override
	public List<EgovMap> SearchWeightBrigdeBilletAllowErrRangeSettings(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<EgovMap> rtnList = wssSettingDao.SearchWeightBrigdeBilletAllowErrRangeSettings(param);
		return rtnList;
	}

	@Override
	public int saveSingleSetting(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		int cnt = wssSettingDao.saveSingleSetting(param);

		return cnt;
	}

	@Override
	public int saveCommonSettings(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		int cnt = wssSettingDao.saveCommonSettings(param);

		return cnt;
	}

	@Override
	public int saveWeighbridgeDirections(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub

		int cnt = 0;
		for (int i = 0; i < param.size(); i++) {
			HashMap<String, Object> map = (HashMap<String, Object>) param.get(i);
			setParamInfo(map);
			// cnt += wssSettingDao.saveWeighbridgeDirections(map);
			cnt += wssSettingDao.saveSingleSetting(map);
		}

		return cnt;
	}

	@Override
	public int saveWeightBrigdeAllowErrRangeSettings(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		int cnt = wssSettingDao.saveWeightBrigdeAllowErrRangeSettings(param);

		return cnt;
	}

	@Override
	public int saveWeightBrigdeBilletAllowErrRangeSettings(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		int cnt = wssSettingDao.saveWeightBrigdeBilletAllowErrRangeSettings(param);

		return cnt;
	}

}

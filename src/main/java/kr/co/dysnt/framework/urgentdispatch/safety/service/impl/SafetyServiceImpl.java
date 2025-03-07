package kr.co.dysnt.framework.urgentdispatch.safety.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.urgentdispatch.safety.dao.SafetyDao;
import kr.co.dysnt.framework.urgentdispatch.safety.service.SafetyService;

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
@Service("SafetyService")
public class SafetyServiceImpl extends CommonServiceImpl implements SafetyService {

	@Resource(name = "safetyDao")
	private SafetyDao safetyDao;

	@Override
	public List<EgovMap> searchSafetyDispatchList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		List<EgovMap> rtnList = safetyDao.searchSafetyDispatchList(param);

		return rtnList;
	}

	@Override
	public int saveSafetyDispatchList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);

			setParamInfo(map);
			if (map.get("inGb") != null && map.get("inGb").equals("1"))// ERP 사전배차 데이터는 제외한다
			{
				continue;
			}

			if (map.get("saveGb") != null && map.get("saveGb").equals("1"))// ERP 사전배차 데이터는 제외한다
			{
				continue;
			}

			if ("C".equals(map.get("status"))) {
				result = safetyDao.insertSafetyDispatch(map);
			} else if ("U".equals(map.get("status"))) {
				List<EgovMap> oldData = safetyDao.selectDispatchOne(map);
				if (oldData.size() == 1) {
					map.put("RENEW_DT", "N");
					map.put("RENEW_TURN", "N");
					if (!oldData.get(0).get("dt").equals(map.get("dt").toString().replace("-", ""))) {
						map.put("RENEW_DT", "Y");
					}

					if (!oldData.get(0).get("carCd").equals(map.get("carCd"))) {
						map.put("RENEW_TURN", "Y");
					}
				}

				result = safetyDao.updateSafetyDispatch(map);
			} else if ("D".equals(map.get("status"))) {
				result = safetyDao.deleteSafetyDispatch(map);
			}
			if (result < 0) {
				return result;
			}
		}
		return 1;
	}

}

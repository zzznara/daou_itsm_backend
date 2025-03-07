package kr.co.dysnt.framework.urgentdispatch.scrap.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.urgentdispatch.scrap.dao.ScrapDao;
import kr.co.dysnt.framework.urgentdispatch.scrap.service.ScrapService;

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
@Service("ScrapService")
public class ScrapServiceImpl extends CommonServiceImpl implements ScrapService {

	@Resource(name = "scrapDao")
	private ScrapDao scrapDao;

	@Override
	public List<EgovMap> searchScrapDispatchList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		List<EgovMap> rtnList = scrapDao.searchScrapDispatchList(param);

		return rtnList;
	}

	@Override
	public int saveScrapDispatchList(List<Map<String, Object>> param) throws Exception {
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
				result = scrapDao.insertScrapDispatch(map);
			} else if ("U".equals(map.get("status"))) {
				List<EgovMap> oldData = scrapDao.selectDispatchOne(map);
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

				result = scrapDao.updateScrapDispatch(map);
			} else if ("D".equals(map.get("status"))) {
				result = scrapDao.deleteScrapDispatch(map);
			}
			if (result < 0) {
				return result;
			}
		}
		return 1;
	}

	@Override
	public List<EgovMap> searchScrapERPGrade(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		List<EgovMap> rtnList = scrapDao.searchScrapERPGrade(param);

		return rtnList;
	}

}

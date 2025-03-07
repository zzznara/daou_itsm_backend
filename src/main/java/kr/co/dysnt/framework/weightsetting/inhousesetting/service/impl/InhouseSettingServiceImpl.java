package kr.co.dysnt.framework.weightsetting.inhousesetting.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.weightsetting.inhousesetting.dao.InhouseSettingDao;
import kr.co.dysnt.framework.weightsetting.inhousesetting.service.InhouseSettingService;

/**
 * <pre>
 * Class Name: ReduceServiceImpl.java
 * Description: 권한 ServiceImpl
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 14.
 * @version 1.0
 * @see
 */
@Service("InhouseSettingService")
public class InhouseSettingServiceImpl extends CommonServiceImpl implements InhouseSettingService {

	@Resource(name = "inhouseSettingDao")
	private InhouseSettingDao inhouseSettingDao;

	@Override
	public List<EgovMap> searchInhouseSettingList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return inhouseSettingDao.searchInhouseSettingList(param);
	}

	@Override
	public int saveInhouseSettingList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			setParamInfo(map);
			// authDao.saveAuthList(map);

			if (map.get("status") != null) {
				switch (map.get("status").toString()) {
					case "C":
						cnt += inhouseSettingDao.saveInhouseSettingList_I(map);
						break;
					case "U":
						cnt += inhouseSettingDao.saveInhouseSettingList_U(map);
						break;
					case "D":
						cnt += inhouseSettingDao.saveInhouseSettingList_D(map);
						break;
				}
			}
		}

		return cnt;
	}

}

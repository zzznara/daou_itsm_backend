package kr.co.dysnt.framework.weightsetting.reduce.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.weightsetting.reduce.dao.ReduceDao;
import kr.co.dysnt.framework.weightsetting.reduce.service.ReduceService;

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
@Service("ReduceService")
public class ReduceServiceImpl extends CommonServiceImpl implements ReduceService {

	@Resource(name = "reduceDao")
	private ReduceDao reduceDao;

	@Override
	public List<EgovMap> searchReduceList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return reduceDao.searchReduceList(param);
	}

	@Override
	public int saveReduceList(List<Map<String, Object>> param) throws Exception {
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
						cnt += reduceDao.saveReduceList_I(map);
						break;
					case "U":
						cnt += reduceDao.saveReduceList_U(map);
						break;
					case "D":
						cnt += reduceDao.saveReduceList_D(map);
						break;
				}
			}
		}

		return cnt;
	}

}

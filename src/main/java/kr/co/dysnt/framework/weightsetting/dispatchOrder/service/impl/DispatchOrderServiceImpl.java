package kr.co.dysnt.framework.weightsetting.dispatchOrder.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.weightsetting.dispatchOrder.dao.DispatchOrderDao;
import kr.co.dysnt.framework.weightsetting.dispatchOrder.service.DispatchOrderService;

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
@Service("DispatchOrderService")
public class DispatchOrderServiceImpl extends CommonServiceImpl implements DispatchOrderService {

	@Resource(name = "dispatchOrderDao")
	private DispatchOrderDao dispatchOrderDao;

	@Override
	public List<EgovMap> SearchCarList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		List<EgovMap> rtnList = dispatchOrderDao.SearchCarList(param);

		return rtnList;
	}

	@Override
	public List<EgovMap> SearchCarDispatchOrderList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		List<EgovMap> rtnList = dispatchOrderDao.SearchCarDispatchOrderList(param);

		return rtnList;
	}

	@Override
	public int saveCarDispatchOrderList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);

			setParamInfo(map);
			// if(map.get("inGb") != null && map.get("inGb").equals("1"))//ERP 사전배차 데이터는
			// 제외한다
			// {
			// continue;
			// }

			if ("C".equals(map.get("status"))) {
				// do nothing
			} else if ("U".equals(map.get("status"))) {
				result = dispatchOrderDao.updateCarDispatchOrderList(map);
			} else if ("D".equals(map.get("status"))) {
				// do nothing
			}
			if (result < 0) {
				return result;
			}
		}
		return 1;
	}

	@Override
	public List<EgovMap> chkRecord(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		List<EgovMap> rtnList = dispatchOrderDao.chkRecord(param);

		return rtnList;
	}
}

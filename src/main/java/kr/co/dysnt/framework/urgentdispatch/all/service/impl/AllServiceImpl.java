package kr.co.dysnt.framework.urgentdispatch.all.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.urgentdispatch.all.dao.AllDao;
import kr.co.dysnt.framework.urgentdispatch.all.service.AllService;

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
@Service("AllService")
public class AllServiceImpl extends CommonServiceImpl implements AllService {

	@Resource(name = "allDao")
	private AllDao allDao;

	@Override
	public List<EgovMap> searchAllDispatchList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		List<EgovMap> rtnList = allDao.searchPurchaseDispatchList(param);

		return rtnList;
	}
}

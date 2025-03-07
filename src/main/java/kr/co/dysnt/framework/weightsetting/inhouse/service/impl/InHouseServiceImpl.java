package kr.co.dysnt.framework.weightsetting.inhouse.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.weightsetting.inhouse.dao.InHouseDao;
import kr.co.dysnt.framework.weightsetting.inhouse.service.InHouseService;

/**
 * <pre>
 * Class Name: InHouseServiceImpl.java
 * Description: 권한 ServiceImpl
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 14.
 * @version 1.0
 * @see
 */
@Service("InHouseService")
public class InHouseServiceImpl extends CommonServiceImpl implements InHouseService {

	@Resource(name = "inHouseDao")
	private InHouseDao inHouseDao;

	@Override
	public List<EgovMap> searchInHouseList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return inHouseDao.searchInHouseList(param);
	}

}

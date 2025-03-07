package kr.co.dysnt.framework.inhousecar.inhousecar.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.inhousecar.inhousecar.dao.InHouseCarDao;
import kr.co.dysnt.framework.inhousecar.inhousecar.service.InHouseCarService;

/**
 * <pre>
 * Class Name: SampleImpl.java
 * Description: 관리 ServiceImpl
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 */
@Service("InHouseCarService")
public class InHouseCarServiceImpl extends CommonServiceImpl implements InHouseCarService {

	@Resource(name = "inHouseCarDao")
	private InHouseCarDao inHouseCarDao;

	@Override
	public List<EgovMap> searchInHouseCarList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return inHouseCarDao.searchInHouseCarList(param);
	}

}

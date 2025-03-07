package kr.co.dysnt.framework.resultinquery.interfacelist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.resultinquery.interfacelist.dao.InterfaceDao;
import kr.co.dysnt.framework.resultinquery.interfacelist.service.InterfaceService;

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
@Service("InterfaceService")
public class InterfaceServiceImpl extends CommonServiceImpl implements InterfaceService {

	@Resource(name = "interfaceDao")
	private InterfaceDao interfaceDao;

	@Override
	public List<EgovMap> searchInterfaceList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return interfaceDao.searchInterfaceList(param);
	}

}

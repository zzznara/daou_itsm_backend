package kr.co.dysnt.framework.resultinquery.result.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.resultinquery.result.dao.ResultDao;
import kr.co.dysnt.framework.resultinquery.result.service.ResultService;

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
@Service("ResultService")
public class ResultServiceImpl extends CommonServiceImpl implements ResultService {

	@Resource(name = "resultDao")
	private ResultDao resultDao;

	@Override
	public List<EgovMap> searchResultList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return resultDao.searchResultList(param);
	}

}

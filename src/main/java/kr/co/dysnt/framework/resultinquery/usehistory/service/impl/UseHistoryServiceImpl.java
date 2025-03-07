package kr.co.dysnt.framework.resultinquery.usehistory.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.resultinquery.usehistory.dao.UseHistoryDao;
import kr.co.dysnt.framework.resultinquery.usehistory.service.UseHistoryService;

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
@Service("UseHistoryService")
public class UseHistoryServiceImpl extends CommonServiceImpl implements UseHistoryService {

	@Resource(name = "useHistoryDao")
	private UseHistoryDao useHistoryDao;

	@Override
	public List<EgovMap> searchUseHistoryList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return useHistoryDao.searchUseHistoryList(param);
	}

}

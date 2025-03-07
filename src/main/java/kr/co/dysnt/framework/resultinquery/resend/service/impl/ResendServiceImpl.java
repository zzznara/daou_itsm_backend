package kr.co.dysnt.framework.resultinquery.resend.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;
import kr.co.dysnt.framework.resultinquery.resend.dao.ResendDao;
import kr.co.dysnt.framework.resultinquery.resend.service.ResendService;

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
@Service("ResendService")
public class ResendServiceImpl extends CommonServiceImpl implements ResendService {

	@Resource(name = "resendDao")
	private ResendDao resendDao;

	@Override
	public List<EgovMap> searchResendList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return resendDao.searchResendList(param);
	}

	@Override
	public int saveResendList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			setParamInfo(map);

			if ("C".equals(map.get("status"))) {
				result = resendDao.insertResend(map);
			} else if ("U".equals(map.get("status"))) {
				result = resendDao.updateResend(map);
			} else if ("D".equals(map.get("status"))) {
				result = resendDao.deleteResend(map);
			}
			if (result < 0) {
				return result;
			}
		}
		return 1;
	}

}

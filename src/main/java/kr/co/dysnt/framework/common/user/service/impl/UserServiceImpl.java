package kr.co.dysnt.framework.common.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.common.user.dao.UserDao;
import kr.co.dysnt.framework.common.user.service.UserService;
import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;

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
@Service("UserService")
public class UserServiceImpl extends CommonServiceImpl implements UserService {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public List<EgovMap> searchUserList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		List<EgovMap> rtnList = userDao.searchUserList(param);
		for (int i = 0; i < rtnList.size(); i++) {
			if (rtnList.get(i) != null && rtnList.get(i).get("pwd") != null) {
				rtnList.get(i).put("pwd", "");// pwd 안나오게 처리
			}
		}

		return rtnList;
	}

	@Override
	public int saveUserList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			String password = (String) map.get("pwd");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String pwd = encoder.encode(password);
			map.put("pwd", pwd);
			// String password1 = (String)map.get("pwdChg");
			//
			// if(!"".equals(password) && password != null) {
			//
			// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			// String pwd = encoder.encode(password);
			//
			// System.out.println("drivTel === " + password);
			//
			// map.put("pwd", pwd);
			// }
			//
			// if(!"".equals(password1) && password1 != null) {
			//
			// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			// String pwd1 = encoder.encode(password1);
			//
			// System.out.println("drivTel === " + password1);
			//
			// map.put("pwdChg", pwd1);
			// }

			setParamInfo(map);
			if ("C".equals(map.get("status"))) {
				result = userDao.insertUser(map);
			} else if ("U".equals(map.get("status"))) {
				result = userDao.updateUser(map);
			} else if ("D".equals(map.get("status"))) {
				result = userDao.deleteUser(map);
			}
			if (result < 0) {
				return result;
			}
		}
		return 1;
	}

	public int changePwd(Map<String, Object> param) throws Exception {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode(param.get("userPwd").toString());
		param.put("pwd", pwd);

		setParamInfo(param);

		int result = userDao.changePwd(param);
		return result;
	}

}

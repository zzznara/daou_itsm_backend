package kr.co.dysnt.framework.common.auth.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.common.auth.dao.AuthDao;
import kr.co.dysnt.framework.common.auth.service.AuthService;
import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;

/**
 * <pre>
 * Class Name: AuthServiceImpl.java
 * Description: 권한 ServiceImpl
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 14.
 * @version 1.0
 * @see
 */
@Service("AuthService")
public class AuthServiceImpl extends CommonServiceImpl implements AuthService {

	@Resource(name = "authDao")
	private AuthDao authDao;

	@Override
	public List<EgovMap> searchAuthList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return authDao.searchAuthList(param);
	}

	@Override
	public int deleteAuthList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		return authDao.deleteAuthList(param);
	}

	@Override
	public int saveAuthList(List<Map<String, Object>> param) throws Exception {
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
						cnt += authDao.saveAuthList_I(map);
						break;
					case "U":
						cnt += authDao.saveAuthList_U(map);
						break;
					case "D":
						cnt += authDao.saveAuthList_D(map);
						break;
				}
			}
		}

		return cnt;
	}

	@Override
	public List<EgovMap> searchAuthMenuList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return authDao.searchAuthMenuList(param);
	}

	@Override
	public int deleteAuthMenuList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		return authDao.deleteAuthMenuList(param);
	}

	@Override
	@Transactional
	public int saveAuthMenuList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int cnt = 0;

		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			setParamInfo(map);
			// authDao.saveAuthMenuList(map);
			if (map.get("status") != null) {
				switch (map.get("status").toString()) {
					case "C":
						cnt += authDao.saveAuthMenuList_I(map);
						break;
					case "U":
						cnt += authDao.saveAuthMenuList_U(map);
						break;
					case "D":
						cnt += authDao.saveAuthMenuList_D(map);
						break;
				}
			}
		}

		return cnt;
	}

	@Override
	public List<EgovMap> searchAuthGroupList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return authDao.searchAuthGroupList(param);
	}

	@Override
	public int deleteAuthGroupList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		return authDao.deleteAuthGroupList(param);
	}

	@Override
	@Transactional
	public int saveAuthGroupList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			setParamInfo(map);
			// authDao.saveAuthGroupList(map);

			if (map.get("status") != null) {
				switch (map.get("status").toString()) {
					case "C":
						cnt += authDao.saveAuthGroupList_I(map);
						break;
					case "U":
						cnt += authDao.saveAuthGroupList_U(map);
						break;
					case "D":
						cnt += authDao.saveAuthGroupList_D(map);
						break;
				}
			}
		}

		return cnt;
	}

}

package kr.co.dysnt.framework.core.security.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.core.security.dao.TmsUserDao;
import kr.co.dysnt.framework.core.security.user.TmsUser;

//import egovframework.rte.psl.dataaccess.util.EgovMap;//전자정부 4.0 미만
import org.egovframe.rte.psl.dataaccess.util.EgovMap;//전자정부 4.0 이상

@Service("tmsUserDetailsService")
public class TmsUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	TmsUserDao tmsUserDao;

	@Override
	public TmsUser loadUserByUsername(String username) throws UsernameNotFoundException {
		TmsUser user = new TmsUser();

		try {
			// 로그인 사용자 기본 정보 불러오기
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("userId", username);
			System.out.println("----------------map----------------" + map);
			user = tmsUserDao.getUserById(map);
			System.out.println("----------------user----------------" + user);

			// List<EgovMap> list = (List<EgovMap>)map.get("result");
			// user = (TmsUser) EgovMapUtils.convertMapToObject((EgovMap)list.get(0), user);

		} catch (Exception e) {
			// e.printStackTrace();
			user = null;
		}

		if (user == null) {
			// throw new TMSUsernameNotFoundException("유저 아이디가 없습니다. 다시 입력하세요.");
			// throw new UsernameNotFoundException(username);
			user = null;
		}

		return user;
	}

}

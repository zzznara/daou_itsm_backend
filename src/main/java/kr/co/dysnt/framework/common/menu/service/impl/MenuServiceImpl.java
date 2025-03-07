package kr.co.dysnt.framework.common.menu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.common.menu.dao.MenuDao;
import kr.co.dysnt.framework.common.menu.service.MenuService;
import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;

/**
 * <pre>
 * Class Name: SampleImpl.java
 * Description: 관리 ServiceImpl
 * </pre>
 *
 * @author 류승우
 * @since 2022. 01. 26.
 * @version 1.0
 * @see
 */
@Service("MenuService")
public class MenuServiceImpl extends CommonServiceImpl implements MenuService {

	@Resource(name = "menuDao")
	private MenuDao menuDao;

	@Override
	public List<EgovMap> searchMenuList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return menuDao.searchMenuList(param);
	}

	@Override
	public void deleteMenuList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		menuDao.deleteMenuList(param);
	}

	@Override
	public int saveMenuList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			setParamInfo(map);
			// authDao.saveAuthList(map);

			if (map.get("status") != null) {
				switch (map.get("status").toString()) {
					case "C":
						result += menuDao.saveMenuList_I(map);
						break;
					case "U":
						result += menuDao.saveMenuList_U(map);
						break;
					case "D":
						result += menuDao.saveMenuList_D(map);
						break;
				}
			}
		}

		return result;
	}

	@Override
	public List<EgovMap> searchUserMenuList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return menuDao.searchUserMenuList(param);
	}

	@Override
	public List<EgovMap> searchMenuAuthInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return menuDao.searchMenuAuthInfo(param);
	}

}

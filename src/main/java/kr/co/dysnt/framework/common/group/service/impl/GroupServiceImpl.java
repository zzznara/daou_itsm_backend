package kr.co.dysnt.framework.common.group.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.common.group.dao.GroupDao;
import kr.co.dysnt.framework.common.group.service.GroupService;
import kr.co.dysnt.framework.core.util.service.CommonServiceImpl;

/**
 * <pre>
 * Class Name: GroupServiceImpl.java
 * Description: 권한 ServiceImpl
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 16.
 * @version 1.0
 * @see
 */
@Service("GroupService")
public class GroupServiceImpl extends CommonServiceImpl implements GroupService {

	@Resource(name = "groupDao")
	private GroupDao groupDao;

	@Override
	public List<EgovMap> searchUserList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return groupDao.searchUserList(param);
	}

	@Override
	public List<EgovMap> searchGroupList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return groupDao.searchGroupList(param);
	}

	@Override
	public int deleteGroupList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		// groupDao.deleteGroupList(param);
		return groupDao.saveGroupList_D(param);
	}

	@Override
	@Transactional
	public int saveGroupList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			setParamInfo(map);
			// groupDao.saveGroupList(map);

			if (map.get("status") != null) {
				switch (param.get(i).get("status").toString()) {
					case "C":
						cnt += groupDao.saveGroupList_I(map);
						break;
					case "U":
						cnt += groupDao.saveGroupList_U(map);
						break;
					case "D":
						cnt += groupDao.saveGroupList_D(map);
						break;
				}
			}

		}

		return cnt;
	}

	@Override
	public List<EgovMap> searchGroupUserList(Map<String, Object> param) throws Exception {
		return groupDao.searchGroupUserList(param);
	}

	@Override
	public int deleteGroupUserList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		// groupDao.deleteGroupUserList(param);
		return groupDao.saveGroupUserList_D(param);
	}

	@Override
	@Transactional
	public int saveGroupUserList(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			setParamInfo(map);
			// groupDao.saveGroupUserList(map);

			if (map.get("status") != null) {
				switch (map.get("status").toString()) {
					case "C":
						cnt += groupDao.saveGroupUserList_I(map);
						break;
					case "U":
						cnt += groupDao.saveGroupUserList_U(map);
						break;
					case "D":
						cnt += groupDao.saveGroupUserList_D(map);
						break;
				}
			}

		}

		return cnt;
	}

}

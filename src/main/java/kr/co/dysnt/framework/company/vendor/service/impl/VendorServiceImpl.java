package kr.co.dysnt.framework.company.vendor.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.company.vendor.dao.VendorDao;
import kr.co.dysnt.framework.company.vendor.service.VendorService;
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
@Service("VendorService")
public class VendorServiceImpl extends CommonServiceImpl implements VendorService {

	@Resource(name = "vendorDao")
	private VendorDao vendorDao;

	@Override
	public List<EgovMap> searchVendorList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return vendorDao.searchVendorList(param);
	}

	@Override
	public void deleteVendor(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		setParamInfo(param);
		vendorDao.deleteVendor(param);
	}

	@Override
	@Transactional
	public void saveVendor(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> map = null;
			map = param.get(i);
			System.out.println(map);
			setParamInfo(map);
			vendorDao.saveVendor(map);

			// 공급업체 등록시 사업자번호, 사업자번호 뒤 5자리를 사용자 아이디와 패스워드로 등록
			if ("C".equals(map.get("status"))) {
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("systemCd", map.get("systemCd"));
				userMap.put("userId", map.get("vndrCd"));
				userMap.put("empNo", map.get("vndrCd"));
				userMap.put("nmKor", map.get("vndrNm"));

				String bsnoCd = (String) map.get("vndrCd");
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String pwd = encoder.encode(bsnoCd.substring(bsnoCd.length() - 5));

				System.out.println("bsnoCd === " + bsnoCd);
				System.out.println("password === " + bsnoCd.substring(bsnoCd.length() - 5));

				userMap.put("pwd", pwd);
				userMap.put("mobileNo", map.get("mngrTel"));
				setParamInfo(userMap);

				vendorDao.createVendorUser(userMap);
			}
		}
	}

	@Override
	public List<EgovMap> searchCompanyList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		List<EgovMap> rstList = null;

		if (param.get("schState") != null) {
			switch (param.get("schState").toString()) {
				case "V":
					rstList = vendorDao.searchCompanyList_V(param);
					break;
				case "R":
					rstList = vendorDao.searchCompanyList_R(param);
					break;
				case "FR":
					rstList = vendorDao.searchCompanyList_FR(param);
					break;
				case "L":
					rstList = vendorDao.searchCompanyList_L(param);
					break;
				default:
					break;
			}
		}

		return rstList;
	}

	@Override
	public List<EgovMap> searchVendorInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return vendorDao.searchVendorInfo(param);
	}

}

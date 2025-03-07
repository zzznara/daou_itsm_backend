package kr.co.dysnt.framework.common.code.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import kr.co.dysnt.framework.common.code.dao.CodeDao;
import kr.co.dysnt.framework.common.code.service.CodeService;
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
@Service("CodeService")
public class CodeServiceImpl extends CommonServiceImpl implements CodeService {

	@Resource(name = "codeDao")
	private CodeDao codeDao;

	@Override
	public List<EgovMap> searchMasterCode(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return codeDao.searchMasterCode(param);
	}

	@Override
	@Transactional
	public int saveMasterCode(List<Map<String, Object>> param) throws Exception {

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
						result += codeDao.saveMasterCode_I(map);
						break;
					case "U":
						result += codeDao.saveMasterCode_U(map);
						break;
					case "D":
						result += codeDao.saveMasterCode_D(map);
						break;
				}
			}
		}

		return result;

	}

	@Override
	public void deleteMasterCode(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		codeDao.saveMasterCode(param);
	}

	@Override
	public List<EgovMap> searchDetailCode(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return codeDao.searchDetailCode(param);
	}

	@Override
	@Transactional
	public int saveDetailCode(List<Map<String, Object>> param) throws Exception {

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
						result += codeDao.saveDetailCode_I(map);
						break;
					case "U":
						result += codeDao.saveDetailCode_U(map);
						break;
					case "D":
						result += codeDao.saveDetailCode_D(map);
						break;
				}
			}
		}

		return result;

	}

	@Override
	public void deleteDetailCode(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		codeDao.saveDetailCode(param);
	}

	@Override
	public List<EgovMap> searchERPCode(Map<String, Object> param) throws Exception {

		List<EgovMap> rtnList = null;

		if (param.get("mastCd") != null) {
			System.out.println(param.get("mastCd").toString());
		} else {
			System.out.println("mastCd is NULL!!!");
		}

		if (param.get("mastCd").equals("DEPT")) {
			// 부서
			rtnList = codeDao.searchERPCodeDEPT(param);
		} else if (param.get("mastCd").equals("PROD_SCRAP")) {
			// 품번 - 고철
			rtnList = codeDao.searchERPCodeProdScrap(param);
		} else if (param.get("mastCd").equals("PROD_NA_SCRAP")) {
			// 품번 - 고철외
			rtnList = codeDao.searchERPCodeProdExceptScrap(param);
		} else if (param.get("mastCd").equals("PROD_TYPE")) {
			// 품목 - 고철
			rtnList = codeDao.searchERPCodeProdType(param);
		} else if (param.get("mastCd").equals("SCRAP_GRADE")) {
			// 고철등급
			rtnList = codeDao.searchERPCodeScrapGrade(param);
		} else if (param.get("mastCd").equals("AREA")) {
			// 납품지
			rtnList = codeDao.searchAreaList(param);
		} else if (param.get("mastCd").equals("CAR")) {
			rtnList = codeDao.searchCar(param);
		} else {
			rtnList = codeDao.searchERPCode(param);
		}

		return rtnList;
	}

	@Override
	public List<EgovMap> searchWgtReduce(Map<String, Object> param) throws Exception {
		List<EgovMap> rtnList = codeDao.searchWgtReduce(param);
		return rtnList;
	}

	@Override
	public List<EgovMap> searchSales(Map<String, Object> param) throws Exception {
		List<EgovMap> rtnList = codeDao.searchSales(param);
		return rtnList;
	}

}

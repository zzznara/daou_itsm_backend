package kr.co.dysnt.framework.common.code.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import kr.co.dysnt.framework.common.dao.CommonDao;

/**
 * <pre>
 * Class Name: CodeDao.java
 * Description: 샘플 DAO Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 01. 26.
 * @version 1.0
 * @see
 */
@Repository
public class CodeDao extends CommonDao {

	private String sqlNameSpace = "common.code.";

	public List<EgovMap> searchMasterCode(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchMasterCode", param);
	}

	public List<EgovMap> searchDetailCode(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchDetailCode", param);
	}

	public void saveMasterCode(Map<String, Object> param) throws Exception {
		selectOne(sqlNameSpace + "saveMasterCode", param);
	}

	public void saveDetailCode(Map<String, Object> param) throws Exception {
		selectOne(sqlNameSpace + "saveDetailCode", param);
	}

	public int saveMasterCode_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveMasterCode_I", param);
	}

	public int saveMasterCode_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveMasterCode_U", param);
	}

	public int saveMasterCode_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveMasterCode_D", param);
	}

	public int saveDetailCode_I(Map<String, Object> param) throws Exception {
		return insert(sqlNameSpace + "saveDetailCode_I", param);
	}

	public int saveDetailCode_U(Map<String, Object> param) throws Exception {
		return update(sqlNameSpace + "saveDetailCode_U", param);
	}

	public int saveDetailCode_D(Map<String, Object> param) throws Exception {
		return delete(sqlNameSpace + "saveDetailCode_D", param);
	}

	// ERP 부서
	public List<EgovMap> searchERPCodeDEPT(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchERPCodeDEPT", param);
	}

	// ERP 품번 - 고철
	public List<EgovMap> searchERPCodeProdScrap(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchERPCodeProdScrap", param);
	}

	// ERP 품번 - 고철외
	public List<EgovMap> searchERPCodeProdExceptScrap(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchERPCodeProdExceptScrap", param);
	}

	// ERP 품목 - 고철
	public List<EgovMap> searchERPCodeProdType(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchERPCodeProdType", param);
	}

	// ERP 고철 등급
	public List<EgovMap> searchERPCodeScrapGrade(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchERPCodeScrapGrade", param);
	}

	// 국내외구분
	public List<EgovMap> searchERPCode(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchERPCode", param);
	}

	// 납품지
	public List<EgovMap> searchAreaList(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchAreaList", param);
	}

	// 차량번호
	public List<EgovMap> searchCar(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchCar", param);
	}

	// 혼적코드
	public List<EgovMap> searchWgtReduce(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchWgtReduce", param);
	}

	// 수주정보
	public List<EgovMap> searchSales(Map<String, Object> param) throws Exception {
		return selectList(sqlNameSpace + "searchSales", param);
	}

}

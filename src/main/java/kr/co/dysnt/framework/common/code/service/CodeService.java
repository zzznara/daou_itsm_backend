package kr.co.dysnt.framework.common.code.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: CodeService.java
 * Description: 샘플 Service Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 01. 26.
 * @version 1.0
 * @see
 *
 */
public interface CodeService {

	List<EgovMap> searchMasterCode(Map<String, Object> param) throws Exception;

	int saveMasterCode(List<Map<String, Object>> param) throws Exception;

	void deleteMasterCode(Map<String, Object> param) throws Exception;

	List<EgovMap> searchDetailCode(Map<String, Object> param) throws Exception;

	int saveDetailCode(List<Map<String, Object>> param) throws Exception;

	void deleteDetailCode(Map<String, Object> param) throws Exception;

	List<EgovMap> searchERPCode(Map<String, Object> param) throws Exception;

	List<EgovMap> searchWgtReduce(Map<String, Object> param) throws Exception;

	List<EgovMap> searchSales(Map<String, Object> param) throws Exception;
}

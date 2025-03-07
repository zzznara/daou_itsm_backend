package kr.co.dysnt.framework.weightsetting.wssSetting.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: ScrapService.java
 * Description: 고철 Service Class
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 19.
 * @version 1.0
 * @see
 *
 */
public interface WssSettingService {

	List<EgovMap> SearchCommonSettings(Map<String, Object> param) throws Exception;

	List<EgovMap> SearchWeighbridgeDirections(Map<String, Object> param) throws Exception;

	List<EgovMap> SearchWeightBrigdeAllowErrRangeSettings(Map<String, Object> param) throws Exception;

	List<EgovMap> SearchWeightBrigdeBilletAllowErrRangeSettings(Map<String, Object> param) throws Exception;

	int saveSingleSetting(Map<String, Object> param) throws Exception;

	int saveCommonSettings(Map<String, Object> param) throws Exception;

	int saveWeighbridgeDirections(List<Map<String, Object>> param) throws Exception;

	int saveWeightBrigdeAllowErrRangeSettings(Map<String, Object> param) throws Exception;

	int saveWeightBrigdeBilletAllowErrRangeSettings(Map<String, Object> param) throws Exception;

}

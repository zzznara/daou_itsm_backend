package kr.co.dysnt.framework.urgentdispatch.safety.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dysnt.framework.core.util.controller.CommonController;
import kr.co.dysnt.framework.urgentdispatch.safety.service.SafetyService;

/**
 * <pre>
 * Class Name: ScrapController.java
 * Description:  긴급배차 고철 Controller Class
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 19.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/urgentdispatch/safety")
@RestController
public class SafetyController extends CommonController {

	@Autowired
	private SafetyService safetyService;

	@RequestMapping(value = "/searchSafetyDispatchList")
	public @ResponseBody JSONObject searchSafetyDispatchList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("category", (String) reqParam.get("category"));
		param.put("custCd", (String) reqParam.get("custCd"));
		param.put("custNm", (String) reqParam.get("custNm"));
		param.put("prodCd1", (String) reqParam.get("prodCd1"));
		param.put("prodNm", (String) reqParam.get("prodNm"));
		param.put("status", (String) reqParam.get("status"));
		param.put("carNo", (String) reqParam.get("carNo"));
		param.put("dt", (String) reqParam.get("dt"));
		param.put("saveGB", (String) reqParam.get("saveGB"));
		param.put("ioGb", (String) reqParam.get("ioGb"));

		List<EgovMap> resultList = safetyService.searchSafetyDispatchList(param);
		setSuccess(result, resultList);
		return result;
	}

	@RequestMapping(value = "/saveSafetyDispatchList")
	public @ResponseBody JSONObject saveSafetyDispatchList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		safetyService.saveSafetyDispatchList(reqParam);
		setSuccess(result, "success");
		return result;
	}

}

package kr.co.dysnt.framework.common.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dysnt.framework.common.code.service.CodeService;
import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;

/**
 * <pre>
 * Class Name: CodeController.java
 * Description:  샘플 Controller Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 02. 07.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/common/code")
@RestController
public class CodeController extends CommonController {

	@Autowired
	MessageSource localeMessageSource;

	@Autowired
	private CodeService codeService;

	@RequestMapping(value = "/searchMasterCode")
	public @ResponseBody JSONObject searchMasterCode(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		System.out.println("mastCd ==" + (String) reqParam.get("mastCd"));
		System.out.println("cdNmKor ==" + (String) reqParam.get("cdNmKor"));
		System.out.println("detlCd == " + (String) reqParam.get("detlCd"));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mastCd", (String) reqParam.get("mastCd"));
		param.put("cdNmKor", (String) reqParam.get("cdNmKor"));
		param.put("useYn", (String) reqParam.get("useYn"));
		List<EgovMap> rstList = codeService.searchMasterCode(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/searchDetailCode")
	public @ResponseBody JSONObject searchDetailCode(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		System.out.println("mastCd ==" + (String) reqParam.get("mastCd"));
		System.out.println("detlCd == " + (String) reqParam.get("detlCd"));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mastCd", IUtility.parseNull(reqParam.get("mastCd")));
		param.put("detlCd", IUtility.parseNull(reqParam.get("detlCd")));
		param.put("useYn", IUtility.parseNull(reqParam.get("useYn")));
		param.put("attr1", IUtility.parseNull(reqParam.get("attr1")));
		param.put("attr2", IUtility.parseNull(reqParam.get("attr2")));
		param.put("attr3", IUtility.parseNull(reqParam.get("attr3")));
		param.put("attr4", IUtility.parseNull(reqParam.get("attr4")));
		param.put("attr5", IUtility.parseNull(reqParam.get("attr5")));
		param.put("attr6", IUtility.parseNull(reqParam.get("attr6")));
		param.put("attr7", IUtility.parseNull(reqParam.get("attr7")));
		param.put("attr8", IUtility.parseNull(reqParam.get("attr8")));
		param.put("attr9", IUtility.parseNull(reqParam.get("attr9")));
		param.put("attr10", IUtility.parseNull(reqParam.get("attr10")));

		List<EgovMap> rstList = codeService.searchDetailCode(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/saveMasterCode")
	public @ResponseBody JSONObject saveMasterCode(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		System.out.println(reqParam);
		HashMap<String, Object> param = new HashMap<String, Object>();
		codeService.saveMasterCode(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/deleteMasterCode")
	public @ResponseBody JSONObject deleteMasterCode(HttpServletRequest request,
			@RequestBody Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		System.out.println(reqParam);
		HashMap<String, Object> param = new HashMap<String, Object>();
		codeService.deleteMasterCode(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/saveDetailCode")
	public @ResponseBody JSONObject saveDetailCode(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		System.out.println(reqParam);
		HashMap<String, Object> param = new HashMap<String, Object>();
		codeService.saveDetailCode(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/deleteDetailCode")
	public @ResponseBody JSONObject saveDetailCode(HttpServletRequest request,
			@RequestBody Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		System.out.println(reqParam);
		HashMap<String, Object> param = new HashMap<String, Object>();
		codeService.deleteDetailCode(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/searchERPCode")
	public @ResponseBody JSONObject searchERPCode(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		System.out.println("mastCd ==" + (String) reqParam.get("mastCd"));
		System.out.println("detlCd == " + (String) reqParam.get("detlCd"));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mastCd", IUtility.parseNull(reqParam.get("mastCd")));
		param.put("detlCd", IUtility.parseNull(reqParam.get("detlCd")));
		// param.put("useYn", IUtility.parseNull(reqParam.get("useYn")));
		// param.put("attr1", IUtility.parseNull(reqParam.get("attr1")));
		// param.put("attr2", IUtility.parseNull(reqParam.get("attr2")));
		// param.put("attr3", IUtility.parseNull(reqParam.get("attr3")));
		// param.put("attr4", IUtility.parseNull(reqParam.get("attr4")));
		// param.put("attr5", IUtility.parseNull(reqParam.get("attr5")));
		// param.put("attr6", IUtility.parseNull(reqParam.get("attr6")));
		// param.put("attr7", IUtility.parseNull(reqParam.get("attr7")));
		// param.put("attr8", IUtility.parseNull(reqParam.get("attr8")));
		// param.put("attr9", IUtility.parseNull(reqParam.get("attr9")));
		// param.put("attr10", IUtility.parseNull(reqParam.get("attr10")));
		param.put("searchTxt", IUtility.parseNull(reqParam.get("searchTxt")));

		List<EgovMap> rstList = codeService.searchERPCode(param);
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/searchWgtReduce")
	public @ResponseBody JSONObject WgtReduceSelectBox(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("custCd", IUtility.parseNull(reqParam.get("custCd")));
		param.put("custNm", IUtility.parseNull(reqParam.get("custNm")));
		param.put("prodNm", IUtility.parseNull(reqParam.get("prodNm")));

		List<EgovMap> rstList = codeService.searchWgtReduce(param);
		setSuccess(result, rstList);

		return result;
	}

	@RequestMapping(value = "/searchSales")
	public @ResponseBody JSONObject searchSales(HttpServletRequest request, @RequestParam Map<String, String> reqParam)
			throws Exception {
		JSONObject result = new JSONObject();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fromDt", IUtility.parseNull(reqParam.get("fromDt")));// 언제부터
		param.put("toDt", IUtility.parseNull(reqParam.get("toDt")));// 언제까지
		param.put("custSearchTxt", IUtility.parseNull(reqParam.get("custSearchTxt")));// 거래처 검색조건
		param.put("jno", IUtility.parseNull(reqParam.get("jno")));// 전표번호

		List<EgovMap> rstList = codeService.searchSales(param);
		setSuccess(result, rstList);

		return result;
	}
}

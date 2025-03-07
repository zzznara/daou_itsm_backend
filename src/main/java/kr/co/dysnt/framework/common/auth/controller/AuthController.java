package kr.co.dysnt.framework.common.auth.controller;

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

import kr.co.dysnt.framework.common.auth.service.AuthService;
import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;

/**
 * <pre>
 * Class Name: AuthController.java
 * Description:  권한 Controller Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 14.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/common/auth")
@RestController
public class AuthController extends CommonController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/searchAuthList")
	public @ResponseBody JSONObject searchAuthList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("schAuthId ==" + (String) reqParam.get("schAuthId"));
		System.out.println("schAuthNm ==" + (String) reqParam.get("schAuthNm"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schAuthId", IUtility.parseNull(reqParam.get("schAuthId")));
		param.put("schAuthNm", IUtility.parseNull(reqParam.get("schAuthNm")));
		List<EgovMap> rstList = authService.searchAuthList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/deleteAuthList")
	public @ResponseBody JSONObject deleteAuthList(HttpServletRequest request,
			@RequestBody Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		authService.deleteAuthList(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/saveAuthList")
	public @ResponseBody JSONObject saveAuthList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		authService.saveAuthList(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/searchAuthMenuList")
	public @ResponseBody JSONObject searchAuthMenuList(HttpServletRequest request,
			@RequestParam Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("schAuthId ==" + (String) reqParam.get("schAuthId"));
		System.out.println("schAuthNm ==" + (String) reqParam.get("schAuthNm"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schAuthId", IUtility.parseNull(reqParam.get("schAuthId")));
		param.put("schAuthNm", IUtility.parseNull(reqParam.get("schAuthNm")));

		List<EgovMap> rstList = authService.searchAuthMenuList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/deleteAuthMenuList")
	public @ResponseBody JSONObject deleteAuthMenuList(HttpServletRequest request,
			@RequestBody Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		authService.deleteAuthMenuList(reqParam);
		setSuccess(result, reqParam.get("result"));
		return result;
	}

	@RequestMapping(value = "/saveAuthMenuList")
	public @ResponseBody JSONObject saveAuthMenuList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		authService.saveAuthMenuList(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/searchAuthGroupList")
	public @ResponseBody JSONObject searchAuthGroupList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("schAuthId ==" + (String) reqParam.get("schAuthId"));
		System.out.println("schAuthNm ==" + (String) reqParam.get("schAuthNm"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schAuthId", IUtility.parseNull(reqParam.get("schAuthId")));
		param.put("schAuthNm", IUtility.parseNull(reqParam.get("schAuthNm")));

		List<EgovMap> rstList = authService.searchAuthGroupList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/deleteAuthGroupList")
	public @ResponseBody JSONObject deleteAuthGroupList(HttpServletRequest request,
			@RequestBody Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		authService.deleteAuthGroupList(reqParam);
		setSuccess(result, reqParam.get("result"));
		return result;
	}

	@RequestMapping(value = "/saveAuthGroupList")
	public @ResponseBody JSONObject saveAuthGroupList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		authService.saveAuthGroupList(reqParam);
		setSuccess(result, "success");
		return result;
	}

}

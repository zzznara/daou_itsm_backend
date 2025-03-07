package kr.co.dysnt.framework.common.menu.controller;

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

import kr.co.dysnt.framework.common.menu.service.MenuService;
import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;

/**
 * <pre>
 * Class Name: MenuController.java
 * Description:  메뉴 Controller Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 02. 25.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/common/menu")
@RestController
public class MenuController extends CommonController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/searchMenuList")
	public @ResponseBody JSONObject searchMenuList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("menuId ==" + (String) reqParam.get("menuId"));
		System.out.println("menuNmKor ==" + (String) reqParam.get("menuNmKor"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("menuId", IUtility.parseNull(reqParam.get("menuId")));
		param.put("menuNmKor", IUtility.parseNull(reqParam.get("menuNmKor")));

		List<EgovMap> resultList = menuService.searchMenuList(param);
		setSuccess(result, resultList);
		return result;
	}

	@RequestMapping(value = "/deleteMenuList")
	public @ResponseBody JSONObject deleteMenuList(HttpServletRequest request,
			@RequestBody Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		menuService.deleteMenuList(reqParam);
		setSuccess(result, reqParam.get("result"));
		return result;
	}

	@RequestMapping(value = "/saveMenuList")
	public @ResponseBody JSONObject saveMenuList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		menuService.saveMenuList(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/searchUserMenuList")
	public @ResponseBody JSONObject searchUserMenuList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("userId ==" + (String) reqParam.get("userId"));
		System.out.println("menuNmKor ==" + (String) reqParam.get("menuNmKor"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", (String) reqParam.get("userId"));
		param.put("menuNmKor", (String) reqParam.get("menuNmKor"));
		List<EgovMap> resultList = menuService.searchUserMenuList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, resultList);

		return result;
	}

	@RequestMapping(value = "/searchMenuAuthInfo")
	public @ResponseBody JSONObject searchMenuAuthInfo(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("userId ==" + (String) reqParam.get("userId"));
		System.out.println("menuId ==" + (String) reqParam.get("menuId"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", (String) reqParam.get("userId"));
		param.put("menuId", (String) reqParam.get("menuId"));
		List<EgovMap> resultList = menuService.searchMenuAuthInfo(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, resultList);
		return result;
	}

}

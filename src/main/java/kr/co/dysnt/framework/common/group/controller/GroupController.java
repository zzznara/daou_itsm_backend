package kr.co.dysnt.framework.common.group.controller;

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

import kr.co.dysnt.framework.common.group.service.GroupService;
import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;

/**
 * <pre>
 * Class Name: GroupController.java
 * Description:  권한 Controller Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 03. 16.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/common/group")
@RestController
public class GroupController extends CommonController {

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/searchUserList")
	public @ResponseBody JSONObject searchUserList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("schUserId ==" + (String) reqParam.get("schUserId"));
		System.out.println("schUserNmKor ==" + (String) reqParam.get("schUserNmKor"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schUserId", IUtility.parseNull(reqParam.get("schUserId")));
		param.put("schUserNmKor", IUtility.parseNull(reqParam.get("schUserNmKor")));

		List<EgovMap> rstList = groupService.searchUserList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);

		return result;
	}

	@RequestMapping(value = "/searchGroupList")
	public @ResponseBody JSONObject searchGroupList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("schGroupId ==" + (String) reqParam.get("schGroupId"));
		System.out.println("schGroupNm ==" + (String) reqParam.get("schGroupNm"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schGroupId", IUtility.parseNull(reqParam.get("schGroupId")));
		param.put("schGroupNm", IUtility.parseNull(reqParam.get("schGroupNm")));
		List<EgovMap> rstList = groupService.searchGroupList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/deleteGroupList")
	public @ResponseBody JSONObject deleteGroupList(HttpServletRequest request,
			@RequestBody Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		groupService.deleteGroupList(reqParam);
		setSuccess(result, reqParam.get("result"));
		return result;
	}

	@RequestMapping(value = "/saveGroupList")
	public @ResponseBody JSONObject saveGroupList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		groupService.saveGroupList(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/searchGroupUserList")
	public @ResponseBody JSONObject searchGroupUserList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("schGroupId ==" + (String) reqParam.get("schGroupId"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schGroupId", IUtility.parseNull(reqParam.get("schGroupId")));

		List<EgovMap> rstList = groupService.searchGroupUserList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/deleteGroupUserList")
	public @ResponseBody JSONObject deleteGroupUserList(HttpServletRequest request,
			@RequestBody Map<String, Object> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		groupService.deleteGroupUserList(reqParam);
		setSuccess(result, reqParam.get("result"));
		return result;
	}

	@RequestMapping(value = "/saveGroupUserList")
	public @ResponseBody JSONObject saveGroupUserList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		groupService.saveGroupUserList(reqParam);
		setSuccess(result, "success");
		return result;
	}

}

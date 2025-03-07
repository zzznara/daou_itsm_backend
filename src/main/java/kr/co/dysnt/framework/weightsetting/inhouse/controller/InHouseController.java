package kr.co.dysnt.framework.weightsetting.inhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;
import kr.co.dysnt.framework.weightsetting.inhouse.service.InHouseService;

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
@RequestMapping(value = "/weightsetting/inhouse")
@RestController
public class InHouseController extends CommonController {

	@Autowired
	private InHouseService inHouseService;

	@RequestMapping(value = "/searchInHouseList")
	public @ResponseBody JSONObject searchInHouseList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("category ==" + (String) reqParam.get("category"));
		System.out.println("custCd ==" + (String) reqParam.get("custCd"));
		System.out.println("prodCd1 ==" + (String) reqParam.get("prodCd1"));
		System.out.println("carNo ==" + (String) reqParam.get("carNo"));
		System.out.println("dt ==" + (String) reqParam.get("dt"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("category", IUtility.parseNull(reqParam.get("category")));
		param.put("custCd", IUtility.parseNull(reqParam.get("custCd")));
		param.put("prodCd1", IUtility.parseNull(reqParam.get("prodCd1")));
		param.put("carNo", IUtility.parseNull(reqParam.get("carNo")));
		param.put("dt", (String) reqParam.get("dt"));

		List<EgovMap> rstList = inHouseService.searchInHouseList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

}

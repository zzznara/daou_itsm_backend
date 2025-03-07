package kr.co.dysnt.framework.weightsetting.dispatchOrder.controller;

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
import kr.co.dysnt.framework.weightsetting.dispatchOrder.service.DispatchOrderService;

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
@RequestMapping(value = "/weightsetting/dispatchOrder")
@RestController
public class DispatchOrderController extends CommonController {

	@Autowired
	private DispatchOrderService dispatchOrderService;

	@RequestMapping(value = "/SearchCarList")
	public @ResponseBody JSONObject SearchCarList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("dt", (String) reqParam.get("dt"));
		param.put("carNo", (String) reqParam.get("carNo"));

		List<EgovMap> resultList = dispatchOrderService.SearchCarList(param);
		setSuccess(result, resultList);
		return result;
	}

	@RequestMapping(value = "/SearchCarDispatchOrderList")
	public @ResponseBody JSONObject SearchCarDispatchOrderList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("dt", (String) reqParam.get("dt"));
		param.put("carNo", (String) reqParam.get("carNo"));

		List<EgovMap> resultList = dispatchOrderService.SearchCarDispatchOrderList(param);
		setSuccess(result, resultList);
		return result;
	}

	@RequestMapping(value = "/SaveCarDispatchOrderList")
	public @ResponseBody JSONObject SaveCarDispatchOrderList(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		dispatchOrderService.saveCarDispatchOrderList(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/chkRecord")
	public @ResponseBody JSONObject chkRecord(HttpServletRequest request, @RequestParam Map<String, String> reqParam)
			throws Exception {
		JSONObject result = new JSONObject();

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("sg", (String) reqParam.get("sg"));
		param.put("dt", (String) reqParam.get("dt"));
		param.put("seq", (String) reqParam.get("seq"));

		List<EgovMap> resultList = dispatchOrderService.chkRecord(param);
		setSuccess(result, resultList);
		return result;
	}
}

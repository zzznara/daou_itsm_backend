package kr.co.dysnt.framework.company.customer.controller;

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

import kr.co.dysnt.framework.company.customer.service.CustomerService;
import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;

/**
 * <pre>
 * Class Name: CustomerController.java
 * Description:  거래처 Controller Class. 한국특강에서는 ERP 데이터를 조회하므로 TMS의 vendor 대신 사용
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 20.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/company/customer")
@RestController
public class CustomerController extends CommonController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/searchCustomerList")
	public @ResponseBody JSONObject searchCustomerList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("custCd ==" + (String) reqParam.get("custCd"));
		System.out.println("custNm ==" + (String) reqParam.get("custNm"));
		System.out.println("cust ==" + (String) reqParam.get("cust"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("custCd", IUtility.parseNull(reqParam.get("custCd")));
		param.put("custNm", IUtility.parseNull(reqParam.get("custNm")));
		param.put("cust", IUtility.parseNull(reqParam.get("cust")));
		param.put("all", IUtility.parseNull(reqParam.get("all")));

		List<EgovMap> rstList = customerService.searchCustomerList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/searchMidVendorList")
	public @ResponseBody JSONObject searchMidVendorList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("searchTxt ==" + (String) reqParam.get("searchTxt"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("searchTxt", IUtility.parseNull(reqParam.get("searchTxt")));

		List<EgovMap> rstList = customerService.searchMidVendorList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}
}

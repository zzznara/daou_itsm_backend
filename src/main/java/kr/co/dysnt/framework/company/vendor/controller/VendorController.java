package kr.co.dysnt.framework.company.vendor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dysnt.framework.company.vendor.service.VendorService;
import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * Class Name: VendorController.java
 * Description:  공급업체 Controller Class
 * </pre>
 *
 * @author 류승우
 * @since 2022. 04. 06.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/company/vendor")
@RestController
public class VendorController extends CommonController {

	@Autowired
	private VendorService vendorService;

	@RequestMapping(value = "/searchVendorList")
	public @ResponseBody JSONObject searchVendorList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("systemCd ==" + (String) reqParam.get("systemCd"));
		System.out.println("schVndrCd ==" + (String) reqParam.get("schVndrCd"));
		System.out.println("schVndrNm ==" + (String) reqParam.get("schVndrNm"));
		System.out.println("schAddr ==" + (String) reqParam.get("schAddr"));
		System.out.println("schBsnsNo ==" + (String) reqParam.get("schBsnsNo"));
		System.out.println("schVndrTypeCd ==" + (String) reqParam.get("schVndrTypeCd"));
		System.out.println("schUseYn ==" + (String) reqParam.get("schUseYn"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("systemCd", IUtility.parseNull(reqParam.get("systemCd")));
		param.put("schVndrNm", IUtility.parseNull(reqParam.get("schVndrNm")));
		param.put("schAddr", IUtility.parseNull(reqParam.get("schAddr")));
		param.put("schBsnsNo", IUtility.parseNull(reqParam.get("schBsnsNo")));
		param.put("schVndrTypeCd", IUtility.parseNull(reqParam.get("schVndrTypeCd")));
		param.put("schUseYn", IUtility.parseNull(reqParam.get("schUseYn")));

		List<EgovMap> rstList = vendorService.searchVendorList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/deleteVendor")
	public @ResponseBody JSONObject deleteVendor(HttpServletRequest request, @RequestBody Map<String, Object> reqParam)
			throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		vendorService.deleteVendor(reqParam);
		setSuccess(result, reqParam.get("result"));
		return result;
	}

	@RequestMapping(value = "/saveVendor")
	public @ResponseBody JSONObject saveVendor(HttpServletRequest request,
			@RequestBody List<Map<String, Object>> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println(reqParam);

		vendorService.saveVendor(reqParam);
		setSuccess(result, "success");
		return result;
	}

	@RequestMapping(value = "/searchCompanyList")
	public @ResponseBody JSONObject searchCompanyList(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("schPlantSystemCd ==" + (String) reqParam.get("schPlantSystemCd"));
		System.out.println("schState ==" + (String) reqParam.get("schState"));
		System.out.println("schSystemCd ==" + (String) reqParam.get("schSystemCd"));
		System.out.println("schVndrCd ==" + (String) reqParam.get("schVndrCd"));
		System.out.println("schRvndrCd ==" + (String) reqParam.get("schRvndrCd"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schState", IUtility.parseNull(reqParam.get("schState")));
		param.put("schPlantSystemCd", IUtility.parseNull(reqParam.get("schPlantSystemCd")));
		param.put("schSystemCd", IUtility.parseNull(reqParam.get("schSystemCd")));
		param.put("schVndrCd", IUtility.parseNull(reqParam.get("schVndrCd")));
		param.put("schRvndrCd", IUtility.parseNull(reqParam.get("schRvndrCd")));

		List<EgovMap> rstList = vendorService.searchCompanyList(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

	@RequestMapping(value = "/searchVendorInfo")
	public @ResponseBody JSONObject searchVendorInfo(HttpServletRequest request,
			@RequestParam Map<String, String> reqParam) throws Exception {
		JSONObject result = new JSONObject();
		System.out.println("schVndrCd ==" + (String) reqParam.get("schVndrCd"));

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("schVndrCd", IUtility.parseNull(reqParam.get("schVndrCd")));

		List<EgovMap> rstList = vendorService.searchVendorInfo(param);
		// setSuccess(result, param.get("result"));
		setSuccess(result, rstList);
		return result;
	}

}

package kr.co.dysnt.framework.resultinquery.resend.controller;

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

import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;
import kr.co.dysnt.framework.resultinquery.resend.service.ResendService;

/**
 * <pre>
 * Class Name: ResendController.java
 * Description:  메뉴 Controller Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/resultinquery/resend")
@RestController
public class ResendController extends CommonController {

    @Autowired
    private ResendService resendService;

    @RequestMapping(value = "/searchResendList")
    public @ResponseBody JSONObject searchResendList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("category ==" + (String) reqParam.get("category"));
        System.out.println("custCd ==" + (String) reqParam.get("custCd"));
        System.out.println("prodCd1 ==" + (String) reqParam.get("prodCd1"));
        System.out.println("carNo ==" + (String) reqParam.get("carNo"));
        System.out.println("ifYn ==" + (String) reqParam.get("ifYn"));
        System.out.println("startDt ==" + (String) reqParam.get("startDt"));
        System.out.println("endDt ==" + (String) reqParam.get("endDt"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("category", IUtility.parseNull(reqParam.get("category")));
        param.put("custCd", IUtility.parseNull(reqParam.get("custCd")));
        param.put("prodCd1", IUtility.parseNull(reqParam.get("prodCd1")));
        param.put("carNo", IUtility.parseNull(reqParam.get("carNo")));
        param.put("ifYn", IUtility.parseNull(reqParam.get("ifYn")));
        param.put("startDt", (String) reqParam.get("startDt"));
        param.put("endDt", (String) reqParam.get("endDt"));

        List<EgovMap> resultList = resendService.searchResendList(param);
        setSuccess(result, resultList);
        return result;
    }

    @RequestMapping(value = "/resendInterfaceList")
    public @ResponseBody JSONObject resendInterfaceList(HttpServletRequest request,
            @RequestBody List<Map<String, Object>> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        resendService.saveResendList(reqParam);
        setSuccess(result, "success");
        return result;
    }

}

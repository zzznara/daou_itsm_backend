package kr.co.dysnt.framework.resultinquery.result.controller;

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
import kr.co.dysnt.framework.resultinquery.result.service.ResultService;

/**
 * <pre>
 * Class Name: ResultController.java
 * Description:  메뉴 Controller Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/resultinquery/result")
@RestController
public class ResultController extends CommonController {

    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "/searchResultList")
    public @ResponseBody JSONObject searchResultList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("category ==" + (String) reqParam.get("category"));
        System.out.println("custCd ==" + (String) reqParam.get("custCd"));
        System.out.println("custNm ==" + (String) reqParam.get("custNm"));
        System.out.println("prodCd1 ==" + (String) reqParam.get("prodCd1"));
        System.out.println("prodNm ==" + (String) reqParam.get("prodNm"));
        System.out.println("carNo ==" + (String) reqParam.get("carNo"));
        System.out.println("startDt ==" + (String) reqParam.get("startDt"));
        System.out.println("endDt ==" + (String) reqParam.get("endDt"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("category", IUtility.parseNull(reqParam.get("category")));
        param.put("custCd", IUtility.parseNull(reqParam.get("custCd")));
        param.put("custNm", IUtility.parseNull(reqParam.get("custNm")));
        param.put("prodCd1", IUtility.parseNull(reqParam.get("prodCd1")));
        param.put("prodNm", IUtility.parseNull(reqParam.get("prodNm")));
        param.put("carNo", IUtility.parseNull(reqParam.get("carNo")));
        param.put("startDt", (String) reqParam.get("startDt"));
        param.put("endDt", (String) reqParam.get("endDt"));

        List<EgovMap> resultList = resultService.searchResultList(param);
        setSuccess(result, resultList);
        return result;
    }

}

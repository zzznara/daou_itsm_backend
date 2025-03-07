package kr.co.dysnt.framework.resultinquery.usehistory.controller;

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

import kr.co.dysnt.framework.core.util.controller.CommonController;
import kr.co.dysnt.framework.resultinquery.usehistory.service.UseHistoryService;
import kr.co.dysnt.framework.resultinquery.usehistory.service.UseHistoryService;

/**
 * <pre>
 * Class Name: UserController.java
 * Description:  메뉴 Controller Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/resultinquery/usehistory")
@RestController
public class UseHistoryController extends CommonController {

    @Autowired
    private UseHistoryService useHistoryService;

    @RequestMapping(value = "/searchUseHistoryList")
    public @ResponseBody JSONObject searchUseHistoryList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("userId ==" + (String) reqParam.get("userId"));
        System.out.println("useHisCntn ==" + (String) reqParam.get("useHisCntn"));
        System.out.println("startDt ==" + (String) reqParam.get("startDt"));
        System.out.println("endDt ==" + (String) reqParam.get("endDt"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("userId", (String) reqParam.get("userId"));
        param.put("useHisCntn", (String) reqParam.get("useHisCntn"));
        param.put("startDt", (String) reqParam.get("startDt"));
        param.put("endDt", (String) reqParam.get("endDt"));
        List<EgovMap> resultList = useHistoryService.searchUseHistoryList(param);
        setSuccess(result, resultList);
        return result;
    }

}

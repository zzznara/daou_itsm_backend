package kr.co.dysnt.framework.weightsetting.reduce.controller;

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
import kr.co.dysnt.framework.weightsetting.reduce.service.ReduceService;

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
@RequestMapping(value = "/weightsetting/reduce")
@RestController
public class ReduceController extends CommonController {

    @Autowired
    private ReduceService reduceService;

    @RequestMapping(value = "/searchReduceList")
    public @ResponseBody JSONObject searchReduceList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("reduceCd ==" + (String) reqParam.get("reduceCd"));
        System.out.println("custNm ==" + (String) reqParam.get("custNm"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("reduceCd", IUtility.parseNull(reqParam.get("reduceCd")));
        param.put("custNm", IUtility.parseNull(reqParam.get("custNm")));
        List<EgovMap> rstList = reduceService.searchReduceList(param);
        // setSuccess(result, param.get("result"));
        setSuccess(result, rstList);
        return result;
    }

    @RequestMapping(value = "/saveReduceList")
    public @ResponseBody JSONObject saveReduceList(HttpServletRequest request,
            @RequestBody List<Map<String, Object>> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        reduceService.saveReduceList(reqParam);
        setSuccess(result, "success");
        return result;
    }

}

package kr.co.dysnt.framework.weightsetting.inhousesetting.controller;

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
import kr.co.dysnt.framework.weightsetting.inhousesetting.service.InhouseSettingService;

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
@RequestMapping(value = "/weightsetting/inhousesetting")
@RestController
public class InhouseSettingController extends CommonController {

    @Autowired
    private InhouseSettingService inhouseSettingService;

    @RequestMapping(value = "/searchInhouseSettingList")
    public @ResponseBody JSONObject searchInhouseSettingList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("carNo ==" + (String) reqParam.get("carNo"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("carNo", IUtility.parseNull(reqParam.get("carNo")));

        List<EgovMap> rstList = inhouseSettingService.searchInhouseSettingList(param);
        // setSuccess(result, param.get("result"));
        setSuccess(result, rstList);
        return result;
    }

    @RequestMapping(value = "/saveInhouseSettingList")
    public @ResponseBody JSONObject saveInhouseSettingList(HttpServletRequest request,
            @RequestBody List<Map<String, Object>> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        inhouseSettingService.saveInhouseSettingList(reqParam);
        setSuccess(result, "success");
        return result;
    }

}

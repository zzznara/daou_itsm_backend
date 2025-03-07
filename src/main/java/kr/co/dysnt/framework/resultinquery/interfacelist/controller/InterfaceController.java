package kr.co.dysnt.framework.resultinquery.interfacelist.controller;

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
import kr.co.dysnt.framework.resultinquery.interfacelist.service.InterfaceService;

/**
 * <pre>
 * Class Name: InterfaceController.java
 * Description:  메뉴 Controller Class
 * </pre>
 *
 * @author 장태준
 * @since 2024. 08. 23.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/resultinquery/interfacelist")
@RestController
public class InterfaceController extends CommonController {

    @Autowired
    private InterfaceService interfaceService;

    @RequestMapping(value = "/searchInterfaceList")
    public @ResponseBody JSONObject searchInterfaceList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("userId ==" + (String) reqParam.get("userId"));
        System.out.println("nmKor ==" + (String) reqParam.get("nmKor"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("userId", (String) reqParam.get("userId"));
        param.put("nmKor", (String) reqParam.get("nmKor"));
        List<EgovMap> resultList = interfaceService.searchInterfaceList(param);
        setSuccess(result, resultList);
        return result;
    }

}

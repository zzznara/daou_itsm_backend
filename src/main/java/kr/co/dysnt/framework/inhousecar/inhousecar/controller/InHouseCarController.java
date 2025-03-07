package kr.co.dysnt.framework.inhousecar.inhousecar.controller;

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
import kr.co.dysnt.framework.inhousecar.inhousecar.service.InHouseCarService;

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
@RequestMapping(value = "/inhousecar/inhousecar")
@RestController
public class InHouseCarController extends CommonController {

    @Autowired
    private InHouseCarService inhouseCarService;

    @RequestMapping(value = "/searchInHouseCarList")
    public @ResponseBody JSONObject searchInHouseCarList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("category ==" + (String) reqParam.get("category"));
        System.out.println("carNo ==" + (String) reqParam.get("carNo"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("category", IUtility.parseNull(reqParam.get("category")));
        param.put("carNo", IUtility.parseNull(reqParam.get("carNo")));

        List<EgovMap> resultList = inhouseCarService.searchInHouseCarList(param);
        setSuccess(result, resultList);
        return result;
    }

}

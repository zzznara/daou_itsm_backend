package kr.co.dysnt.framework.common.user.controller;

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

import kr.co.dysnt.framework.common.user.service.UserService;
import kr.co.dysnt.framework.core.util.controller.CommonController;

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
@RequestMapping(value = "/common/user")
@RestController
public class UserController extends CommonController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/searchUserList")
    public @ResponseBody JSONObject searchUserList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("userId ==" + (String) reqParam.get("userId"));
        System.out.println("nmKor ==" + (String) reqParam.get("nmKor"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("userId", (String) reqParam.get("userId"));
        param.put("nmKor", (String) reqParam.get("nmKor"));
        List<EgovMap> resultList = userService.searchUserList(param);
        setSuccess(result, resultList);
        return result;
    }

    @RequestMapping(value = "/saveUserList")
    public @ResponseBody JSONObject saveUserList(HttpServletRequest request,
            @RequestBody List<Map<String, Object>> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        userService.saveUserList(reqParam);
        setSuccess(result, "success");
        return result;
    }

    @RequestMapping(value = "/changePwd")
    public @ResponseBody JSONObject changePwd(HttpServletRequest request, @RequestBody Map<String, Object> reqParam)
            throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        userService.changePwd(reqParam);
        setSuccess(result, "success");
        return result;
    }
}

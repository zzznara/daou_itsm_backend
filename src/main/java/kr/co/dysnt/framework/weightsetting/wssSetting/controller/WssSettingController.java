package kr.co.dysnt.framework.weightsetting.wssSetting.controller;

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

import kr.co.dysnt.framework.core.util.controller.CommonController;
import kr.co.dysnt.framework.weightsetting.wssSetting.service.WssSettingService;

/**
 * <pre>
 * Class Name: ScrapController.java
 * Description:  긴급배차 고철 Controller Class
 * </pre>
 *
 * @author 전현우
 * @since 2024. 09. 19.
 * @version 1.0
 * @see
 */
@RequestMapping(value = "/weightsetting/wssSetting")
@RestController
public class WssSettingController extends CommonController {

    @Autowired
    private WssSettingService wssSettingService;

    @RequestMapping(value = "/SearchCommonSettings")
    public @ResponseBody JSONObject SearchCommonSettings(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("dt", (String) reqParam.get("dt"));

        List<EgovMap> resultList = wssSettingService.SearchCommonSettings(param);
        setSuccess(result, resultList);
        return result;
    }

    @RequestMapping(value = "/SearchWeighbridgeDirections")
    public @ResponseBody JSONObject SearchWeighbridgeDirections(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("dt", (String) reqParam.get("dt"));

        List<EgovMap> resultList = wssSettingService.SearchWeighbridgeDirections(param);
        setSuccess(result, resultList);
        return result;
    }

    @RequestMapping(value = "/SearchWeightBrigdeAllowErrRangeSettings")
    public @ResponseBody JSONObject SearchWeightBrigdeAllowErrRangeSettings(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("dt", (String) reqParam.get("dt"));

        List<EgovMap> resultList = wssSettingService.SearchWeightBrigdeAllowErrRangeSettings(param);
        setSuccess(result, resultList);
        return result;
    }

    @RequestMapping(value = "/SearchWeightBrigdeBilletAllowErrRangeSettings")
    public @ResponseBody JSONObject SearchWeightBrigdeBilletAllowErrRangeSettings(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("dt", (String) reqParam.get("dt"));

        List<EgovMap> resultList = wssSettingService.SearchWeightBrigdeBilletAllowErrRangeSettings(param);
        setSuccess(result, resultList);
        return result;
    }

    @RequestMapping(value = "/saveSingleSetting")
    public @ResponseBody JSONObject saveSingleSetting(HttpServletRequest request,
            @RequestBody Map<String, Object> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("setCd", reqParam.get("setCd"));
        param.put("setVal", reqParam.get("setVal"));

        wssSettingService.saveSingleSetting(reqParam);
        setSuccess(result, "success");
        return result;
    }

    @RequestMapping(value = "/saveCommonSettings")
    public @ResponseBody JSONObject saveCommonSettings(HttpServletRequest request,
            @RequestBody Map<String, Object> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("setCd", reqParam.get("setCd"));
        param.put("setVal", reqParam.get("setVal"));

        wssSettingService.saveCommonSettings(reqParam);
        setSuccess(result, "success");
        return result;
    }

    @RequestMapping(value = "/saveWeighbridgeDirections")
    public @ResponseBody JSONObject saveWeighbridgeDirections(HttpServletRequest request,
            @RequestBody List<Map<String, Object>> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        wssSettingService.saveWeighbridgeDirections(reqParam);
        setSuccess(result, "success");
        return result;
    }

    @RequestMapping(value = "/saveWeightBrigdeAllowErrRangeSettings")
    public @ResponseBody JSONObject saveWeightBrigdeAllowErrRangeSettings(HttpServletRequest request,
            @RequestBody Map<String, Object> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        wssSettingService.saveWeightBrigdeAllowErrRangeSettings(reqParam);
        setSuccess(result, "success");
        return result;
    }

    @RequestMapping(value = "/saveWeightBrigdeBilletAllowErrRangeSettings")
    public @ResponseBody JSONObject saveWeightBrigdeBilletAllowErrRangeSettings(HttpServletRequest request,
            @RequestBody Map<String, Object> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(reqParam);

        wssSettingService.saveWeightBrigdeBilletAllowErrRangeSettings(reqParam);
        setSuccess(result, "success");
        return result;
    }

}

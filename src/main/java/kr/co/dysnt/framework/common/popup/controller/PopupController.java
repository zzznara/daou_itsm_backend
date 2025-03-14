package kr.co.dysnt.framework.common.popup.controller;

import kr.co.dysnt.framework.common.popup.service.PopupService;
import kr.co.dysnt.framework.core.util.IUtility;
import kr.co.dysnt.framework.core.util.controller.CommonController;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common/popup")
@RequiredArgsConstructor
public class PopupController extends CommonController {
    private final PopupService popupService;

    @RequestMapping(value = "/list")
    public @ResponseBody JSONObject getPopupList(HttpServletRequest request,
            @RequestParam Map<String, String> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println("popupId ==" + (String) reqParam.get("popupId"));
        System.out.println("popupNm ==" + (String) reqParam.get("popupNm"));

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("popupId", IUtility.parseNull(reqParam.get("popupId")));
        param.put("popupNm", IUtility.parseNull(reqParam.get("popupNm")));

        List<EgovMap> resultList = popupService.getPopupList(param);
        setSuccess(result, resultList);
        return result;
    }

    @RequestMapping(value = "/save")
    public @ResponseBody JSONObject save(HttpServletRequest request,
            @RequestBody Map<String, Object> reqParam) throws Exception {
        JSONObject result = new JSONObject();
        
        try {
            if (reqParam.get("popupId") == null || reqParam.get("popupId").toString().isEmpty()) {
                String popupId = popupService.generatePopupId();
                reqParam.put("popupId", popupId);
            }

            popupService.save(reqParam);
            setSuccess(result, "저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            setFailed(result, "저장 중 오류가 발생했습니다.\n"+ e.getMessage());
        }
        
        return result;
    }

    @RequestMapping(value = "/delete/{popupId}")
    public @ResponseBody JSONObject delete(HttpServletRequest request,
            @PathVariable String popupId) throws Exception {
        JSONObject result = new JSONObject();
        
        try {
            popupService.deletePopup(popupId);
            setSuccess(result, "삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            setFailed(result, "삭제 중 오류가 발생했습니다.\n"+ e.getMessage());
        }
        
        return result;
    }
}

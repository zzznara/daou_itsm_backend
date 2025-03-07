package kr.co.dysnt.framework.core.util.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import kr.co.dysnt.framework.core.util.IUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonController extends AbstractController {

    protected static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager
            .getLogger(CommonController.class);

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return null;
    }

    protected void setError(Map<String, Object> result, String errorCode, String errorMsg) {
        result.put("errorCode", errorCode);
        result.put("errorMsg", errorMsg);
        result.put("isError", "Y");
    }

    protected void setError(Map<String, Object> result, String errorCode) {
        switch (errorCode) {
            case "1001":
                setError(result, errorCode, "사용자 정보가 존재하지 않습니다.");
                break;

            case "1002":
                setError(result, errorCode, "권한이 없습니다.");
                break;

            default:
                setError(result, errorCode, "처리 중 오류가 발생하였습니다.");
                break;
        }
    }

    protected void setSuccess(Map<String, Object> result, Object data) {
        try {
            if (data instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) data;
                if (IUtility.containSystemAttr(map))
                    IUtility.removeSystemAttrData(map);

            } else if (data instanceof List) {
                List<Object> list = (List) data;
                if (list.size() > 0 && list.get(0) instanceof Map && IUtility.containSystemAttr(list.get(0))) {
                    for (int i = 0, x = list.size(); i < x; i++) {
                        Object obj = list.get(i);
                        if (obj instanceof Map) {
                            Map<String, Object> dataMap = (Map<String, Object>) obj;
                            IUtility.removeSystemAttrData(dataMap);
                        } else {
                            break;
                        }
                    }
                } else if (list.size() > 0 && list.get(0) instanceof List) {
                    for (int i = 0; i < list.size(); i++) {
                        List<Object> _list = (List) list.get(i);
                        if (_list.size() > 0 && _list.get(0) instanceof Map
                                && IUtility.containSystemAttr(_list.get(0))) {
                            for (int x = 0; _list.size() < x; x++) {
                                Object obj = _list.get(x);
                                if (obj instanceof Map) {
                                    Map<String, Object> dataMap = (Map<String, Object>) obj;
                                    IUtility.removeSystemAttrData(dataMap);
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }

        result.put("data", data);
        result.put("isSuccess", "Y");
        result.put("statusCode", "200");
    }

    protected void setSuccess(Map<String, Object> result) {
        result.put("data", new HashMap<String, Object>());
        result.put("isSuccess", "Y");
        result.put("statusCode", "200");
    }

    protected void setFailed(Map<String, Object> result, Object data) {
        try {
            if (data instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) data;
                if (IUtility.containSystemAttr(map))
                    IUtility.removeSystemAttrData(map);

            } else if (data instanceof List) {
                List<Object> list = (List) data;
                if (list.size() > 0 && list.get(0) instanceof Map && IUtility.containSystemAttr(list.get(0))) {
                    for (int i = 0, x = list.size(); i < x; i++) {
                        Object obj = list.get(i);
                        if (obj instanceof Map) {
                            Map<String, Object> dataMap = (Map<String, Object>) obj;
                            IUtility.removeSystemAttrData(dataMap);
                        } else {
                            break;
                        }
                    }
                } else if (list.size() > 0 && list.get(0) instanceof List) {
                    for (int i = 0; i < list.size(); i++) {
                        List<Object> _list = (List) list.get(i);
                        if (_list.size() > 0 && _list.get(0) instanceof Map
                                && IUtility.containSystemAttr(_list.get(0))) {
                            for (int x = 0; _list.size() < x; x++) {
                                Object obj = _list.get(x);
                                if (obj instanceof Map) {
                                    Map<String, Object> dataMap = (Map<String, Object>) obj;
                                    IUtility.removeSystemAttrData(dataMap);
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }

        result.put("data", data);
        result.put("isSuccess", "N");
        result.put("statusCode", "400");
    }

    protected void setFailed(Map<String, Object> result) {
        result.put("data", new HashMap<String, Object>());
        result.put("isSuccess", "N");
        result.put("statusCode", "400");
    }

}

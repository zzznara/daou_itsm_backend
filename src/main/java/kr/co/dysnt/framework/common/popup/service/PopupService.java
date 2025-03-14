package kr.co.dysnt.framework.common.popup.service;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import java.util.List;
import java.util.Map;

public interface PopupService {
    List<EgovMap> getPopupList(Map<String, Object> params);
    void save(Map<String, Object> params);
    void deletePopup(String popupId);
    String generatePopupId();
}

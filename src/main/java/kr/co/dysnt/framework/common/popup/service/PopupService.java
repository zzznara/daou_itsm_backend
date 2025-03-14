package kr.co.dysnt.framework.common.popup.service;

import kr.co.dysnt.framework.common.popup.domain.Popup;

import java.util.List;
import java.util.Map;

public interface PopupService {
    List<Popup> getPopupList(Map<String, Object> params);
    Popup getPopup(String popupId);
    void createPopup(Popup popup);
    void updatePopup(Popup popup);
    void deletePopup(String popupId);
    String generatePopupId();
}

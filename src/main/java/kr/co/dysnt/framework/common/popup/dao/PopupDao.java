package kr.co.dysnt.framework.common.popup.dao;

import kr.co.dysnt.framework.common.popup.domain.Popup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PopupDao {
    List<Popup> selectPopupList(Map<String, Object> params);
    Popup selectPopup(String popupId);
    int insertPopup(Popup popup);
    int updatePopup(Popup popup);
    int deletePopup(String popupId);
    String selectLastPopupId(String datePrefix);
}

package kr.co.dysnt.framework.common.popup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import java.util.List;
import java.util.Map;

@Mapper
public interface PopupDao {
    List<EgovMap> selectPopupList(Map<String, Object> params);
    void insertPopup(Map<String, Object> params);
    void updatePopup(Map<String, Object> params);
    void deletePopup(String popupId);
}

package kr.co.dysnt.framework.common.popup.service.impl;

import kr.co.dysnt.framework.common.popup.dao.PopupDao;
import kr.co.dysnt.framework.common.popup.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PopupServiceImpl implements PopupService {
    private final PopupDao popupDao;

    @Override
    public List<EgovMap> getPopupList(Map<String, Object> params) {
        return popupDao.selectPopupList(params);
    }

    @Override
    @Transactional
    public void save(Map<String, Object> params) {
        if (params.get("popupId") == null || params.get("popupId").toString().isEmpty()) {
            popupDao.insertPopup(params);
        } else {
            popupDao.updatePopup(params);
        }
    }

    @Override
    public void deletePopup(String popupId) {
        popupDao.deletePopup(popupId);
    }

    @Override
    public String generatePopupId() {
        return "POP" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

package kr.co.dysnt.framework.common.popup.service.impl;

import kr.co.dysnt.framework.common.popup.dao.PopupDao;
import kr.co.dysnt.framework.common.popup.domain.Popup;
import kr.co.dysnt.framework.common.popup.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PopupServiceImpl implements PopupService {

    private final PopupDao popupDao;

    @Override
    public List<Popup> getPopupList(Map<String, Object> params) {
        return popupDao.selectPopupList(params);
    }

    @Override
    public Popup getPopup(String popupId) {
        return popupDao.selectPopup(popupId);
    }

    @Override
    @Transactional
    public void createPopup(Popup popup) {
        popup.setInptDt(LocalDateTime.now());
        popupDao.insertPopup(popup);
    }

    @Override
    @Transactional
    public void updatePopup(Popup popup) {
        popup.setUpdtDt(LocalDateTime.now());
        popupDao.updatePopup(popup);
    }

    @Override
    @Transactional
    public void deletePopup(String popupId) {
        popupDao.deletePopup(popupId);
    }

    @Override
    public String generatePopupId() {
        // 현재 날짜를 yyMMdd 형식으로 가져옴
        String datePrefix = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"));
        
        // 해당 날짜의 마지막 시퀀스 번호를 조회
        String lastPopupId = popupDao.selectLastPopupId(datePrefix);
        
        int sequence = 1;
        if (lastPopupId != null && lastPopupId.length() >= 8) {
            // 마지막 2자리가 시퀀스 번호
            sequence = Integer.parseInt(lastPopupId.substring(6)) + 1;
        }
        
        // 시퀀스를 2자리 숫자로 포맷팅 (01, 02, ..., 99)
        return String.format("%s%02d", datePrefix, sequence);
    }
}

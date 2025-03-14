package kr.co.dysnt.framework.common.popup.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Popup {
    private String popupId;      // 팝업의 고유 ID
    private String popupNm;      // 팝업명
    private String popupTy;      // 팝업 유형 (레이어, 윈도우)
    private String popupCn;      // 팝업 내용 (HTML 가능)
    private String popupUrl;     // 팝업에 연결할 URL (윈도우 팝업인 경우)
    private Integer width;       // 팝업 가로 크기 (기본값 400px)
    private Integer height;      // 팝업 세로 크기 (기본값 300px)
    private Integer positionX;   // X 좌표 위치 (기본값 100)
    private Integer positionY;   // Y 좌표 위치 (기본값 100)
    private String useYn;        // 사용여부
    private LocalDateTime inptDt;// 등록일
    private String inptId;       // 등록자
    private String inptIp;       // 등록IP
    private LocalDateTime updtDt;// 수정일
    private String updtId;       // 수정자
    private String updtIp;       // 수정IP
}

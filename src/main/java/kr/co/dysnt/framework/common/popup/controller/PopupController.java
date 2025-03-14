package kr.co.dysnt.framework.common.popup.controller;

import kr.co.dysnt.framework.common.popup.domain.Popup;
import kr.co.dysnt.framework.common.popup.service.PopupService;
import kr.co.dysnt.framework.common.util.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common/popup")
@RequiredArgsConstructor
public class PopupController {

    private final PopupService popupService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getPopupList(@RequestParam Map<String, Object> params) {
        try {
            List<Popup> popupList = popupService.getPopupList(params);
            return ResponseEntity.ok(ApiResponse.successResponse(popupList));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(ApiResponse.errorResponse("팝업 목록 조회 중 오류가 발생했습니다."));
        }
    }

    @GetMapping("/generateId")
    public ResponseEntity<ApiResponse<?>> generatePopupId() {
        try {
            String popupId = popupService.generatePopupId();
            return ResponseEntity.ok(ApiResponse.successResponse(popupId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(ApiResponse.errorResponse("팝업 ID 생성 중 오류가 발생했습니다."));
        }
    }

    @GetMapping("/{popupId}")
    public ResponseEntity<ApiResponse<?>> getPopup(@PathVariable String popupId) {
        try {
            Popup popup = popupService.getPopup(popupId);
            return ResponseEntity.ok(ApiResponse.successResponse(popup));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(ApiResponse.errorResponse("팝업 조회 중 오류가 발생했습니다."));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createPopup(@RequestBody Popup popup) {
        try {
            popupService.createPopup(popup);
            return ResponseEntity.ok(ApiResponse.successWithNoContent());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(ApiResponse.errorResponse("팝업 등록 중 오류가 발생했습니다."));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> updatePopup(@RequestBody Popup popup) {
        try {
            popupService.updatePopup(popup);
            return ResponseEntity.ok(ApiResponse.successWithNoContent());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(ApiResponse.errorResponse("팝업 수정 중 오류가 발생했습니다."));
        }
    }

    @DeleteMapping("/{popupId}")
    public ResponseEntity<ApiResponse<?>> deletePopup(@PathVariable String popupId) {
        try {
            popupService.deletePopup(popupId);
            return ResponseEntity.ok(ApiResponse.successWithNoContent());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(ApiResponse.errorResponse("팝업 삭제 중 오류가 발생했습니다."));
        }
    }
}

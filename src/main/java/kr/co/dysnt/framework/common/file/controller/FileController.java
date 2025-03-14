package kr.co.dysnt.framework.common.file.controller;

import kr.co.dysnt.framework.common.util.api.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/common/file")
public class FileController {

    @GetMapping("/listDirectories")
    public ResponseEntity<ApiResponse<?>> listDirectories(@RequestParam String path) {
        try {
            // 프로젝트 루트 디렉토리 기준으로 경로 설정
            String projectRoot = System.getProperty("user.dir");
            String targetPath = new File(projectRoot).getParent() + File.separator + path;
            File directory = new File(targetPath);

            if (!directory.exists() || !directory.isDirectory()) {
                return ResponseEntity.badRequest()
                    .body(ApiResponse.errorResponse("Invalid directory path"));
            }

            List<String> directories = new ArrayList<>();
            File[] files = directory.listFiles();
            
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && !file.getName().startsWith(".")) {
                        directories.add(file.getName());
                    }
                }
            }

            return ResponseEntity.ok()
                .body(ApiResponse.successResponse(directories));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(ApiResponse.errorResponse("Failed to list directories: " + e.getMessage()));
        }
    }
}

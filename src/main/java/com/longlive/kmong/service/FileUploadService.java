package com.longlive.kmong.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {

    public String upload(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        String fileDir = "C:\\Users\\윤민수\\Downloads\\kmongimage\\"; // 프로젝트 내부의 static/img 디렉토리 경로

        
        try {
            // 디렉토리 생성 (없는 경우)
            File directory = new File(fileDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 파일 저장
            file.transferTo(new File(fileDir + fileName));


        } catch (
                IOException e) {
            e.printStackTrace();

        }
   return fileName;
    }
    }


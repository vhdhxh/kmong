package com.longlive.kmong.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.longlive.kmong.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j

@PropertySource("classpath:config.properties")
@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3 amazonS3;
    private final UserService userService;

    @Value("${s3.bucket}")
    private String bucket;

    public String saveFile(MultipartFile multipartFile , PrincipalDetails principalDetails) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString()+"_"+originalFilename;
        String fileName2 = "image/"+fileName;

        Map<String,String> map = new HashMap<>();
        map.put("email",principalDetails.getDto().getUser_email());
        map.put("image",fileName);
            userService.updateImage(map);
           principalDetails.getDto().setUser_image(fileName);

        amazonS3.putObject(bucket, fileName2, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, fileName2).toString();
    }
}

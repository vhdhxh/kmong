package com.longlive.kmong.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadDTO {
    private boolean uploaded;
    private String fileName;
    private String url;
}

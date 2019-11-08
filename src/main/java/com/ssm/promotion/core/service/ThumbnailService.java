package com.ssm.promotion.core.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

public interface ThumbnailService {
    String generateThumbnail(CommonsMultipartFile file, String realUploadPaht, String newFileName) throws IOException;
}

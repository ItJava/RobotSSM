package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.service.ThumbnailService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

@Service("thumbnailService")
public class ThumbnailServiceImpl implements ThumbnailService {

    //设置节约图的宽度以及高度
    public static final int heigth = 100;
    public static final int width = 100;


    @Override
    public String generateThumbnail(CommonsMultipartFile file, String realUploadPaht, String newFileName) throws IOException {
        // TODO Auto-generated method stub
        ResourceBundle resource = ResourceBundle.getBundle("config");
        String thumbnailPictureFolder = resource.getString("thumbnailPictureFolder");
        //如果目录不存在创建目录
        File uploadFile = new File(realUploadPaht+thumbnailPictureFolder);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        //缩略图保存的绝对地址
        String des = realUploadPaht+thumbnailPictureFolder+"/"+newFileName;
        //按照我给的宽高生成缩略图
        Thumbnails.of(file.getInputStream()).size(width, heigth).toFile(des);
        //返回缩略图地址
        String relative_address = thumbnailPictureFolder+"/" + newFileName;
        return relative_address;
    }
}

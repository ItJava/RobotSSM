package com.ssm.promotion.core.util;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiNiuYunTools {
    @Value("#{ossProperties.thumbnailPictureFolder}")
    private String thumbnailPictureFolder;
    @Value("#{ossProperties.AccessKey}")
    private String accessKey;
    @Value("#{ossProperties.SecretKey}")
    private String secretKey;
    @Value("#{ossProperties.BucketName}")
    private String bucketName;
    @Value("#{ossProperties.QiNiuHeaderAC}")
    private String qiNiuHeaderAC;

    private Auth auth;

    public Auth getAuth() {
        if (auth == null) {
            System.out.println("qiniu akey = " + accessKey);
            System.out.println("qiniu skey = " + secretKey);
            auth = Auth.create(accessKey, secretKey);
        }
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getThumbnailPictureFolder() {
        return thumbnailPictureFolder;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getQiNiuHeaderAC() {
        return qiNiuHeaderAC;
    }
}

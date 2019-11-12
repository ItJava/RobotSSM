package com.ssm.promotion.core.entity.bean.qiniu;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

public class ReturnQiNiuCallBackBean implements Serializable {


  private String key;
  private String hash;
  private String bucket;
  private String fsize;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getFsize() {
        return fsize;
    }

    public void setFsize(String fsize) {
        this.fsize = fsize;
    }
}

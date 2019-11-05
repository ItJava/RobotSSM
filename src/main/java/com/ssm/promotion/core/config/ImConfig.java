package com.ssm.promotion.core.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImConfig {

    @Value("#{imProperties.IM_BASE_URL}")
    private String IM_BASE_URL;

    @Value("#{imProperties.ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("#{imProperties.AUTH_TOKEN}")
    private String AUTH_TOKEN;

    @Value("#{imProperties.APP_ID}")
    private String APP_ID;


    public String getIM_BASE_URL() {
        return IM_BASE_URL;
    }

    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public String getAPP_ID() {
        return APP_ID;
    }
}

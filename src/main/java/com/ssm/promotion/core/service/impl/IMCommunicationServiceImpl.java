package com.ssm.promotion.core.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.ssm.promotion.core.config.ImConfig;
import com.ssm.promotion.core.service.IMCommunicationService;
import com.ssm.promotion.core.util.DateUtil;
import com.ssm.promotion.core.util.MD5Util;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;


@Service("wxImService")
public class IMCommunicationServiceImpl implements IMCommunicationService {


    @Resource
    private ImConfig imConfig;

    @Resource
    private OkHttpClient okHttpClient;

    @Override
    public String createImAccount(String userId, String mobile) {
        String time = DateUtil.getNowUrlTime();
        String url = imConfig.getIM_BASE_URL() + "2015-06-30/Accounts/" + imConfig.getACCOUNT_SID() + "/Clients?sig=" + getSig(time);
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("appId", imConfig.getAPP_ID());
        jsonObject2.put("userId", mobile);
        jsonObject2.put("friendlyName", mobile);
        jsonObject2.put("mobile", mobile);
        jsonObject1.put("client", jsonObject2);
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject1.toJSONString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", getAuthorization(time))
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        String dataResult = "";
        Response response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                dataResult = response.body().string();
                return dataResult;
            } else {
                dataResult = "create im account failed!";
                return dataResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dataResult;
    }

    @Override
    public String dropImAccount(String userId) {

        String time = DateUtil.getNowUrlTime();
        String url = imConfig.getIM_BASE_URL() + "2015-06-30/Accounts/" + imConfig.getACCOUNT_SID() + "/dropClient?sig=" + getSig(time);
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("appId", imConfig.getAPP_ID());
        jsonObject2.put("userId", userId);
        jsonObject1.put("client", jsonObject2);
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject1.toJSONString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", getAuthorization(time))
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);

        String dataResult = "";
        Response response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                dataResult = response.body().string();
                return dataResult;
            } else {
                dataResult = "drop im account failed!";
                return dataResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;
    }

    @Override
    public String queryClientByMobile(String mobile) {

        String time = DateUtil.getNowUrlTime();
        String url = imConfig.getIM_BASE_URL() + "2015-06-30/Accounts/" + imConfig.getACCOUNT_SID() + "/ClientsByMobile?sig=" + getSig(time) + "&appId=" + imConfig.getAPP_ID() + "&mobile=" + mobile;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", getAuthorization(time))
                .build();
        Call call = okHttpClient.newCall(request);

        String dataResult = "";
        Response response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                dataResult = response.body().string();
                //               System.out.println("成功了:" + dataResult);
                return dataResult;
            } else {
                dataResult = "queryClientByMobile im account failed!";
                return dataResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;

    }

    @Override
    public String queryClientByUserid(String userId) {
        String time = DateUtil.getNowUrlTime();
        String url = imConfig.getIM_BASE_URL() + "2015-06-30/Accounts/" + imConfig.getACCOUNT_SID() + "/ClientsByUserId?sig=" + getSig(time) + "&appId=" + imConfig.getAPP_ID() + "&userId=" + userId;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", getAuthorization(time))
                .build();
        Call call = okHttpClient.newCall(request);
        String dataResult = "";
        Response response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                dataResult = response.body().string();
                return dataResult;
            } else {
                dataResult = "queryClientByUserid im account failed!";
                return dataResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResult;

    }


    private String getAuthorization(String time) {
        return Base64.encode((imConfig.getACCOUNT_SID() + ":" + time).getBytes());
    }

    private String getSig(String time) {
        return MD5Util.getMD5Str(imConfig.getACCOUNT_SID() + imConfig.getAUTH_TOKEN() + time).toUpperCase();
    }

}

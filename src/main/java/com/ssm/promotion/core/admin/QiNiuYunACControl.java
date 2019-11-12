package com.ssm.promotion.core.admin;


import com.google.gson.Gson;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.bean.qiniu.ReturnQiNiuCallBackBean;
import com.ssm.promotion.core.service.QiniuyunAcService;
import com.ssm.promotion.core.util.MD5Util;
import com.ssm.promotion.core.util.QiNiuYunTools;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/qiniuyun")
public class QiNiuYunACControl extends BaseController {

    private static final Logger log = Logger.getLogger(QiNiuYunACControl.class);// 日志文件

    @Resource
    private QiniuyunAcService qiniuyunAcService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Result test() {
        return ResultGenerator.genParamerFailResult("-----------qiniuyun----test  测试成功！----------------!");
    }
    @ResponseBody
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public Result test1() {
        return ResultGenerator.genParamerFailResult("-----------qiniuyun----test1  测试成功！----------------!");
    }


    @ResponseBody
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public Result callback(@RequestParam("key") String key,
                           @RequestParam("hash") String hash,
                           @RequestParam("bucket") String bucket,
                           @RequestParam("fsize") String fsize) {

        log.info("key:" + key + "   hash:" + hash + "   bucket:" + bucket + "   fsize:" + fsize);

        Auth auth = QiNiuYunTools.getInstance().getAuth();
        //检查是否为七牛合法的回调请求
      /*  boolean validCallback = auth.isValidCallback(callbackAuthHeader, callbackUrl, callbackBody, callbackBodyType);
        if (validCallback) {
            //继续处理其他业务逻辑
        } else {
            //这是哪里的请求，被劫持，篡改了吧？
        }*/
        ReturnQiNiuCallBackBean returnQiNiuCallBackBean = new ReturnQiNiuCallBackBean();
        returnQiNiuCallBackBean.setKey(key);
        returnQiNiuCallBackBean.setHash(hash);
        returnQiNiuCallBackBean.setBucket(bucket);
        returnQiNiuCallBackBean.setFsize(fsize);
        return ResultGenerator.genSuccessResult(new Gson().toJson(returnQiNiuCallBackBean));
    }


    @ResponseBody
    @RequestMapping(value = "/getConfigToken", method = RequestMethod.POST)
    public Result addEyePwd(@RequestHeader(value = "item") String item,
                            @RequestHeader(value = "accessKey") String mAccessKey,
                            @RequestHeader(value = "secretKey") String mSecretKey) {

       ResourceBundle resource = ResourceBundle.getBundle("config");
      /*  String qiNiuHeaderAC = resource.getString("QiNiuHeaderAC");
        String accessKey = resource.getString("AccessKey");
        String secretKey = resource.getString("SecretKey");*/
        String bucketName = resource.getString("BucketName");

     /*   System.out.println("qiNiuHeaderAC:"+qiNiuHeaderAC);
        System.out.println("accessKey:"+accessKey);
        System.out.println("secretKey:"+secretKey);
        System.out.println("bucketName:"+bucketName);

        if (item.equals(MD5Util.getMD5Str(qiNiuHeaderAC)) ||
                mAccessKey.equals(MD5Util.getMD5Str(accessKey)) ||
                mSecretKey.equals(MD5Util.getMD5Str(secretKey))) return*/ ResultGenerator.genParamerFailResult("请求参数有误!");

        Auth auth = QiNiuYunTools.getInstance().getAuth();
        String uploadToke = auth.uploadToken(bucketName, null, 3600, new StringMap()
                .put("callbackUrl", "http://interface.creazyman.top/RobotSSM/qiniuyun/callback")
                .put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)"));


//        if(qiNiuYunAc==null){  //未通过ac生成token
//
//        }else{
//
//        }
        return ResultGenerator.genParamerFailResult("uploadToke:"+uploadToke);

    }


}

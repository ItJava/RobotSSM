package com.ssm.promotion.core.admin;


import com.google.gson.Gson;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Upfilecall;
import com.ssm.promotion.core.entity.bean.qiniu.ReturnQiNiuCallBackBean;
import com.ssm.promotion.core.service.QiniuyunAcService;
import com.ssm.promotion.core.service.UpfilecallService;
import com.ssm.promotion.core.util.MD5Util;
import com.ssm.promotion.core.util.MUpLoadConfigConstant;
import com.ssm.promotion.core.util.QiNiuYunTools;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/qiniuyun")
public class QiNiuYunACControl extends BaseController implements MUpLoadConfigConstant {

    @Resource
    private QiNiuYunTools qiNiuYunTools;

    private static final Logger log = Logger.getLogger(QiNiuYunACControl.class);// 日志文件


    @Resource
    private UpfilecallService upfilecallService;

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
                           @RequestParam("btype") String btype,
                           @RequestParam("bfrealname") String bfrealname,
                           @RequestParam("fsize") String fsize) throws IOException {
        String callbackAuthHeader = request.getHeader("Authorization");
        log.info("callbackAuthHeader:"+callbackAuthHeader);
        if(callbackAuthHeader==null) return ResultGenerator.genFailResult("鉴权失败,非法请求！....");
        if(!(callbackAuthHeader.contains("QBox")&&callbackAuthHeader.contains(qiNiuYunTools.getAccessKey()))) return ResultGenerator.genFailResult("鉴权失败,非法请求！....");

         if(btype.equals(TYPE_LAUNCHER_PIC_UPLOAD)){
             Upfilecall upfilecall=upfilecallService.sUpfilecallByTypeFileName(btype,bfrealname);
             if(upfilecall==null){
                 Upfilecall upfilecall1=new Upfilecall();
                 upfilecall1.setKey(key);
                 upfilecall1.setBucket(bucket);
                 upfilecall1.setHash(hash);
                 upfilecall1.setFsize(fsize);
                 upfilecall1.setBtype(btype);
                 upfilecall1.setBfrealname(bfrealname);
                 upfilecallService.insert(upfilecall);
             }else{
                 upfilecall.setKey(key);
                 upfilecall.setBucket(bucket);
                 upfilecall.setHash(hash);
                 upfilecall.setFsize(fsize);
                 upfilecall.setBtype(btype);
                 upfilecall.setBfrealname(bfrealname);
                 upfilecallService.updateByPrimaryKey(upfilecall);
             }
             List<Upfilecall> dbUpfilecalls=upfilecallService.selectByType(btype);
           return   ResultGenerator.genSuccessResult(new Gson().toJson(dbUpfilecalls));
         }
        ReturnQiNiuCallBackBean returnQiNiuCallBackBean=new ReturnQiNiuCallBackBean();
        returnQiNiuCallBackBean.setKey(key);
        returnQiNiuCallBackBean.setBucket(bucket);
        returnQiNiuCallBackBean.setHash(hash);
        returnQiNiuCallBackBean.setFsize(fsize);
        returnQiNiuCallBackBean.setBtype(btype);
        returnQiNiuCallBackBean.setBfrealname(bfrealname);
        String  callbodyStr=new Gson().toJson(returnQiNiuCallBackBean);
        log.info("callbodyStr:"+callbodyStr);
        return ResultGenerator.genSuccessResult(new Gson().toJson(returnQiNiuCallBackBean));
    }
    /*
    返回到客户端数据：
    {"data":"{\"key\":\"FuJonSk8mp4tUx9rUDgwky6c3LEE\",\"hash\":\"FuJonSk8mp4tUx9rUDgwky6c3LEE\",\"bucket\":\"ssmrobot1111\",\"fsize\":\"242840\",\"btype\":\"www\",\"bfrealname\":\"111\"}","message":"SUCCESS","resultCode":200}
     */
    @ResponseBody
    @RequestMapping(value = "/getConfigToken", method = RequestMethod.POST)
    public Result getConfigToken(@RequestHeader(value = "uploadfiletokenHeader") String uploadfiletokenHeader) {
        String qiNiuHeaderAC = qiNiuYunTools.getQiNiuHeaderAC();  // "VhoZVNomsN3B4wowoRWduAiHrcVY-AdSZSctnmo7l5Em7OwQno7p3T4dCB2fZnw4oj54b40S2BAvy_2P";
        String bucketName =qiNiuYunTools.getBucketName(); //"ssmrobot1111";
        log.info("qiNiuHeaderAC:" + qiNiuHeaderAC + "  MD5Util.getMD5Str(qiNiuHeaderAC):" + MD5Util.getMD5Str(qiNiuHeaderAC));
        log.info("bucketName:" + bucketName);
         if (!uploadfiletokenHeader.equals(MD5Util.getMD5Str(qiNiuHeaderAC)))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        Auth auth = qiNiuYunTools.getAuth();
        String uploadToke = auth.uploadToken(bucketName, null, 3600, new StringMap()
                .put("callbackUrl", "http://interface.creazyman.top/RobotSSM/qiniuyun/callback")
                .put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)&btype=$(x:btype)&bfrealname=$(x:bfrealname)"));
         return ResultGenerator.genSuccessResult(uploadToke);
    }


}

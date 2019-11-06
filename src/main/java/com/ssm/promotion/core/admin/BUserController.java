package com.ssm.promotion.core.admin;

import com.google.gson.Gson;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Buser;
import com.ssm.promotion.core.entity.MqttUser;
import com.ssm.promotion.core.service.BuserService;
import com.ssm.promotion.core.service.IMCommunicationService;
import com.ssm.promotion.core.service.MqttUserService;
import com.ssm.promotion.core.util.EncryptionUtil;
import com.ssm.promotion.core.util.PhotoUtil;
import com.ssm.promotion.core.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;


@Controller
@RequestMapping("/busers")
public class BUserController extends BaseController {

    private static final Logger log = Logger.getLogger(BUserController.class);// 日志文件


    @Resource
    private BuserService buserService;

    @Resource
    private MqttUserService mqttUserService;

    @Resource
    private IMCommunicationService wxImService;

    //注册[不带图像]
    @RequestMapping(value = "/addUserNoPic", method = RequestMethod.POST)
    @ResponseBody
    public Result addUserNoPic(@RequestParam("userName") String buserName,
                               @RequestParam("pwd") String bPwd,
                               @RequestParam("bUserPhone") String bPhone,
                               @RequestParam("bUserAddress") String bAddress) {
        System.out.println("/busers/addUserNoPic  参数：buserName：" + buserName + " pwd：" + bPwd + " bUserPhone：" + bPhone + " bUserAddress：" + bAddress);
        if (StringUtil.isEmpty(buserName) ||
                StringUtil.isEmpty(bPhone) ||
                StringUtil.isEmpty(bAddress) ||
                StringUtil.isEmpty(bPwd))
            return ResultGenerator.genParamerFailResult("参数：buserName：" + buserName + " pwd：" + bPwd + " bUserPhone：" + bPhone + " bUserAddress：" + bAddress);
        Buser resultUser = buserService.selectBuserByPhone(bPhone);
        if (resultUser != null) return ResultGenerator.genFailResult("用户已经注册！");
     /*
       零时屏蔽
       String pushTokenStr = wxImService.createImAccount(bPhone, bPhone);
        ImCreateResponse imCreateResponse = new Gson().fromJson(pushTokenStr, ImCreateResponse.class);//wxImService.createImAccount(phone, phone);
        if (imCreateResponse == null) {
            AddUserData addUserData = new AddUserData();
            addUserData.setMessage(pushTokenStr);
            return ResultGenerator.genFailResult("第三方license生成有误,请联系后台查错！");
        }*/
        String phoneToken = "test_phoneToken";  //模拟第三方token
        Buser buser = new Buser();
        buser.setUserAddress(bAddress);
        buser.setRevision(0);
        buser.setUserName(buserName);
        //buser.setUserPwd(MD5Util.getMD5Str(bPwd + Constants.ENCODE_EXTRA_PARAMER_ADDED));
        buser.setUserPwd(EncryptionUtil.encryption(bPwd));
        buser.setPhoneToken(phoneToken);
        buser.setUserPhone(bPhone);
        buser.setCreatedTime(new Date());
        buser.setCreatedBy(bPhone);
        buser.setUserPic("");
        buser.setUpdatedBy(buserName);
        buser.setCreatedTime(new Date());
        int bUserResultFlag = buserService.insert(buser);
        String userResultJson = new Gson().toJson(buser);
        if (bUserResultFlag == 0) return ResultGenerator.genFailResult("插入BUser失败！插入数据：" + userResultJson);

        MqttUser mqttUser = new MqttUser();
        //mqttUser.setIsSuperuser(false);
        mqttUser.setCreatetime(new Date());
        mqttUser.setUsername(EncryptionUtil.encryption(bPhone));
        mqttUser.setPassword(EncryptionUtil.encryption(bPhone));
        int mqttResultFlag = mqttUserService.insert(mqttUser);
        if (mqttResultFlag == 0) return ResultGenerator.genFailResult("MqttUser失败！插入数据：" + new Gson().toJson(mqttUser));
        return ResultGenerator.genSuccessResult(userResultJson);
    }


    //注册[带图像]
    @ResponseBody
    @RequestMapping(value = "/addUserSaveFile", method = RequestMethod.POST)
    public Result addUserSaveFile(@RequestParam("userName") String buserName,
                                  @RequestParam("pwd") String bPwd,
                                  @RequestParam("bUserPhone") String bPhone,
                                  @RequestParam("bUserAddress") String bAddress,
                                  @RequestParam("pic") MultipartFile file) {

        System.out.println("/busers/addUserNoPic  参数：buserName：" + buserName + " pwd：" + bPwd + " bUserPhone：" + bPhone + " bUserAddress：" + bAddress);
        if (StringUtil.isEmpty(buserName) ||
                StringUtil.isEmpty(bPhone) ||
                StringUtil.isEmpty(bAddress) ||
                StringUtil.isEmpty(bPwd))
            return ResultGenerator.genParamerFailResult("参数：buserName：" + buserName + " pwd：" + bPwd + " bUserPhone：" + bPhone + " bUserAddress：" + bAddress);
        Buser resultUser = buserService.selectBuserByPhone(bPhone);
        if (resultUser != null) return ResultGenerator.genFailResult("用户已经注册！");
     /*
       零时屏蔽
       String pushTokenStr = wxImService.createImAccount(bPhone, bPhone);
        ImCreateResponse imCreateResponse = new Gson().fromJson(pushTokenStr, ImCreateResponse.class);//wxImService.createImAccount(phone, phone);
        if (imCreateResponse == null) {
            AddUserData addUserData = new AddUserData();
            addUserData.setMessage(pushTokenStr);
            return ResultGenerator.genFailResult("第三方license生成有误,请联系后台查错！");
        }*/

        String photoUrl = PhotoUtil.saveFile(file, bPhone, request);

        String phoneToken = "test_phoneToken";  //模拟第三方token
        Buser buser = new Buser();
        buser.setUserAddress(bAddress);
        buser.setRevision(0);
        buser.setUserName(buserName);
        buser.setUserPwd(EncryptionUtil.encryption(bPwd));
        buser.setPhoneToken(phoneToken);
        buser.setUserPhone(bPhone);
        buser.setCreatedTime(new Date());
        buser.setCreatedBy(bPhone);
        buser.setUserPic(photoUrl);
        buser.setUpdatedBy(buserName);
        buser.setCreatedTime(new Date());
        int bUserResultFlag = buserService.insert(buser);
        String userResultJson = new Gson().toJson(buser);
        if (bUserResultFlag == 0) return ResultGenerator.genFailResult("插入BUser失败！插入数据：" + userResultJson);

        MqttUser mqttUser = new MqttUser();
        //mqttUser.setIsSuperuser(false);
        mqttUser.setCreatetime(new Date());
        mqttUser.setUsername(EncryptionUtil.encryption(bPhone));
        mqttUser.setPassword(EncryptionUtil.encryption(bPwd));
        int mqttResultFlag = mqttUserService.insert(mqttUser);
        if (mqttResultFlag == 0) return ResultGenerator.genFailResult("MqttUser失败！插入数据：" + new Gson().toJson(mqttUser));
        return ResultGenerator.genSuccessResult(userResultJson);
    }


    //登录
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(pwd)) return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);
        if (buser != null) {
            if (buser.getUserPwd().equals(EncryptionUtil.encryption(pwd))) {
                return ResultGenerator.genSuccessResult(new Gson().toJson(buser));
            } else {
                return ResultGenerator.genFailResult("密码不对哦！");
            }
        } else {
            return ResultGenerator.genFailResult("用户还未注册！");
        }
    }

    //找回密码
    @ResponseBody
    @RequestMapping(value = "/findPwdByPhone", method = RequestMethod.POST)
    public Result findPwdByPhone(@RequestParam("phone") String phone) {
        if (StringUtil.isEmpty(phone)) return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);
        if (buser == null) return ResultGenerator.genFailResult("请检测手机号是否正确,该手机号未注册！");
        else
            buser.setUserPwd(EncryptionUtil.decrypt(buser.getUserPwd()));
        return ResultGenerator.genSuccessResult(new Gson().toJson(buser));
    }


    @ResponseBody
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Result upDatePwd(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(pwd)) return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);

        if (buser == null) return ResultGenerator.genFailResult("请检测手机号是否正确,该手机号未注册！");
        else
            buser.setUserPwd(EncryptionUtil.encryption(pwd));
        buserService.updateByPrimaryKeySelective(buser);
        return ResultGenerator.genSuccessResult(new Gson().toJson(buser));

    }


    @ResponseBody
    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
    public Result upDateUserName(@RequestParam("phone") String phone, @RequestParam("userName") String buserName) {
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(buserName))
            return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);
        if (buser == null) return ResultGenerator.genFailResult("请检测手机号是否正确,该手机号未注册！");
        else
            buser.setUserName(buserName);
        buserService.updateByPrimaryKeySelective(buser);
        return ResultGenerator.genSuccessResult(new Gson().toJson(buser));
    }


    @ResponseBody
    @RequestMapping(value = "/updateToken", method = RequestMethod.POST)
    public Result upDatePhoneToken(@RequestParam("phone") String phone, @RequestParam("phoneToken") String phoneToken) {
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(phoneToken))
            return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);
        if (buser == null) return ResultGenerator.genFailResult("请检测手机号是否正确,该手机号未注册！");
        else
            buser.setPhoneToken(phoneToken);
        buserService.updateByPrimaryKeySelective(buser);
        return ResultGenerator.genSuccessResult(new Gson().toJson(buser));
    }


    @ResponseBody
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public Result upDatePic(@RequestParam("phone") String phone, @RequestParam("pic") MultipartFile file) {
        if (StringUtil.isEmpty(phone)) return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);
        if (buser == null) return ResultGenerator.genFailResult("请检测手机号是否正确,该手机号未注册！");
        else {
            String photoUrl = PhotoUtil.saveFile(file, phone, request);
            buser.setUserPhone(photoUrl);
        }
        buserService.updateByPrimaryKeySelective(buser);
        return ResultGenerator.genSuccessResult(new Gson().toJson(buser));
    }

    @ResponseBody
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public Result upDateAddress(@RequestParam("phone") String phone, @RequestParam("address") String address) {
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(address))
            return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);
        if (buser == null) return ResultGenerator.genFailResult("请检测手机号是否正确,该手机号未注册！");
        else
            buser.setUserAddress(address);
        buserService.updateByPrimaryKeySelective(buser);
        return ResultGenerator.genSuccessResult(new Gson().toJson(buser));
    }
    
}

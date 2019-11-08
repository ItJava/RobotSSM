package com.ssm.promotion.core.admin;


import com.google.gson.Gson;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Device;
import com.ssm.promotion.core.entity.Eyeprotect;
import com.ssm.promotion.core.entity.Eyepwd;
import com.ssm.promotion.core.service.*;
import com.ssm.promotion.core.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/eyepwd")
public class EyePwdControl extends BaseController {

    private static final Logger log = Logger.getLogger(EyePwdControl.class);// 日志文件

    @Resource
    private DeviceService deviceService;

    @Resource
    private IMCommunicationService imCommunicationService;

    @Resource
    private PhonetokenService phonetokenService;

    @Resource
    private MqttUserService mqttUserService;

    @Resource
    private DevicebindService devicebindService;

    @Resource
    private BuserService buserService;

    @Resource
    private ChatService chatService;

    @Resource
    private EyeprotectService eyeprotectService;

    @Resource
    private EyepwdService eyepwdService;

    @ResponseBody
    @RequestMapping(value = "/addEyePwd", method = RequestMethod.POST)
    public Result addEyePwd(@RequestParam("deviceId") String deviceID,
                             @RequestParam("createdBy") String createdBy,
                             @RequestParam("eyepwd") String eyepwd) {
        if (StringUtil.isEmpty(deviceID) ||
                StringUtil.isEmpty(createdBy)||
                StringUtil.isEmpty(eyepwd)
        )return ResultGenerator.genParamerFailResult("请求参数有误!");

        Device device = deviceService.selectByPrimaryKey(deviceID);
        if(device==null)ResultGenerator.genParamerFailResult("设备暂未注册，请先注册设备!");
        Eyepwd eyePwd = new Eyepwd();
        eyePwd.setDeviceId(deviceID);
        eyePwd.setCreatedBy(createdBy);
        eyePwd.setCreatedTime(new Date());
        eyePwd.setUpdatedBy(createdBy);
        eyePwd.setUpdatedTime(new Date());
        eyePwd.setRevision(0);
        eyePwd.setEyePwd(eyepwd);
        eyepwdService.insert(eyePwd);
        return ResultGenerator.genSuccessResult(new Gson().toJson(eyePwd));
    }


    @ResponseBody
    @RequestMapping(value = "/getEyePwd", method = RequestMethod.POST)
    public Result getEyePwd(@RequestParam("deviceId") String deviceID) {
        if (StringUtil.isEmpty(deviceID)
        )return ResultGenerator.genParamerFailResult("请求参数有误!");
        Device device = deviceService.selectByPrimaryKey(deviceID);
        if(device==null)ResultGenerator.genParamerFailResult("设备暂未注册，请先注册设备!");
        Eyepwd eyepwd =  eyepwdService.selectByPrimaryKey(deviceID);
        if(eyepwd==null) return ResultGenerator.genParamerFailResult("家长密码不存在,您还没有设置呢!");
        else return ResultGenerator.genSuccessResult(new Gson().toJson(eyepwd));
    }

    @ResponseBody
    @RequestMapping(value = "/updateEyePwd", method = RequestMethod.POST)
    public Result updateEyePwd(@RequestParam("deviceId") String deviceID,
                               @RequestParam("updateBy") String updateby,
                               @RequestParam("eyepwd") String eyepwd) {
        if (StringUtil.isEmpty(deviceID) ||
                StringUtil.isEmpty(updateby) ||
                StringUtil.isEmpty(eyepwd)
        ) return ResultGenerator.genParamerFailResult("请求参数有误!");
        if(eyepwd==null) return ResultGenerator.genParamerFailResult("家长密码不存在,您还没有设置呢!");
        Device device = deviceService.selectByPrimaryKey(deviceID);
        if(device==null)ResultGenerator.genParamerFailResult("设备暂未注册，请先注册设备!");
        Eyepwd eyepwdBean =  eyepwdService.selectByPrimaryKey(deviceID);
        if(eyepwdBean==null){
            log.info("updateEyePwd 更新护眼家长密码，但是密码不存在，就直接更新,如果这样，说明逻辑问题出现了！");
            Eyepwd eyeAddPwd = new Eyepwd();
            eyeAddPwd.setDeviceId(deviceID);
            eyeAddPwd.setCreatedBy(updateby);
            eyeAddPwd.setCreatedTime(new Date());
            eyeAddPwd.setUpdatedBy(updateby);
            eyeAddPwd.setEyePwd(eyepwd);
            eyeAddPwd.setUpdatedTime(new Date());
            eyeAddPwd.setRevision(0);
            eyepwdService.insert(eyeAddPwd);
            return ResultGenerator.genSuccessResult(new Gson().toJson(eyeAddPwd));
        }

        eyepwdBean.setDeviceId(deviceID);
        eyepwdBean.setEyePwd(eyepwd);
        eyepwdBean.setUpdatedBy(updateby);
        eyepwdBean.setUpdatedTime(new Date());
        eyepwdService.updateByPrimaryKey(eyepwdBean);
        return ResultGenerator.genSuccessResult(new Gson().toJson(eyepwdBean));
    }


    @ResponseBody
    @RequestMapping(value = "/delEyePwd", method = RequestMethod.POST)
    public Result delEyePwd(@RequestParam("deviceId") String deviceID) {
        if (StringUtil.isEmpty(deviceID)
        )return ResultGenerator.genParamerFailResult("请求参数有误!");
         Eyepwd eyepwd =  eyepwdService.selectByPrimaryKey(deviceID);
        if(eyepwd==null) return ResultGenerator.genParamerFailResult("家长密码不存在,您还没有设置呢!");
        eyepwdService.deleteByPrimaryKey(deviceID);
        return ResultGenerator.genSuccessResult(new Gson().toJson(eyepwd));
    }


}

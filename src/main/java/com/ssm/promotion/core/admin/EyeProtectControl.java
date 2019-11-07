package com.ssm.promotion.core.admin;


import com.google.gson.Gson;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Chat;
import com.ssm.promotion.core.entity.Eyeprotect;
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
@RequestMapping("/eyeprotect")
public class EyeProtectControl extends BaseController {

    private static final Logger log = Logger.getLogger(EyeProtectControl.class);// 日志文件

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

    @ResponseBody
    @RequestMapping(value = "/addTimeAlarm", method = RequestMethod.POST)
    public Result addTimeAlarm(@RequestParam("deviceId") String robotId,
                             @RequestParam("createdBy") String createdBy,
                             @RequestParam("eyeTip") int eyeTip,
                             @RequestParam("timeBegin") int timeBegin,
                             @RequestParam("timeEnd") int timeEnd,
                             @RequestParam("timeLength") int timeLength,
                             @RequestParam("timeLimit") int timeLimit,
                             @RequestParam("workTimeBegin") int workTimeBegin,
                             @RequestParam("workTimeEng") int workTimeEng,
                             @RequestParam("workTimeLength") int workTimeLength,
                             @RequestParam("workTimeLimit") int workTimeLimit) {
        if (StringUtil.isEmpty(robotId) ||
                StringUtil.isEmpty(createdBy)
        )return ResultGenerator.genParamerFailResult("请求参数有误!");
        Eyeprotect eyeprotect = new Eyeprotect();
        eyeprotect.setRobotId(robotId);
        eyeprotect.setCreatedBy(createdBy);
        eyeprotect.setUpdatedBy(createdBy);
        eyeprotect.setUpdatedTime(new Date());
        eyeprotect.setCreatedTime(new Date());
        eyeprotect.setEyeTip(eyeTip);
        eyeprotect.setRevision(0);
        eyeprotect.setTimeBegin(timeBegin);
        eyeprotect.setTimeEnd(timeEnd);
        eyeprotect.setTimeLength(timeLength);
        eyeprotect.setTimeLimit(timeLimit);

        eyeprotect.setWorkTimeBegin(workTimeBegin);
        eyeprotect.setWorkTimeEng(workTimeEng);
        eyeprotect.setWorkTimeLength(workTimeLength);
        eyeprotect.setWorkTimeLimit(workTimeLimit);

        eyeprotectService.insert(eyeprotect);
        return ResultGenerator.genSuccessResult(new Gson().toJson(eyeprotect));
    }



    @ResponseBody
    @RequestMapping(value = "/getTimeAlarm", method = RequestMethod.POST)
    public Result getTimeAlarm(@RequestParam("deviceId") String robotId) {
        if (StringUtil.isEmpty(robotId)
        )return ResultGenerator.genParamerFailResult("请求参数有误!");
        Eyeprotect eyeprotect =  eyeprotectService.selectByPrimaryKey(robotId);
         return ResultGenerator.genSuccessResult(new Gson().toJson(eyeprotect));
    }




    @ResponseBody
    @RequestMapping(value = "/updateTimeAlarm", method = RequestMethod.POST)
    public Result updateTimeAlarm(@RequestParam("deviceId") String robotId,
                                  @RequestParam("createdBy") String createdBy,
                                  @RequestParam("eyeTip") int eyeTip,
                                  @RequestParam("timeBegin") int timeBegin,
                                  @RequestParam("timeEnd") int timeEnd,
                                  @RequestParam("timeLength") int timeLength,
                                  @RequestParam("timeLimit") int timeLimit,
                                  @RequestParam("workTimeBegin") int workTimeBegin,
                                  @RequestParam("workTimeEng") int workTimeEng,
                                  @RequestParam("workTimeLength") int workTimeLength,
                                  @RequestParam("workTimeLimit") int workTimeLimit) {
        if (StringUtil.isEmpty(robotId) ||
                StringUtil.isEmpty(createdBy)
        )return ResultGenerator.genParamerFailResult("请求参数有误!");
        Eyeprotect eyeprotect = new Eyeprotect();
        eyeprotect.setRobotId(robotId);
        eyeprotect.setCreatedBy(createdBy);
        eyeprotect.setUpdatedBy(createdBy);
        eyeprotect.setUpdatedTime(new Date());
        eyeprotect.setCreatedTime(new Date());
        eyeprotect.setEyeTip(eyeTip);
        eyeprotect.setRevision(0);
        eyeprotect.setTimeBegin(timeBegin);
        eyeprotect.setTimeEnd(timeEnd);
        eyeprotect.setTimeLength(timeLength);
        eyeprotect.setTimeLimit(timeLimit);

        eyeprotect.setWorkTimeBegin(workTimeBegin);
        eyeprotect.setWorkTimeEng(workTimeEng);
        eyeprotect.setWorkTimeLength(workTimeLength);
        eyeprotect.setWorkTimeLimit(workTimeLimit);
        int insertFlag =  eyeprotectService.upDateEyeprotectAlarm(eyeprotect);
         return ResultGenerator.genSuccessResult(new Gson().toJson(eyeprotect));
    }







}

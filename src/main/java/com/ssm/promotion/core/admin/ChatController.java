package com.ssm.promotion.core.admin;

import com.google.gson.Gson;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Chat;
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
@RequestMapping("/chat")
public class ChatController extends BaseController {

    private static final Logger log = Logger.getLogger(ChatController.class);// 日志文件


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


    @ResponseBody
    @RequestMapping(value = "/addChatMsg", method = RequestMethod.POST)
    public Result addChatMsg(@RequestParam("deviceId") String deviceId,
                             //@RequestParam("chatTime") Date chatTime,
                             @RequestParam("chatQuestion") String chatQuestion,
                             @RequestParam("chatAnswer") String chatAnswer,
                             @RequestParam("chatType") String chatType) {
        if (StringUtil.isEmpty(deviceId) ||
                StringUtil.isEmpty(chatQuestion) ||
                StringUtil.isEmpty(chatAnswer) ||
                StringUtil.isEmpty(chatType)) return ResultGenerator.genParamerFailResult("请求参数有误!");
        Chat chat = new Chat();
        chat.setDeviceId(deviceId);
        chat.setChatAnswer(chatAnswer);
        chat.setChatQuestion(chatQuestion);
        chat.setChatTime(new Date());
        chat.setChatType(chatType);
        chatService.insert(chat);
        return ResultGenerator.genSuccessResult(new Gson().toJson(chat));
    }


 /*   @ResponseBody
    @RequestMapping(value = "/addChatMsg", method = RequestMethod.POST)
    public Result addChatMsg(@RequestParam("deviceId") String deviceId, int start, int limit) {
        if (StringUtil.isEmpty(deviceId) ||
                StringUtil.isEmpty(chatQuestion) ||
                StringUtil.isEmpty(chatAnswer) ||
                StringUtil.isEmpty(chatType)) return ResultGenerator.genParamerFailResult("请求参数有误!");

        
        return ResultGenerator.genSuccessResult(new Gson().toJson(chat));

    }*/


}

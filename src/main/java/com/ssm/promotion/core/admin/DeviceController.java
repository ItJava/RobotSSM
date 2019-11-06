package com.ssm.promotion.core.admin;

import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Device;
import com.ssm.promotion.core.service.DeviceService;
import com.ssm.promotion.core.service.IMCommunicationService;
import com.ssm.promotion.core.service.MqttUserService;
import com.ssm.promotion.core.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {

    private static final Logger log = Logger.getLogger(DeviceController.class);// 日志文件



    @Resource
    private DeviceService deviceService;

    @Resource
    private IMCommunicationService imCommunicationService;



    //设备端绑定、获取当前绑定信息
    @ResponseBody
    @RequestMapping(value = "/getDeviceMsg", method = RequestMethod.POST)
    public Result getDeviceMsg(@RequestParam("deviceId") String deviceId) {
        if (StringUtil.isEmpty(deviceId)) return ResultGenerator.genParamerFailResult("请求参数有误!");

        Device device=deviceService.selectByPrimaryKey(deviceId);



       /* Device device = deviceService.getByDeviceIdAndType(deviceId, type);
        LinYunLicense linYunLicense = linYunLincenceService.getByDeviceIdAndType(deviceId, type);

        if(linYunLicense==null){
            ReturnDeviceBean registDeviceBean = new ReturnDeviceBean();
            return getClientMessage(registDeviceBean, ResultCode.PARAMETER_INDEED, "parameters error");
        }

        if (device != null) {
            System.out.println("设备端已经注册,此时候返回数据给设备端！");
            ReturnDeviceBean returnDeviceBean = new ReturnDeviceBean(deviceId, linYunLicense.getLinyunlincense(),
                    device.getDeviceName(),"",0);
            return getClientMessage(returnDeviceBean, ResultCode.SUCCESS, "Success");
        }
        mqttUserService.save(new MqttUser(deviceId, deviceId, new Date()));
        deviceService.save(new Device(type, deviceId, new Date(), new Date(), "机器人"));
        ReturnDeviceBean returnDeviceBean = new ReturnDeviceBean(deviceId, linYunLicense.getLinyunlincense(),
                "机器人","",0);
        return getClientMessage(returnDeviceBean, ResultCode.SUCCESS, "Success");*/

        return ResultGenerator.genParamerFailResult("请求参数有误!");

    }


}

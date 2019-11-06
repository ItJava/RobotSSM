package com.ssm.promotion.core.admin;

import com.google.gson.Gson;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.*;
import com.ssm.promotion.core.entity.bean.device.ReturnBindDeviceMsg;
import com.ssm.promotion.core.entity.bean.device.ReturnDeviceBean;
import com.ssm.promotion.core.service.*;
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
import java.util.List;


@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {

    private static final Logger log = Logger.getLogger(DeviceController.class);// 日志文件


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


    //设备端绑定、获取当前绑定信息
    @ResponseBody
    @RequestMapping(value = "/getDeviceMsg", method = RequestMethod.POST)
    public Result getDeviceMsg(@RequestParam("deviceId") String deviceId) {
        if (StringUtil.isEmpty(deviceId)) return ResultGenerator.genParamerFailResult("请求参数有误!");

        Device device = deviceService.selectByPrimaryKey(deviceId);
        Phonetoken phonetoken = phonetokenService.selectByDeviceId(deviceId);

        if (phonetoken == null) return ResultGenerator.genParamerFailResult("服务器暂未部署通话license!");
        if (device != null) {
            ReturnDeviceBean returnDeviceBean = new ReturnDeviceBean();
            returnDeviceBean.setBirthDay(device.getCreatedTime());
            returnDeviceBean.setDeviceId(device.getDeviceId());
            returnDeviceBean.setDeviceName(device.getDeviceName());
            returnDeviceBean.setDevicePic(device.getDevicePic());
            returnDeviceBean.setPhoneLic(phonetoken.getPhoneToken());
            return ResultGenerator.genSuccessResult(new Gson().toJson(returnDeviceBean));
        }
        MqttUser mqttUser = new MqttUser();
        mqttUser.setCreatetime(new Date());
        mqttUser.setUsername(EncryptionUtil.encryption(deviceId));
        mqttUser.setPassword(EncryptionUtil.encryption(deviceId));
        int mqttResultFlag = mqttUserService.insert(mqttUser);
        if (mqttResultFlag == 0) return ResultGenerator.genFailResult("MqttUser失败!" + new Gson().toJson(mqttUser));

        Device deviceSave = new Device();
        deviceSave.setCreatedBy(deviceId);
        deviceSave.setCreatedTime(new Date());
        deviceSave.setDeviceId(deviceId);
        deviceSave.setDeviceName("小万");
        deviceSave.setDevicePic("");
        deviceSave.setUpdatedBy(deviceId);
        deviceSave.setUpdatedTime(new Date());
        deviceSave.setRevision(0);
        int deviceSaveFlag = deviceService.insert(deviceSave);
        if (deviceSaveFlag == 0)
            return ResultGenerator.genFailResult("deviceSaveFlag失败!" + new Gson().toJson(deviceSave));

        ReturnDeviceBean returnDeviceBean = new ReturnDeviceBean();
        returnDeviceBean.setBirthDay(deviceSave.getCreatedTime());
        returnDeviceBean.setDeviceId(deviceSave.getDeviceId());
        returnDeviceBean.setDeviceName(deviceSave.getDeviceName());
        returnDeviceBean.setDevicePic(deviceSave.getDevicePic());
        returnDeviceBean.setPhoneLic(phonetoken.getPhoneToken());
        return ResultGenerator.genSuccessResult(returnDeviceBean);
    }


    //设备端绑定、获取当前绑定信息
    @ResponseBody
    @RequestMapping(value = "/updateDeviceName", method = RequestMethod.POST)
    public Result getDeviceMsg(@RequestParam("deviceId") String deviceId, @RequestParam("deviceName") String deviceName) {
        if (StringUtil.isEmpty(deviceId) || StringUtil.isEmpty(deviceId))
            return ResultGenerator.genParamerFailResult("请求参数有误!");


        Device device = deviceService.selectByPrimaryKey(deviceId);
        if (device == null) return ResultGenerator.genParamerFailResult("请求参数有误!");
        device.setDeviceName(deviceName);
        deviceService.updateByPrimaryKeySelective(device);
        Phonetoken phonetoken = phonetokenService.selectByDeviceId(deviceId);
        if (phonetoken == null) return ResultGenerator.genParamerFailResult("服务器暂未部署通话license!");
        ReturnDeviceBean returnDeviceBean = new ReturnDeviceBean();
        returnDeviceBean.setBirthDay(device.getCreatedTime());
        returnDeviceBean.setDeviceId(device.getDeviceId());
        returnDeviceBean.setDeviceName(device.getDeviceName());
        returnDeviceBean.setDevicePic(device.getDevicePic());
        returnDeviceBean.setPhoneLic(phonetoken.getPhoneToken());
        return ResultGenerator.genSuccessResult(new Gson().toJson(returnDeviceBean));
    }


    //设备端绑定、获取当前绑定信息
    @ResponseBody
    @RequestMapping(value = "/updateDevicePic", method = RequestMethod.POST)
    public Result updateDevicePic(@RequestParam("deviceId") String deviceId,
                                  @RequestParam("pic") MultipartFile file) {

        if (StringUtil.isEmpty(deviceId) || StringUtil.isEmpty(deviceId))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        Device device = deviceService.selectByPrimaryKey(deviceId);
        if (device == null) return ResultGenerator.genParamerFailResult("请求参数有误!");
        String photoUrl = PhotoUtil.saveFile(file, deviceId, request);
        device.setDevicePic(photoUrl);
        deviceService.updateByPrimaryKeySelective(device);
        Phonetoken phonetoken = phonetokenService.selectByDeviceId(deviceId);
        if (phonetoken == null) return ResultGenerator.genParamerFailResult("服务器暂未部署通话license!");
        ReturnDeviceBean returnDeviceBean = new ReturnDeviceBean();
        returnDeviceBean.setBirthDay(device.getCreatedTime());
        returnDeviceBean.setDeviceId(device.getDeviceId());
        returnDeviceBean.setDeviceName(device.getDeviceName());
        returnDeviceBean.setDevicePic(device.getDevicePic());
        returnDeviceBean.setPhoneLic(phonetoken.getPhoneToken());
        return ResultGenerator.genSuccessResult(new Gson().toJson(returnDeviceBean));
    }

    //设备端绑定、获取当前绑定信息
    @ResponseBody
    @RequestMapping(value = "/updateBirThday", method = RequestMethod.POST)
    public Result updateDevicePic(@RequestParam("deviceId") String deviceId,
                                  @RequestParam("birthdate") Date birthdate) {
        if (StringUtil.isEmpty(deviceId) || StringUtil.isEmpty(deviceId))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        Device device = deviceService.selectByPrimaryKey(deviceId);
        if (device == null) return ResultGenerator.genParamerFailResult("请求参数有误!");
        device.setCreatedTime(birthdate);
        deviceService.updateByPrimaryKeySelective(device);
        Phonetoken phonetoken = phonetokenService.selectByDeviceId(deviceId);
        if (phonetoken == null) return ResultGenerator.genParamerFailResult("服务器暂未部署通话license!");
        ReturnDeviceBean returnDeviceBean = new ReturnDeviceBean();
        returnDeviceBean.setBirthDay(device.getCreatedTime());
        returnDeviceBean.setDeviceId(device.getDeviceId());
        returnDeviceBean.setDeviceName(device.getDeviceName());
        returnDeviceBean.setDevicePic(device.getDevicePic());
        returnDeviceBean.setPhoneLic(phonetoken.getPhoneToken());
        return ResultGenerator.genSuccessResult(new Gson().toJson(returnDeviceBean));
    }


    //List<Devicebind> selectByPhone(String phone);


    @ResponseBody
    @RequestMapping(value = "/getDeviceBindByPhone", method = RequestMethod.POST)
    public Result getDeviceBindByPhone(@RequestParam("phone") String phone) {
        if (StringUtil.isEmpty(phone))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        List<Devicebind> devicebinds = devicebindService.selectByPhone(phone);
        return ResultGenerator.genSuccessResult(new Gson().toJson(devicebinds));
    }

    @ResponseBody
    @RequestMapping(value = "/getDeviceBindByDevice", method = RequestMethod.POST)
    public Result getDeviceBindByDeviceId(@RequestParam("deviceId") String deviceId) {
        if (StringUtil.isEmpty(deviceId))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        List<Devicebind> devicebinds = devicebindService.selectByDeviceId(deviceId);
        return ResultGenerator.genSuccessResult(new Gson().toJson(devicebinds));
    }


    @ResponseBody
    @RequestMapping(value = "/bindDevice", method = RequestMethod.POST)
    public Result bindDevice(@RequestParam("deviceId") String deviceId,
                             @RequestParam("phone") String phone) {
        if (StringUtil.isEmpty(deviceId) || StringUtil.isEmpty(phone))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        Device device = deviceService.selectByPrimaryKey(deviceId);
        Buser buser = buserService.selectBuserByPhone(phone);

        if (device == null) return ResultGenerator.genParamerFailResult("设备未注册");
        if (buser == null) return ResultGenerator.genParamerFailResult("用户未注册");

        Devicebind devicebind = devicebindService.selectByPhoneAndDevice(phone, deviceId);
        if (devicebind != null) return ResultGenerator.genParamerFailResult("您已经绑定该设备");
        Devicebind devicebind1 = new Devicebind();
        devicebind1.setDeviceId(deviceId);
        devicebind1.setPhone(phone);
        devicebind1.setBindTime(new Date());
        devicebindService.insert(devicebind1);
        ReturnBindDeviceMsg returnBindDeviceMsg = new ReturnBindDeviceMsg();
        returnBindDeviceMsg.setClientId(phone);
        returnBindDeviceMsg.setDeviceBirthDate(device.getCreatedTime());
        returnBindDeviceMsg.setDeviceId(deviceId);
        returnBindDeviceMsg.setDeviceName(device.getDeviceName());
        returnBindDeviceMsg.setDevicePic(device.getDevicePic());
        return ResultGenerator.genSuccessResult(new Gson().toJson(returnBindDeviceMsg));
    }


    @ResponseBody
    @RequestMapping(value = "/unBindDevice", method = RequestMethod.POST)
    public Result unBindDevice(@RequestParam("deviceId") String deviceId,
                               @RequestParam("phone") String phone) {
        if (StringUtil.isEmpty(deviceId) || StringUtil.isEmpty(phone))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        Device device = deviceService.selectByPrimaryKey(deviceId);
        Buser buser = buserService.selectBuserByPhone(phone);
        if (device == null) return ResultGenerator.genParamerFailResult("设备未注册");
        if (buser == null) return ResultGenerator.genParamerFailResult("用户未注册");
        Devicebind devicebind = devicebindService.selectByPhoneAndDevice(phone, deviceId);
        if (devicebind == null) return ResultGenerator.genParamerFailResult("该账号未绑定设备,请检查手机号和设备ID!");
        int delFlag = devicebindService.deletBindDeviceByDeviceIdAndPhone(phone, deviceId);
        if (delFlag == 0) return ResultGenerator.genParamerFailResult("解绑失败！");
        return ResultGenerator.genSuccessResult(new Gson().toJson(devicebind));
    }

    @ResponseBody
    @RequestMapping(value = "/unBindDeviceAll", method = RequestMethod.POST)
    public Result unBindDeviceAllByDeviceId(@RequestParam("deviceId") String deviceId) {
        if (StringUtil.isEmpty(deviceId))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        Device device = deviceService.selectByPrimaryKey(deviceId);
        if (device == null) return ResultGenerator.genParamerFailResult("设备未注册");
        List<Devicebind> devicebinds = devicebindService.selectByDeviceId(deviceId);
        if (devicebinds.size() == 0) return ResultGenerator.genParamerFailResult("该设备未被绑定,请检查手机号和设备ID!");
        int delFlag = devicebindService.deletBindDeviceAllByDeviceId(deviceId);
        System.out.println("delFlag:" + delFlag);
        return ResultGenerator.genSuccessResult(new Gson().toJson(devicebinds));
    }

    @ResponseBody
    @RequestMapping(value = "/unBindDeviceAll", method = RequestMethod.POST)
    public Result unBindDeviceAllByPhone(@RequestParam("phone") String phone) {
        if (StringUtil.isEmpty(phone))
            return ResultGenerator.genParamerFailResult("请求参数有误!");
        Buser buser = buserService.selectBuserByPhone(phone);
        if (buser == null) return ResultGenerator.genParamerFailResult("该用户未注册");
        List<Devicebind> devicebinds = devicebindService.selectByPhone(phone);
        if (devicebinds.size() == 0) return ResultGenerator.genParamerFailResult("该账号未绑定设备,请检查手机号和设备ID!");
        int delFlag = devicebindService.deletBindDeviceAllByPhone(phone);
        System.out.println("delFlag:" + delFlag);
        return ResultGenerator.genSuccessResult(new Gson().toJson(devicebinds));
    }



}

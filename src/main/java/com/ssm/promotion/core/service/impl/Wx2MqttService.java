package com.ssm.promotion.core.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.iot.handler.publisher.UserGetPublisher;
import com.iot.handler.publisher.UserSetPublisher;
import com.iot.mqtt.config.Topic;
import com.iot.mqtt.config.Wx2MqttConfig;
import com.iot.wx.bean.BothSyncResponse;
import com.iot.wx.bean.WxBaseMsg;
import com.ssm.promotion.core.service.impl.mqtt.BindMasterCmd;
import com.ssm.promotion.core.service.impl.mqtt.UnBindDeviceCmd;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/*import com.wanxiang.iot.handler.publisher.UserGetPublisher;
import com.wanxiang.iot.handler.publisher.UserSetPublisher;
import com.wanxiang.iot.mqtt.config.Topic;
import com.wanxiang.iot.mqtt.config.Wx2MqttConfig;
import com.wanxiang.iot.wx.bean.BothSyncResponse;
import com.wanxiang.iot.wx.bean.WxBaseMsg;
import com.wanxiang.robot.business.mqtt.CommandFactory;
import com.wanxiang.robot.business.mqtt.MqttBaseCmd;
import com.wanxiang.robot.business.mqtt.imp.BindAuthorizationMasterCmd;
import com.wanxiang.robot.business.mqtt.imp.BindMasterCmd;
import com.wanxiang.robot.business.mqtt.imp.UnBindDeviceCmd;
import com.wanxiang.robot.service.IDeviceService;
import com.wanxiang.robot.service.IEquipmentService;
import com.wanxiang.robot.service.IMqttService;
import com.wanxiang.robot.service.IUserService;*/

/*

@Service("wx2MqttService")
public class Wx2MqttService implements MqttCallback, IMqttService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private MqttClient client;


    @Resource
    private IEquipmentService equipmentService;

    @Resource
    private IUserService userService;

    @Resource
    private IDeviceService deviceService;


    @Autowired
    private Wx2MqttConfig wx2MqttConfig;
    private MqttConnectOptions connectOptions = null;

    @PostConstruct
    public void init() {
        logger.info("初始化Wx2MqttService");
        try {
            client = new MqttClient(wx2MqttConfig.getHost() + ":" + wx2MqttConfig.getPort(), wx2MqttConfig.getClientId(), new MemoryPersistence());
            reConnect();
            initSetService();

        } catch (MqttException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }

    }

    private void initSetService() {

        BindMasterCmd.getInstance().setServicce(this, equipmentService, userService, deviceService);
        BindAuthorizationMasterCmd.getInstance().setServicce(this, equipmentService, userService, deviceService);
        UnBindDeviceCmd.getInstance().setServicce(this, equipmentService, userService, deviceService);

    }

    private void subscribe() throws MqttException {
        //订阅消息
        logger.info("初始订阅消息");
        System.out.println("初始订阅消息");
        client.subscribe(Topic.TOPICS, Topic.QOS);
    }

    public void reConnect() {
        try {
            connect();
            client.setCallback(this);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private MqttConnectOptions getConnectOptions() {
        if (connectOptions == null) {
            connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setUserName(wx2MqttConfig.getUserName());
            connectOptions.setPassword(wx2MqttConfig.getPassWord().toCharArray());

            System.out.println("得到当前的账户  密码 参数：" + wx2MqttConfig.getUserName() + "    " + wx2MqttConfig.getPassWord());
            // 设置超时时间 TODO 这两个参数设入properties中
            connectOptions.setConnectionTimeout(20);
            // 设置会话心跳时间
            connectOptions.setKeepAliveInterval(20);
        }
        return connectOptions;
    }

    private void connect() throws MqttException {
        //阻塞在此直到连上或超时没连上


        // 调试模式先屏蔽
        logger.info("阻塞在此直到连上或超时没连上");
        System.out.println("阻塞在此直到连上或超时没连上");
        client.connect(getConnectOptions());
        subscribe();
        logger.info("阻塞完毕:Connected=" + client.isConnected());
        System.out.println("阻塞在此直到连上或超时没连上" + client.isConnected());


    }

    // 连接丢失后，一般在这里面进行重连
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("  连接断开了：  connectionLost");
        throwable.printStackTrace();
        //       System.out.println("----------->" + throwable.getMessage());
//        logger.info("连接断开");
        logger.info("连接断开，正在重连");
        reConnect();


    }


    // subscribe后得到的消息会执行到这里面
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        //       System.out.println("messageArrived:" + topic + "      :" + new String(mqttMessage.getPayload()));

        logger.error("messageArrived:" + topic + "      :" + new String(mqttMessage.getPayload()));
        String dataTemp3 = new String(mqttMessage.getPayload(), "UTF-8");   //Linux 服务器代码
        if (StringUtils.isEmpty(topic)) {
            return;
        }
        //  String data = new String(mqttMessage.getPayload());
        System.out.println(" --------->服务端收到数据:" + dataTemp3);
        JSONObject obj = JSONObject.parseObject(dataTemp3);
        String cmd = obj.getString("cmd");
        MqttBaseCmd mqttBaseCmd = CommandFactory.newInstance(cmd, dataTemp3, this);
        mqttBaseCmd.exe();

    }

    // publish后会执行到这里
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("publish后的deliveryComplete：" + iMqttDeliveryToken.isComplete() + "");
    }

    public BothSyncResponse route(String jsonRequestBody) {
        WxBaseMsg wxBaseMsg = WxBaseMsg.fromJson(jsonRequestBody);
        if (WxBaseMsg.MSG_TYPE_BIND.equals(wxBaseMsg.getMsg_type()) || WxBaseMsg.MSG_TYPE_UNBIND.equals(wxBaseMsg.getMsg_type())) {
            //  new UserBindOrUnbindPublisher(this).handle(jsonRequestBody);
        } else if (WxBaseMsg.MSG_TYPE_SET.equals(wxBaseMsg.getMsg_type())) {
            new UserSetPublisher(this).handle(jsonRequestBody);
        } else if (WxBaseMsg.MSG_TYPE_GET.equals(wxBaseMsg.getMsg_type())) {
            new UserGetPublisher(this).handle(jsonRequestBody);
        }
        return BothSyncResponse.OK();
    }

    public synchronized boolean publish(String topicStr, MqttMessage mqttMessage) {
        logger.info("准备publish的topic:" + topicStr);
        logger.info("准备publish的mqttMsg:" + mqttMessage);
        System.out.println("---------------publish ----");
        if (client == null) {
            //if (client == null || !client.isConnected()) {
            reConnect();
            logger.error("Wx2MqttService未连接上，无法publish");
            return false;
        }
        try {
            MqttTopic topic = client.getTopic(topicStr);
            MqttDeliveryToken token = topic.publish(mqttMessage);
            token.waitForCompletion();
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
*/

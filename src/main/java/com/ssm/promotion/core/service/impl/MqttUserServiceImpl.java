package com.ssm.promotion.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.iot.mqtt.config.Topic;
import com.iot.mqtt.config.Wx2MqttConfig;
import com.ssm.promotion.core.admin.PictureController;
import com.ssm.promotion.core.dao.MqttUserMapper;
import com.ssm.promotion.core.entity.MqttUser;
import com.ssm.promotion.core.service.*;
import com.ssm.promotion.core.service.impl.mqtt.BindMasterCmd;
import com.ssm.promotion.core.service.impl.mqtt.CommandFactory;
import com.ssm.promotion.core.service.impl.mqtt.UnBindDeviceCmd;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service("wx2MqttService")
public class MqttUserServiceImpl implements MqttCallback, MqttUserService {

    private static final Logger log = Logger.getLogger(PictureController.class);// 日志文件

    private MqttClient client;

    @Resource
    private MqttUserMapper mqttUserMapper;

    @Autowired
    private Wx2MqttConfig wx2MqttConfig;

    @Resource
    private DevicebindService equipmentService;

    @Resource
    private BuserService userService;

    @Resource
    private DeviceService deviceService;






    private MqttConnectOptions connectOptions = null;

    @PostConstruct
    public void init() {
        log.info("MqttUserServiceImpl PostConstruct init 初始化Wx2MqttService");

        try {
            client = new MqttClient(wx2MqttConfig.getHost() + ":" + wx2MqttConfig.getPort(), wx2MqttConfig.getClientId(), new MemoryPersistence());
            reConnect();
            initSetService();

        } catch (MqttException e) {
            e.printStackTrace();
            log.info("MqttUserServiceImpl PostConstruct init Exception:" + e.toString());

        }

    }


    private void initSetService() {

        BindMasterCmd.getInstance().setServicce(this, equipmentService, userService, deviceService);
        BindAuthorizationMasterCmd.getInstance().setServicce(this, equipmentService, userService, deviceService);
        UnBindDeviceCmd.getInstance().setServicce(this, equipmentService, userService, deviceService);

    }


    public int deleteByPrimaryKey(Integer id) {
        return mqttUserMapper.deleteByPrimaryKey(id);
    }


    public int insert(MqttUser record) {
        return mqttUserMapper.insert(record);
    }


    public int insertSelective(MqttUser record) {
        return mqttUserMapper.insertSelective(record);
    }


    public MqttUser selectByPrimaryKey(Integer id) {
        return mqttUserMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(MqttUser record) {
        return mqttUserMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(MqttUser record) {
        return mqttUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public synchronized boolean publish(String topicStr, MqttMessage mqttMessage) {


        log.info("准备publish的topic:" + topicStr);
        log.info("准备publish的mqttMsg:" + mqttMessage);

        System.out.println("---------------publish ----");
        if (client == null) {
            //if (client == null || !client.isConnected()) {
            reConnect();
            log.error("Wx2MqttService未连接上，无法publish");
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


    // 连接丢失后，一般在这里面进行重连
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("  连接断开了：  connectionLost");
        throwable.printStackTrace();
        log.info("MqttUserServiceImpl connectionLost:连接断开，正在重连");
        reConnect();


    }


    // subscribe后得到的消息会执行到这里面
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        //       System.out.println("messageArrived:" + topic + "      :" + new String(mqttMessage.getPayload()));

        //logger.error("messageArrived:" + topic + "      :" + new String(mqttMessage.getPayload()));
        log.info("MqttUserServiceImpl messageArrived:连接断开，正在重连" + topic + "      :" + new String(mqttMessage.getPayload()));

        String dataTemp3 = new String(mqttMessage.getPayload(), "UTF-8");   //Linux 服务器代码
        if (StringUtils.isEmpty(topic)) {
            return;
        }
        //  String data = new String(mqttMessage.getPayload());
        System.out.println(" --------->服务端收到数据:" + dataTemp3);
        JSONObject obj = JSONObject.parseObject(dataTemp3);
        String cmd = obj.getString("cmd");
        MqttBaseCmdService mqttBaseCmd = CommandFactory.newInstance(cmd, dataTemp3, this);
        mqttBaseCmd.exe();

    }

    // publish后会执行到这里
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        log.info("publish后的deliveryComplete：" + iMqttDeliveryToken.isComplete() + "");

    }


    private void subscribe() throws MqttException {
        //订阅消息
        //  logger.info("初始订阅消息");
        log.info("MqttUserServiceImpl：subscribe");
        System.out.println("初始订阅消息");
        client.subscribe(Topic.TOPICS, Topic.QOS);
    }

    private void connect() throws MqttException {
      /*  //阻塞在此直到连上或超时没连上
        // 调试模式先屏蔽
        log.info("阻塞在此直到连上或超时没连上");

        System.out.println("阻塞在此直到连上或超时没连上");
        client.connect(getConnectOptions());
        subscribe();
        //logger.info("阻塞完毕:Connected=" + client.isConnected());
        log.info("阻塞完毕:Connected=" + client.isConnected());

        System.out.println("阻塞在此直到连上或超时没连上" + client.isConnected());

*/
    }


    private MqttConnectOptions getConnectOptions() {
        if (connectOptions == null) {
            connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setUserName(wx2MqttConfig.getUserName());
            connectOptions.setPassword(wx2MqttConfig.getPassWord().toCharArray());
            System.out.println("得到当前的账户  密码 参数：" + wx2MqttConfig.getUserName() + "    " + wx2MqttConfig.getPassWord());
            connectOptions.setConnectionTimeout(20);
            // 设置会话心跳时间
            connectOptions.setKeepAliveInterval(20);
        }
        return connectOptions;
    }

    public void reConnect() {
       /* try {
            connect();
            client.setCallback(this);
        } catch (MqttException e) {
            e.printStackTrace();
        }*/
    }


}

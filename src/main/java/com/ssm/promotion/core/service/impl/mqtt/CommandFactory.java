package com.ssm.promotion.core.service.impl.mqtt;


import com.ssm.promotion.core.service.MqttBaseCmdService;
import com.ssm.promotion.core.service.MqttUserService;


public class CommandFactory {
    private static final String KEY_PUSH_MUSIC = "push_music";
    private static final String KEY_STEP = "step_control";
    private static final String KEY_BIND = "bind_master";
    private static final String KEY_BIND_AUTHORIZATION = "grant_master_authorization";   //授权信息
    private static final String KEY_UN_BIND = "unbind_master";   //解绑
    private static final String KEY_PUSH_VIDEO = "push_video";   //推送视频


    public static MqttBaseCmdService newInstance(String cmd, String data, MqttUserService wx2MqttService) {

        MqttBaseCmdService command = null;
        switch (cmd) {
            case KEY_PUSH_MUSIC:
                command = new PushMusicCmd(cmd, data, wx2MqttService);
                break;

                case KEY_PUSH_VIDEO:
                command = new PushVideoCmd(cmd, data, wx2MqttService);
                break;

            case KEY_STEP:
                command = new StepControlCmd(cmd, data, wx2MqttService);
                break;


            case KEY_BIND:
                command = BindMasterCmd.getInstance();
                BindMasterCmd.getInstance().runMasterCmd(cmd, data);
                 break;

            case KEY_BIND_AUTHORIZATION:
               /* command = BindAuthorizationMasterCmd.getInstance();
                BindAuthorizationMasterCmd.getInstance().runMasterCmd(cmd, data);*/
                break;
            case KEY_UN_BIND:
                command = UnBindDeviceCmd.getInstance();
                UnBindDeviceCmd.getInstance().runMasterCmd(cmd, data);
                break;


        }
        return command;
    }
}

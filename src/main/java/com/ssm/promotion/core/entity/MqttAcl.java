package com.ssm.promotion.core.entity;

import lombok.Data;

@Data
public class MqttAcl {
    private Integer id;

    /**
    * 0: deny, 1: allow
    */
    private Integer allow;

    /**
    * IpAddress
    */
    private String ipaddr;

    /**
    * Username
    */
    private String username;

    /**
    * ClientId
    */
    private String clientid;

    /**
    * 1: subscribe, 2: publish, 3: pubsub
    */
    private Integer access;

    /**
    * Topic Filter
    */
    private String topic;
}
package com.ssm.promotion.core.entity;

import java.util.Date;
import lombok.Data;

@Data
public class MqttUser {
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private Boolean isSuperuser;

    private Date created;

    private Integer issuperuser;

    private Date createtime;
}
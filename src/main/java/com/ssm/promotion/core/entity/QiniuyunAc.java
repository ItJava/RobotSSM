package com.ssm.promotion.core.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QiniuyunAc implements Serializable {
    private int id;

    private String accesskey;

    private String secretkey;

    private static final long serialVersionUID = 1L;
}
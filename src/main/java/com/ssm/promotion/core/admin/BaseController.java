package com.ssm.promotion.core.admin;


import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {


    @Autowired
    protected HttpServletRequest request;    //自动注入


}


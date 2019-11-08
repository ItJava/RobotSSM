package com.ssm.promotion.core.admin;


import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseController {


    @Autowired
    protected HttpServletRequest request;    //自动注入


    @Autowired
    protected HttpSession session;    //自动注入



}


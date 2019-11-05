package com.ssm.promotion.core.service;

public interface IMCommunicationService {

    String createImAccount(String userId, String mobile);

    String dropImAccount(String userId);

    String queryClientByMobile(String mobile);

    String queryClientByUserid(String userId);
}

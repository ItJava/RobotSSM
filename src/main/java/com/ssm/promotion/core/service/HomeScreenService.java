package com.ssm.promotion.core.service;

import com.ssm.promotion.core.entity.Chat;
import com.ssm.promotion.core.entity.HomeScreens;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

public interface HomeScreenService {


    String uploadPictures(CommonsMultipartFile file, String realUploadPaht, String newFileName) throws IOException;

    int deleteByPrimaryKey(Integer id);

    int deletHomePicByDeviceId(String deviceId);


    int insert(HomeScreens record);

    int insertHomePics(List<HomeScreens> records);



    int insertSelective(HomeScreens record);


    HomeScreens selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(HomeScreens record);


    int updateByPrimaryKey(HomeScreens record);



}

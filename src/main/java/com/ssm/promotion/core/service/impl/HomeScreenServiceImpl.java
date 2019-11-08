package com.ssm.promotion.core.service.impl;
import com.ssm.promotion.core.dao.HomeScreensMapper;
import com.ssm.promotion.core.entity.HomeScreens;
import com.ssm.promotion.core.service.HomeScreenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.ResourceBundle;

@Service("homescreenService")
public class HomeScreenServiceImpl implements HomeScreenService {


    @Resource
    private HomeScreensMapper homeScreensMapper;


    @Override
    public String uploadPictures(CommonsMultipartFile file, String realUploadPaht, String newFileName)
            throws IOException {
        ResourceBundle resource = ResourceBundle.getBundle("config");
        String storePictureFolder = resource.getString("storePictureFolder");
        //如果目录不存在创建目录
        File uploadFile = new File(realUploadPaht+storePictureFolder);
        if (!uploadFile.exists()) {
            System.err.println(uploadFile.exists());
            uploadFile.mkdirs();
        }
        //创建输入流
        InputStream inputStream = file.getInputStream();
        //生成输出地址URL realUploadPaht=绝对路径，storePictureFolder=存放图片的文件夹名 +文件的名字 +file.getOriginalFilename()
        String outPutPath  = realUploadPaht+storePictureFolder +"/"+ newFileName;
        //创建输出流 以及新文件名
        OutputStream outputStream = new FileOutputStream(outPutPath);
        //设置缓冲区
        byte[] buffer = new byte[1024];
        //输入流读入缓冲区，输出流从缓冲区写出
        while (inputStream.read(buffer)>0) {
            outputStream.write(buffer);
        }
        outputStream.close();
        //返回原图上传的相对地址
        String relative_address = storePictureFolder +"/"+ newFileName;
        return relative_address;
    }


    public int deleteByPrimaryKey(Integer id) {
        return homeScreensMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deletHomePicByDeviceId(String deviceId) {

        return 0;
    }




    public int insert(HomeScreens record) {
        return homeScreensMapper.insert(record);
    }

    @Override
    public int insertHomePics(List<HomeScreens> records) {
        for(HomeScreens homeScreen:records){
            insert(homeScreen);
        }
        return 0;
    }


    public int insertSelective(HomeScreens record) {
        return homeScreensMapper.insertSelective(record);
    }


    public HomeScreens selectByPrimaryKey(Integer id) {
        return homeScreensMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(HomeScreens record) {
        return homeScreensMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(HomeScreens record) {
        return homeScreensMapper.updateByPrimaryKey(record);
    }


}

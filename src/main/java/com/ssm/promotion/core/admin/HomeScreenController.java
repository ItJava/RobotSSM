package com.ssm.promotion.core.admin;

import com.google.gson.Gson;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.*;
import com.ssm.promotion.core.entity.bean.device.ReturnBindDeviceMsg;
import com.ssm.promotion.core.entity.bean.device.ReturnDeviceBean;
import com.ssm.promotion.core.service.*;
import com.ssm.promotion.core.util.EncryptionUtil;
import com.ssm.promotion.core.util.PhotoUtil;
import com.ssm.promotion.core.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/*
批量上传demo：
https://blog.csdn.net/u010843886/article/details/80629158
https://blog.csdn.net/zsq520520/article/details/72675687

 */
@Controller
@RequestMapping("/home")
public class HomeScreenController extends BaseController {

    private static final Logger log = Logger.getLogger(HomeScreenController.class);// 日志文件


    @Resource
    private DeviceService deviceService;

    @Resource
    private IMCommunicationService imCommunicationService;

    @Resource
    private PhonetokenService phonetokenService;

    @Resource
    private MqttUserService mqttUserService;

    @Resource
    private DevicebindService devicebindService;

    @Resource
    private BuserService buserService;

    @Resource
    private HomeScreenService homeScreenService;

    @Resource
    private ThumbnailService thumbnailService;


    //设备端绑定、获取当前绑定信息
    @ResponseBody
    @RequestMapping(value = "/uploadHomePic", method = RequestMethod.POST)
    public Result uploadHomePic(@RequestParam("upload_file") CommonsMultipartFile[] files,
                                @RequestParam("deviceId") String deviceId,
                                @RequestParam("createUser") String createUser,
                                @RequestParam("updateUser") String updateUser) throws FileNotFoundException, IOException {


     /*   //获取前台传过来的图片，和JSON字符串，这里需要转化成自己的po类到处需要在网上找JSON的几个包才能完成转化
        JSONObject jsonObject = JSONObject.fromObject(JSON_prduct);
        //将字符串JSON数据转化成自己的po 类
        Product product  = (Product) JSONObject.toBean(jsonObject, Product.class);*/
        //获取配置文件中的路径
        ResourceBundle resource = ResourceBundle.getBundle("config");
        Map<String, Object> map = new HashMap<String, Object>();
        String imageURL = null ;
        map.put("title","false");
//        //设置需要存的图片地址
        String pictureStorageAddress = resource.getString("realUploadPath")+request.getContextPath();
        System.err.println("图片存放地址："+pictureStorageAddress);
        //设置缩略图存放地址
        String thumbanilStorageAddress =  resource.getString("realThumbnailPath")+request.getContextPath();
        System.err.println("缩略图存放地址："+thumbanilStorageAddress);
        List<HomeScreens> homeScreens=new ArrayList<>();

        //循环取出图片
        for (CommonsMultipartFile cmf : files) {
            HomeScreens homeScreen=new HomeScreens();
            //获取文件全名
            String fileName = cmf.getOriginalFilename();
            String extensionName = fileName.substring(fileName.lastIndexOf(".")+1);
            System.out.println("获取的扩展名："+ extensionName);
            String newFileName = String.valueOf(System.currentTimeMillis())+"."+extensionName;
            System.out.println("新的图片名称："+newFileName);
//            存入图片后获得原图的相对地址,将相对地址存入数据库
            imageURL= homeScreenService.uploadPictures(cmf, pictureStorageAddress, newFileName);
            System.err.println("原图相对地址："+imageURL);
//             获取缩略图的相对地址返回给前段使用
            String thumbanilImageURL = thumbnailService.generateThumbnail(cmf, thumbanilStorageAddress, newFileName);
            System.err.println("缩略图相对地址："+thumbanilImageURL);
            homeScreen.setDeviceid(deviceId);
            homeScreen.setCreatedBy(createUser);
            homeScreen.setCreatedTime(new Date());
            homeScreen.setUpdatedBy(updateUser);
            homeScreen.setUpdatedTime(new Date());
            homeScreen.setRevision(0);
            homeScreen.setRealPic(imageURL);
            homeScreen.setThumbPic(thumbanilImageURL);
            homeScreens.add(homeScreen);
        }

        homeScreenService.deletHomePicByDeviceId(deviceId);
        homeScreenService.insertHomePics(homeScreens);

       /* //获取原图的绝对路径
        String storePictureFolder =pictureStorageAddress+request.getContextPath()+resource.getString("storePictureFolder");
        System.out.println("原图的绝对路径："+ storePictureFolder);
        //获取缩略图的绝对路径
        String realThumbanilPath =thumbanilStorageAddress+request.getContextPath()+resource.getString("thumbnailPictureFolder");
        System.out.println("缩略图的绝对路径："+ realThumbanilPath);
        //读取上传图片的图片
        List<String> realImageList = new ArrayList<String>();
        //获取缩略图里面的所有图片
       // realImageList = ProductImageListImpl.printFile(realThumbanilPath);
        //设置返回的imageList缩略图
       // map.put("realImageList", realImageList);
        System.out.println("realThumbanilPath:"+realThumbanilPath);
        System.out.println("imageURL:"+imageURL);*/

        /**
         *  设置图片相对地址到数据库以及日期用户等
         */
      /*  String  userInfo = (String) session.getAttribute("userInfo");
        String  userInfo1 = (String) request.getSession().getAttribute("userInfo");
        System.out.println("Session值1"+userInfo +"Session值2"+userInfo1);*/
//        product.setProductImage(imageURL);
//        product.setProductDeliverytime(CustomDate.getPresentTime());

//        //插入到数据库
       /* int temp = productService.inserProduct(product);


        if (temp > 0) {
            System.out.println("发布宝贝成功，已插入数据库！");
            map.put("message", "恭喜发布宝贝成功！");
            map.put("title", true);
        }*/
        return ResultGenerator.genFailResult("deviceSaveFlag!");
    }
    /*
     return ResultGenerator.genFailResult("deviceSaveFlag失败!" + new Gson().toJson(deviceSave));
    s return ResultGenerator.genSuccessResult(returnDeviceBean);
     */




}

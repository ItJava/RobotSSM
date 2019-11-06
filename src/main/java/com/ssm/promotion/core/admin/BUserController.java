package com.ssm.promotion.core.admin;

import com.google.gson.Gson;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Buser;
import com.ssm.promotion.core.entity.MqttUser;
import com.ssm.promotion.core.service.BuserService;
import com.ssm.promotion.core.service.IMCommunicationService;
import com.ssm.promotion.core.service.MqttUserService;
import com.ssm.promotion.core.util.EncryptionUtil;
import com.ssm.promotion.core.util.PhotoUtil;
import com.ssm.promotion.core.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;


@Controller
@RequestMapping("/busers")
public class BUserController extends BaseController {

    private static final Logger log = Logger.getLogger(BUserController.class);// 日志文件


    @Resource
    private BuserService buserService;

    @Resource
    private MqttUserService mqttUserService;

    @Resource
    private IMCommunicationService wxImService;

    //注册[不带图像]
    @RequestMapping(value = "/addUserNoPic", method = RequestMethod.POST)
    @ResponseBody
    public Result addUserNoPic(@RequestParam("userName") String buserName,
                               @RequestParam("pwd") String bPwd,
                               @RequestParam("bUserPhone") String bPhone,
                               @RequestParam("bUserAddress") String bAddress) {
        System.out.println("/busers/addUserNoPic  参数：buserName：" + buserName + " pwd：" + bPwd + " bUserPhone：" + bPhone + " bUserAddress：" + bAddress);
        if (StringUtil.isEmpty(buserName) ||
                StringUtil.isEmpty(bPhone) ||
                StringUtil.isEmpty(bAddress) ||
                StringUtil.isEmpty(bPwd))
            return ResultGenerator.genParamerFailResult("参数：buserName：" + buserName + " pwd：" + bPwd + " bUserPhone：" + bPhone + " bUserAddress：" + bAddress);
        Buser resultUser = buserService.selectBuserByPhone(bPhone);
        if (resultUser != null) return ResultGenerator.genFailResult("用户已经注册！");
     /*
       零时屏蔽
       String pushTokenStr = wxImService.createImAccount(bPhone, bPhone);
        ImCreateResponse imCreateResponse = new Gson().fromJson(pushTokenStr, ImCreateResponse.class);//wxImService.createImAccount(phone, phone);
        if (imCreateResponse == null) {
            AddUserData addUserData = new AddUserData();
            addUserData.setMessage(pushTokenStr);
            return ResultGenerator.genFailResult("第三方license生成有误,请联系后台查错！");
        }*/
        String phoneToken = "test_phoneToken";  //模拟第三方token
        Buser buser = new Buser();
        buser.setUserAddress(bAddress);
        buser.setRevision(0);
        buser.setUserName(buserName);
        //buser.setUserPwd(MD5Util.getMD5Str(bPwd + Constants.ENCODE_EXTRA_PARAMER_ADDED));
        buser.setUserPwd(EncryptionUtil.encryption(bPwd));
        buser.setPhoneToken(phoneToken);
        buser.setUserPhone(bPhone);
        buser.setCreatedTime(new Date());
        buser.setCreatedBy(bPhone);
        buser.setUserPic("");
        int bUserResultFlag = buserService.insert(buser);
        String userResultJson = new Gson().toJson(buser);
        if (bUserResultFlag == 0) return ResultGenerator.genFailResult("插入BUser失败！插入数据：" + userResultJson);

        MqttUser mqttUser = new MqttUser();
        //mqttUser.setIsSuperuser(false);
        mqttUser.setCreatetime(new Date());
        mqttUser.setUsername(EncryptionUtil.encryption(bPhone));
        mqttUser.setPassword(EncryptionUtil.encryption(bPhone));
        int mqttResultFlag = mqttUserService.insert(mqttUser);
        if (mqttResultFlag == 0) return ResultGenerator.genFailResult("MqttUser失败！插入数据：" + new Gson().toJson(mqttUser));
        return ResultGenerator.genSuccessResult(userResultJson);
    }


    //注册[带图像]
    @ResponseBody
    @RequestMapping(value = "/addUserSaveFile", method = RequestMethod.POST)
    public Result addUserSaveFile(@RequestParam("userName") String buserName,
                                  @RequestParam("pwd") String bPwd,
                                  @RequestParam("bUserPhone") String bPhone,
                                  @RequestParam("bUserAddress") String bAddress,
                                  @RequestParam("pic") MultipartFile file) {

        System.out.println("/busers/addUserNoPic  参数：buserName：" + buserName + " pwd：" + bPwd + " bUserPhone：" + bPhone + " bUserAddress：" + bAddress);
        if (StringUtil.isEmpty(buserName) ||
                StringUtil.isEmpty(bPhone) ||
                StringUtil.isEmpty(bAddress) ||
                StringUtil.isEmpty(bPwd))
            return ResultGenerator.genParamerFailResult("参数：buserName：" + buserName + " pwd：" + bPwd + " bUserPhone：" + bPhone + " bUserAddress：" + bAddress);
        Buser resultUser = buserService.selectBuserByPhone(bPhone);
        if (resultUser != null) return ResultGenerator.genFailResult("用户已经注册！");
     /*
       零时屏蔽
       String pushTokenStr = wxImService.createImAccount(bPhone, bPhone);
        ImCreateResponse imCreateResponse = new Gson().fromJson(pushTokenStr, ImCreateResponse.class);//wxImService.createImAccount(phone, phone);
        if (imCreateResponse == null) {
            AddUserData addUserData = new AddUserData();
            addUserData.setMessage(pushTokenStr);
            return ResultGenerator.genFailResult("第三方license生成有误,请联系后台查错！");
        }*/

        String photoUrl = PhotoUtil.saveFile(file, bPhone, request);

        String phoneToken = "test_phoneToken";  //模拟第三方token
        Buser buser = new Buser();
        buser.setUserAddress(bAddress);
        buser.setRevision(0);
        buser.setUserName(buserName);
        buser.setUserPwd(EncryptionUtil.encryption(bPwd));
        buser.setPhoneToken(phoneToken);
        buser.setUserPhone(bPhone);
        buser.setCreatedTime(new Date());
        buser.setCreatedBy(bPhone);
        buser.setUserPic(photoUrl);
        int bUserResultFlag = buserService.insert(buser);
        String userResultJson = new Gson().toJson(buser);
        if (bUserResultFlag == 0) return ResultGenerator.genFailResult("插入BUser失败！插入数据：" + userResultJson);

        MqttUser mqttUser = new MqttUser();
        //mqttUser.setIsSuperuser(false);
        mqttUser.setCreatetime(new Date());
        mqttUser.setUsername(EncryptionUtil.encryption(bPhone));
        mqttUser.setPassword(EncryptionUtil.encryption(bPwd));
        int mqttResultFlag = mqttUserService.insert(mqttUser);
        if (mqttResultFlag == 0) return ResultGenerator.genFailResult("MqttUser失败！插入数据：" + new Gson().toJson(mqttUser));
        return ResultGenerator.genSuccessResult(userResultJson);
    }


    //登录
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(pwd)) return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);
        if (buser != null) {
            if (buser.getUserPwd().equals(EncryptionUtil.encryption(pwd))) {
                return ResultGenerator.genSuccessResult(new Gson().toJson(buser));
            } else {
                return ResultGenerator.genFailResult("密码不对哦！");
            }
        } else {
            return ResultGenerator.genFailResult("用户还未注册！");

        }
    }

    //找回密码
    @ResponseBody
    @RequestMapping(value = "/findPwdByPhone", method = RequestMethod.POST)
    public Result findPwdByPhone(@RequestParam("phone") String phone) {
        if (StringUtil.isEmpty(phone)) return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);

        if (buser == null) return ResultGenerator.genFailResult("请检测手机号是否正确,该手机号未注册！");
        else
            buser.setUserPwd(EncryptionUtil.decrypt(buser.getUserPwd()));
        return ResultGenerator.genSuccessResult(new Gson().toJson(buser));
    }


    @ResponseBody
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Result upDatePwd(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd) {
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(pwd)) return ResultGenerator.genParamerFailResult("登录参数有误");
        Buser buser = buserService.selectBuserByPhone(phone);
        buser.setUserPwd(EncryptionUtil.encryption(pwd));
        buserService.updateByPrimaryKeySelective(buser);
        return ResultGenerator.genSuccessResult(new Gson().toJson(buser));

    /*    User userByUserName = userService.getByUserPhone(phone);
        if (userByUserName == null) {
            AddUserData addUserData = new AddUserData();
            return getClientMessage(addUserData, ResultCode.ERROR, ResultRemindMsg.ACCOUNT_NO_EXITS);
        }

        userService.updateUser(phone, pwd);
        User user = userService.getByUserPhone(phone);
        mqttUserService.updateMqttUser(phone, pwd);
        return getClientMessage(user, ResultCode.SUCCESS, "Success");*/

    }









    /*
     @ResponseBody
    @RequestMapping("/findPwdByPhone")
    public String findPwdByPhone(@RequestParam("phone") String userName) {
        User userByUserName = userService.getByUserName(userName);
        return getClientMessage(userByUserName, ResultCode.SUCCESS, "Success");
    }
     */


/*

userAddress
userPwd
userName
userPhone
phoneToken

 */




/*    //登录
    @RequestMapping(value = "/cookie", method = RequestMethod.POST)
    @ResponseBody
    public Result login(User user) {
        try {
            String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
            user.setPassword(MD5pwd);
        } catch (Exception e) {
            user.setPassword("");
        }
        User resultUser = userService.login(user);
        log.info("request: user/login , user: " + user.toString());
        if (resultUser == null) {
            return ResultGenerator.genFailResult("请认真核对账号、密码！");
        } else {
            resultUser.setPassword("PASSWORD");
            Map data = new HashMap();
            data.put("currentUser", resultUser);
            System.out.println("得到数据："+ResultGenerator.genSuccessResult(data));
            return ResultGenerator.genSuccessResult(data);
        }
    }



    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    public String list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, User s_user, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", StringUtil.formatLike(s_user.getUserName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<User> userList = userService.findUser(map);
        Long total = userService.getTotalUser(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(userList);
        result.put("rows", jsonArray);
        result.put("total", total);
        log.info("request: user/list , map: " + map.toString());
        ResponseUtil.write(response, result);
        return null;
    }

   //添加或修改管理员
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody User user) throws Exception {
        int resultTotal = 0;
        String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
        user.setPassword(MD5pwd);
        resultTotal = userService.addUser(user);
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }

    //修改
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Result update(@RequestBody User user) throws Exception {
        String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
        user.setPassword(MD5pwd);
        int resultTotal = userService.updateUser(user);
        log.info("request: user/update , user: " + user.toString());
        if (resultTotal > 0)return ResultGenerator.genSuccessResult();
        else return ResultGenerator.genFailResult("FAIL");

    }

   //删除管理员
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable(value = "ids") String ids) throws Exception {
        if (ids.length() > 20)return ResultGenerator.genFailResult("ERROR");
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            userService.deleteUser(Integer.valueOf(idsStr[i]));
        }
        log.info("request: article/delete , ids: " + ids);
        return ResultGenerator.genSuccessResult();
    }

    */


}

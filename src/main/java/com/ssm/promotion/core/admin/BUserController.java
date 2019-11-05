package com.ssm.promotion.core.admin;

import com.google.gson.Gson;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Buser;
import com.ssm.promotion.core.entity.PageBean;
import com.ssm.promotion.core.entity.User;
import com.ssm.promotion.core.service.BuserService;
import com.ssm.promotion.core.service.UserService;
import com.ssm.promotion.core.util.MD5Util;
import com.ssm.promotion.core.util.ResponseUtil;
import com.ssm.promotion.core.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/busers")
public class BUserController {

    @Resource
    private BuserService buserService;
    private static final Logger log = Logger.getLogger(BUserController.class);// 日志文件


    //登录
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
        System.out.println();
        Buser resultUser = buserService.selectBuserByPhone(bPhone);
        if (resultUser == null) {
          /*   String pushTokenStr = wxImService.createImAccount(phone, phone);

           ImCreateResponse imCreateResponse = new Gson().fromJson(pushTokenStr, ImCreateResponse.class);/[表情]ImService.createImAccount(phone, phone);
            if(imCreateResponse==null){
                AddUserData addUserData = new AddUserData();
                addUserData.setMessage(pushTokenStr);
                return getClientMessage(addUserData, ResultCode.ERROR, ResultRemindMsg.PARAMETER_ERROR);
            }
            String pushToken = imCreateResponse.getResp().getClient().getLoginToken();          //@RequestParam("pushToken") String pushToken
            //  String photoUrl = PhotoUtil.saveFile(file, userName, request);
            userService.saveUser(userName, passWord, phone, pushToken,"");
            mqttUserService.save(userName, passWord);
            loginStatusService.save(new LoginState(phone,""));   //添加当前登录的信息
            return getClientMessage(new User(userName, phone, pushToken, ""), ResultCode.SUCCESS, "Success");
*/




            //未注册
            return ResultGenerator.genFailResult("用户未注册！");
        } else {
            //已经注册
            return ResultGenerator.genFailResult("用户已经注册！");
        }

        /*
        {
    "resultCode": 500,
    "message": "用户已经注册！",
    "data": null
}
         */


    }


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

package com.sharism.user_server.controller;

import com.sharism.user_server.model.UserInfo;
import com.sharism.user_server.service.UserService;
import com.sharism.user_server.utils.DateTime;
import com.sharism.user_server.utils.Uuid;
import com.sharism.user_server.utils.mapToBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DYS
 * @Package com.sharism.user_server.controller
 * @Description:
 * @date 2018/3/18-19:36
 * @Version: 1.0
 */
@Controller
public class RegisteredUserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping(value = "/saveUserInfo", produces = {"application/json;charset=UTF-8"})
    public int saveUserInfo(HttpServletRequest request, HttpServletResponse response){
        //前台：
        // map转json字符串放入data
        // map = JSON.stringify(rebateMap);
        System.out.println(DateTime.getStringDate());

        String map1 = request.getParameter("map");
        Map map = new HashMap<String,Object>();
        map.put("id","id123");
        map.put("role",1 );
        map.put("userNickname","骨灰盒的束缚");
        map.put("userMail","1134873379@qq.com" );
        map.put("userPhone","15754325094");
        map.put("userPassword","md5password");
        map.put("userState", "锁住了");
        map.put("theme","默认主题");
        map.put("userScore",100);
        map.put("blogCount",51);
        map.put("photoCount",50);
        map.put("fileCount",49);
        map.put("memoryCapacity",409600);
        map.put("stored",10240);
        map.put("userName","董迎顺");
        map.put("userAge",24);
        map.put("userSex","男");
        map.put("birthday","1994-11-30");
        map.put("constellation","摩羯座");
        map.put("location","北京");
        map.put("major","专业");
        map.put("hobbies","IT");
        map.put("signature","爱咋咋地");
        map.put("contactWay","15754325094");
        map.put("expound","这是个人说明");
        map.put("qq","1134873379");
        map.put("mail","1134873379@qq.com");
        map.put("registerDate","2018-03-14-11-30:26");
        map.put("privacySet",1);
        map.put("iconPath","http://.....dys.png");
        map.put("loginTime","2018-03-14-12-30:46");
        map.put("loginIp","192.168.1.1");




        //if(map == null)
          //测试
          //  return 0;

        JSONObject jb = JSONObject.fromObject(map);
        Map userInfoMap = (Map)jb;
        UserInfo  userInfo=null;
        try {
            userInfo  = (UserInfo)mapToBean.mapToBean(map, UserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userInfo.setId(Uuid.getUUID());
        userInfo.setUserState("0");
        userInfo.setBlogCount(0l);
        userInfo.setRole(1);
        userInfo.setTheme("默认");
        userInfo.setFileCount(0l);
        userInfo.setPhotoCount(0l);
        userInfo.setRegisterDate(DateTime.getStringDate());
        userInfo.setUserScore(0.0);
        userInfo.setPrivacySet(1);
        userInfo.setMemoryCapacity(4096.0);
        userInfo.setUsedCapacity(0.0);
        userInfo.setIconPath("http:...dys.png");
        //return bookService.findBookList(pageNum,pageSize);
        return userService.registeredUser(userInfo);
    }



}

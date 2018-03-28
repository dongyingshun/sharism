package com.sharism.blog_server.controller;

import com.sharism.blog_server.model.BlogInfo;
import com.sharism.blog_server.service.BlogService;
import com.sharism.blog_server.utils.DateTime;
import com.sharism.blog_server.utils.Result;
import com.sharism.blog_server.utils.Uuid;
import com.sharism.blog_server.utils.mapToBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author DYS
 * @Package com.sharism.blog_server.controller
 * @Description:
 * @date 2018/3/24-23:50
 * @Version: 1.0
 */
@CrossOrigin //支持跨域请求
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
     /**
        * @ProjectName:    BlogController
        * @Description:    插入一篇博客
        * @Author:         DYS
        * @CreateDate:     2018/3/25-0:32
        * @UpdateUser:     DYS
        * @UpdateDate:
        * @UpdateRemark:   The modified content
        * @Version:        1.0
        */


    @RequestMapping(value = "/insertBlog", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result insertBlog(HttpServletRequest request, HttpServletResponse response){

        //TODO :获取session值

        String userId="690770002d9f4b78a10903efc3320391";




        String blog= request.getParameter("blogMap");
        if(blog==null)
            return  Result.newInstance().setCode(-1).setMessage("数据为空").setValue(null);
        JSONObject jb = JSONObject.fromObject(blog);
        Map blogMap = (Map)jb;
        BlogInfo blogInfo=new BlogInfo();
        try {
            blogInfo  = (BlogInfo) mapToBean.mapToBean(blogMap, BlogInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.newInstance().setCode(-2).setMessage("数据转换有误").setValue(null);
        }
        //id
        blogInfo.setId(Uuid.getUUID());
        //作者id
        blogInfo.setUserId(userId);
        //发表时间
        blogInfo.setPublishDate(DateTime.getStringDate());
        //发表删除
        blogInfo.setRemove(0);
        //点赞个数
        blogInfo.setPraiseAmount(0);
        //阅读次数
        blogInfo.setReadCount(0);
        //转发量
        blogInfo.setTranspondAmount(0);

        try {
            blogService.insertBlog(blogInfo);
            return  Result.newInstance().setCode(1).setMessage("博客上传成功").setValue(null);
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.newInstance().setCode(0).setMessage("博客上传失败").setValue(null);
        }
    }



    @RequestMapping(value = "/blogList", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result blogList(HttpServletRequest request, HttpServletResponse response){
        //TODO 获取session
        String userId="690770002d9f4b78a10903efc3320391";


        return  Result.newInstance().setCode(1).setMessage("成功").setValue(null);

    }
    @RequestMapping(value = "/test",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result test(){
        return  Result.newInstance().setCode(1).setMessage("测试成功").setValue("file_server 测试");

    }
}

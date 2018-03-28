package com.sharism.file_server.controller;

import com.sharism.file_server.kafka.beans.Capacity;
import com.sharism.file_server.model.FileInfo;
import com.sharism.file_server.service.UploadRecordService;
import com.sharism.file_server.service.UploaderService;
import com.sharism.file_server.utils.DateTime;
import com.sharism.file_server.utils.PublicValue;
import com.sharism.file_server.utils.Result;
import com.sharism.file_server.utils.Uuid;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author DYS
 * @Package com.sharism.file_server.controller
 * @Description:
 * @date 2018/3/15-16:31
 * @Version: 1.0
 */
@Controller
@CrossOrigin //支持跨域请求
//@Scope("prototype")//将Controller设置为多例模式
//@RequestMapping(value = "")
public class FileServerController {
    @Autowired
    private UploadRecordService uploadRecordService;
    @Resource(name = "uploaderService")
    private UploaderService uploaderService;

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @RequestMapping(value = "/upload",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result uploadFile(@RequestParam(value="file") MultipartFile file){
    //TODO:补充验证session逻辑

        String userId="690770002d9f4b78a10903efc3320391";

    //TODO：补充验证用户可用存储容量

    //上传FastDFS
    if(file==null)
        return Result.newInstance().setCode(-1).setMessage("上传的文件为空").setValue(null);
        String fileName = file.getOriginalFilename();//获取文件的名字
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);//获取文件后缀名
        String path =null;
        byte[] bytes =new byte[]{};
        try {
            bytes = file.getBytes();
            path = uploaderService.uploadFile(bytes, prefix);//返回存储的路径
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.newInstance().setCode(-2).setMessage("上传失败！").setValue(e.toString());

        }
    //文件数据记录
    FileInfo  fileInfo =new FileInfo();
    fileInfo.setId(Uuid.getUUID());
    fileInfo.setFileName(fileName);
    fileInfo.setFilePath(path);
    fileInfo.setFileType(prefix);
    double capacity=new BigDecimal(bytes.length/PublicValue.FILE_VOLUME_UNIT).setScale(PublicValue.FILE_SIZE_EFFECTIVE_LENGTH, BigDecimal.ROUND_HALF_UP).doubleValue();
    fileInfo.setFileSize(capacity);
    fileInfo.setUploadTime(DateTime.getStringDate());
    //TODO：存入用户id
    fileInfo.setUserId(userId);
    fileInfo.setIsDel(PublicValue.FILE_EXIXT);

        try {
             uploadRecordService.insertFileRecord(fileInfo);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //i=0 删除成功 2 数据不存在
                uploaderService.deleteFile(StringUtils.substringBefore(path, "/"), StringUtils.substringAfter(path, "/"));
                return Result.newInstance().setCode(-3).setMessage("上传失败，文件删除成功");
            } catch (Exception e1) {
                e1.printStackTrace();
                return Result.newInstance().setCode(-4).setMessage("上传失败，文件删除失败").setValue(e1.toString());

            }

        }
        JSONObject obj = JSONObject.fromObject(new Capacity(capacity,userId,1));
        //发送文件容量信息
        kafkaTemplate.send("capacity",obj.toString());

        return Result.newInstance().setCode(1).setMessage("上传成功").setValue(PublicValue.HTTP+PublicValue.TRACKER_IP+":"+PublicValue.TRACKER_NGINX_PORT+"/"+path);
    }

      /**
         * @ProjectName:    FileServerController
         * @Description:    批量删除文件信息
         * @Author:         DYS
         * @CreateDate:     2018/3/16-0:35
         * @UpdateUser:     DYS
         * @UpdateDate:
         * @UpdateRemark:   The modified content
         * @Version:        1.0
         */
    @RequestMapping(value = "/deleteFileInfo")
    @ResponseBody
    public Result deleteFileIndo(HttpServletRequest request, String[] ids)  {
        //ids 为文件地址 group2/M00/00/00/CpqBPFqmlU6AafLKAHRUvsH1NTc326.JPG

        //TODO:补充验证session逻辑

        //验证数组是否为空
        if(ids.length==0)
            return Result.newInstance().setCode(-1).setMessage("文件地址为空");
        int flag=0;
        try {
            flag=  uploadRecordService.batchDeleteFileInfo(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.newInstance().setCode(-2).setMessage("服务器开小差了，删除失败");
        }

        return Result.newInstance().setCode(1).setMessage("删除成功").setValue(flag);
    }
    @RequestMapping(value = "/deleteFileData")
    @ResponseBody
    public Object deleteFileData(HttpServletRequest request, String[] ids) throws Exception {
        //ids 为文件地址 group2/M00/00/00/CpqBPFqmlU6AafLKAHRUvsH1NTc326.JPG
        //TODO:补充验证session逻辑
        //验证数组是否为空
        if(ids.length==0)
            return "";
        String  test="group2/M00/00/00/CpqBPFqmlU6AafLKAHRUvsH1NTc326.JPG";
        String groupName = StringUtils.substringBefore(test, "/");

        String  path= StringUtils.substringAfter(test, "/");
        System.out.println("groupName:"+groupName);
        System.out.println("path:"+path);
        //i=0 删除成功 2 数据不存在
        int i = uploaderService.deleteFile(groupName, path);
        //还有插入数据库的动作，代码还需要优化，此处先这样，不过可以上传成功了。他返回的是一个类似M00/00/00/xxxxxsssxxxxx.png这样的path，让你存入数据库的，用于访问和下载用的。
        System.out.print(i);
        return i;
    }


    @RequestMapping(value = "/test",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result test(){
     return  Result.newInstance().setCode(1).setMessage("测试成功").setValue("file_server 测试");

    }
}

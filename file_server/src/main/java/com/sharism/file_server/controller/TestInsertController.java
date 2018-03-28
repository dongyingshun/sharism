package com.sharism.file_server.controller;

import com.sharism.file_server.model.FileInfo;
import com.sharism.file_server.service.UploadRecordService;
import com.sharism.file_server.service.UploaderService;
import com.sharism.file_server.utils.DateTime;
import com.sharism.file_server.utils.PublicValue;
import com.sharism.file_server.utils.Result;
import com.sharism.file_server.utils.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;

/**
 * 文件处理控制器:包括上传、下载,主要是调用fastdfs的api
 */
@Controller
public class TestInsertController {

    @Autowired
    private UploadRecordService uploadRecordService;
    /**
     * 上传头像
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insert")
    @ResponseBody//@RequestParam(value="file") MultipartFile file
    public Result uploadFile(){

        String userId="690770002d9f4b78a10903efc3320391";

        String prefix ="jpg";
        //还有插入数据库的动作，代码还需要优化，此处先这样，不过可以上传成功了。他返回的是一个类似M00/00/00/xxxxxsssxxxxx.png这样的path，让你存入数据库的，用于访问和下载用的。
        String path="118.25.48.91:8000/group2/M00/00/00/CpqBPFq53ryAHyg8AAGQMuVvS3M134.jpg";
        System.out.print(path);
        System.out.println("文件长度："+102400/1024+"kb");

        //文件数据记录
        FileInfo  fileInfo =new FileInfo();
        fileInfo.setId(Uuid.getUUID());
        fileInfo.setFileName("dys.jpg");
        fileInfo.setFilePath(path);
        fileInfo.setFileType(prefix);
        fileInfo.setFileSize(new BigDecimal(102400/PublicValue.FILE_VOLUME_UNIT).setScale(PublicValue.FILE_SIZE_EFFECTIVE_LENGTH, BigDecimal.ROUND_HALF_UP).doubleValue());
        fileInfo.setUploadTime(DateTime.getStringDate());
        fileInfo.setDeleteTime("0");
        fileInfo.setUserId(userId);
        fileInfo.setIsDel(PublicValue.FILE_EXIXT);
        try {
            uploadRecordService.insertFileRecord(fileInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.newInstance().setCode(-3).setMessage("添加失败").setValue(PublicValue.TRACKER_IP+":"+PublicValue.TRACKER_NGINX_PORT+"/"+path);

    }
        return Result.newInstance().setCode(1).setMessage("添加成功").setValue(PublicValue.TRACKER_IP+":"+PublicValue.TRACKER_NGINX_PORT+"/"+path);

    }

}
package com.sharism.file_server.service;

import com.sharism.file_server.model.FileInfo;

   /**
    * @ProjectName:    UploadRecordService
    * @Description:    数据库记录
    * @Author:         DYS
    * @CreateDate:     2018/3/15-16:01
    * @UpdateUser:     DYS
    * @UpdateDate:
    * @UpdateRemark:   The modified content
    * @Version:        1.0
    */
public interface UploadRecordService {
    /**
     * @ProjectName:    UploadRecordService
     * @Description:    插入一条文件存储记录
     * @Author:         DYS
     * @CreateDate:     2018/3/15-16:00
     * @UpdateUser:     DYS
     * @UpdateDate:
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */
    int insertFileRecord(FileInfo fileInfo)throws Exception;
    /**
     * @ProjectName:    UploadRecordService
     * @Description:    插入一条文件存储记录 可判断空值
     * @Author:         DYS
     * @CreateDate:     2018/3/16-0:25
     * @UpdateUser:     DYS
     * @UpdateDate:
     * @UpdateRemark:   The modified content
     * @Version:        1.0
     */
    int insertSelectiveFileRecord(FileInfo fileInfo) throws Exception;

   /**
    * @ProjectName:    UploadRecordService
    * @Description:    批量更改文件删除信息
    * @Author:         DYS
    * @CreateDate:     2018/3/16-0:52
    * @UpdateUser:     DYS
    * @UpdateDate:
    * @UpdateRemark:   The modified content
    * @Version:        1.0
    */
    int batchDeleteFileInfo(String[] empno)throws Exception;

  /**
    * @ProjectName:    UploadRecordService
    * @Description:    批量删除文件数据
    * @Author:         DYS
    * @CreateDate:     2018/3/16-1:18
    * @UpdateUser:     DYS
    * @UpdateDate:
    * @UpdateRemark:   The modified content
    * @Version:        1.0
    */
    int batchDeleteFileData(String[] empno)throws Exception;
}

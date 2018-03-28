package com.sharism.file_server.service.impl;

import com.sharism.file_server.service.UploaderService;
import com.sharism.file_server.utils.FastDFSClientUtils;
import org.springframework.stereotype.Service;

/**
 * 文件上传接口
 */
@Service("uploaderService")
public class UploaderServiceImpl implements UploaderService {
    @Override
    public String uploadFile(byte[] file_buff, String file_ext_name) throws Exception{
        FastDFSClientUtils client = new FastDFSClientUtils("conf/fdfs_client.conf");
        String uploadFile = client.uploadFile(file_buff, file_ext_name, null);
        return uploadFile;
    }

    @Override
    public int deleteFile(String groupName,String path) throws Exception{
        FastDFSClientUtils client = new FastDFSClientUtils("conf/fdfs_client.conf");
        int  deleteFile = client.deleteFile(groupName, path);
        return deleteFile;
    }
    public byte[] downloadFile(String groupName,String path,String fileName)throws Exception{
        FastDFSClientUtils client = new FastDFSClientUtils("conf/fdfs_client.conf");
        byte[] b = client.downloadFile(groupName,path);
        return b;
    }
}

package com.sharism.file_server.service;

public interface UploaderService {
    String uploadFile(byte[] file_buff, String file_ext_name) throws Exception;
    int deleteFile(String groupName, String path) throws Exception;
    byte[] downloadFile(String groupName, String path, String fileName)throws Exception;

}

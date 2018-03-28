package com.sharism.file_server.service.impl;

import com.sharism.file_server.mapper.FileInfoMapper;
import com.sharism.file_server.model.FileInfo;
import com.sharism.file_server.service.UploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DYS
 * @Package com.sharism.file_server.service.impl
 * @Description:
 * @date 2018/3/15-16:07
 * @Version: 1.0
 */
@Service("uploadRecordService")
public class UploadRecordServiceImpl implements UploadRecordService{
    @Autowired
    private FileInfoMapper fileInfoMapper;
    @Override
    public int insertFileRecord(FileInfo fileInfo) throws Exception {
        return fileInfoMapper.insert(fileInfo);
    }

    @Override
    public int insertSelectiveFileRecord(FileInfo fileInfo) throws Exception {
        return fileInfoMapper.insertSelective(fileInfo);
    }

    @Override
    public int batchDeleteFileInfo(String[] empno) throws Exception {
        return fileInfoMapper.batchDeleteFileInfo(empno);
    }

    @Override
    public int batchDeleteFileData(String[] empno) throws Exception {
        return fileInfoMapper.batchDeleteFileData(empno);

    }



}

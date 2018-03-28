package com.sharism.file_server.utils;

/**
 * @author DYS
 * @Package com.sharism.file_server.utils
 * @Description: 设置数值
 * @date 2018/3/15-23:17
 * @Version: 1.0
 */
public class PublicValue {
    //文件存在 置为0
    public static final int FILE_EXIXT=0;
    //文件删除 置为1
    public static final int FILE_DELETE=1;
    //文件容量 有效小数点位数
    public static final int FILE_SIZE_EFFECTIVE_LENGTH=8;
    //文件容量计算 1024(kb)  1024*1024(MB)  1024*1024*1024(GB)
    public static final int FILE_VOLUME_UNIT=1024;
    //上传文件大小 5M 5*1024*1024
    public static final int FILE_MAX_UPLOAD_SIZE=10240 * 1024 * 1024; //10GB
    //http
    public static final String HTTP="http://";
    //Tracker ip 地址
    public static final String TRACKER_IP="118.25.48.91";
    //Tracker Nginx 监听端口号
    public static final String TRACKER_NGINX_PORT="8000";
}

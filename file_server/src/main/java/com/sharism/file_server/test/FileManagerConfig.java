package com.sharism.file_server.test;

import java.io.Serializable;

/**
 * @author DYS
 * @Package com.sharism.file_server.test
 * @Description:
 * @date 2018/3/15-20:00
 * @Version: 1.0
 */
public interface FileManagerConfig extends Serializable {
    public static final String FILE_DEFAULT_WIDTH     = "120";
    public static final String FILE_DEFAULT_HEIGHT    = "120";
    public static final String FILE_DEFAULT_AUTHOR    = "Diandi";
    public static final String PROTOCOL = "http://";
    public static final String SEPARATOR = "/";
    public static final String TRACKER_NGNIX_PORT   = "8888";
    public static final String CLIENT_CONFIG_FILE   = "conf/fdfs_client.conf";
}

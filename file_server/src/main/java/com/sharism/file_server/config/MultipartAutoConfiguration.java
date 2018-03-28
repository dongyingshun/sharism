package com.sharism.file_server.config;

import com.sharism.file_server.utils.PublicValue;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author DYS
 * @Package com.sharism.file_server.config
 * @Description:显示声明CommonsMultipartResolver为mutipartResolver
 * @date 2018/3/15-21:42
 * @Version: 1.0
 */
public class MultipartAutoConfiguration {

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
         resolver.setDefaultEncoding("UTF-8");
        // resolver.setResolveLazily(true);// resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        // resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(PublicValue.FILE_MAX_UPLOAD_SIZE);// 上传文件大小 5M 5*1024*1024
        return resolver;
    }
}

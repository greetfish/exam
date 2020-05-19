package com.junle.exam.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebFileStoreConfig  implements WebMvcConfigurer{
    /**
     * 静态资源处理
     * **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File("/C:/springboot/");
        if(!file.exists()){
            file.mkdirs();
        }
    }
}

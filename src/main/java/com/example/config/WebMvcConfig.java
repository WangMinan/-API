package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.path.windows}")
    private String windowsFilePath;
    @Value("${file.path.linux}")
    private String linuxFilePath;

    /**
     * 静态资源文件映射配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/file/**").addResourceLocations("file:" + windowsFilePath);
        } else { //linux和mac系统
            registry.addResourceHandler("/file/**").addResourceLocations("file:" + linuxFilePath);
        }
    }
}

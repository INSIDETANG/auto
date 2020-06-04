package com.autonomy.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    /*
     * 注册静态文件的自定义映射路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //定义到新文件夹
        registry.addResourceHandler("/image/**")
                .addResourceLocations("classpath:/image/");
        //定义到硬盘
//        registry.addResourceHandler("/picture/**")
//                .addResourceLocations("file:D:/picture/");
        super.addResourceHandlers(registry);
    }
}

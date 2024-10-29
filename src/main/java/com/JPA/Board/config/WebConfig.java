package com.JPA.Board.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.stereotype.Controller;

@Controller
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath="/upload/**";
    private String savePath="file:///C:/Users/wjaud/OneDrive/바탕 화면/MOST IMPORTANT/WIT_TEST/file/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
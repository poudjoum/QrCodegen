package com.jumpytech.QrCodeGen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class StaticRessourceConfig implements WebMvcConfigurer {

	@Value("${qrcode.storage-path}")
    private String storagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + storagePath + "/");
    }
}

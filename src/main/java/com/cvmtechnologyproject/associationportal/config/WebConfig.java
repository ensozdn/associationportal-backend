package com.cvmtechnologyproject.associationportal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

//	Hızlı geliştirme: Yüklenen dosya anında URL’den görülebilir.
//	Basit entegrasyon: Frontend, medyayı /uploads/... ile doğrudan çeker.

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // uploads klasörünün yolunu al
        Path uploadDir = Paths.get("uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        // /uploads/** isteği gelirse dosya sisteminden karşıla
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath + "/")
                .setCachePeriod(0); // tarayıcı önbelleğini kapat
    }
}
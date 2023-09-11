package com.jiuan.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

/**
 * @className: ResourcesConfig
 * @description: resource config
 * @author: Dimple
 * @date: 10/22/19
 */
@Configuration
//@EnableWebMvc
public class ResourcesConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String pathUtl = Paths.get(JiuanBlogConfig.getProfile()).normalize().toUri().toASCIIString();
        registry.addResourceHandler("/file/**").addResourceLocations(pathUtl).setCachePeriod(0);

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    //允许跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }


}

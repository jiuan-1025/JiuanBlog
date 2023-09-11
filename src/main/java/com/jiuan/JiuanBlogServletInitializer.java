package com.jiuan;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @className: DimpleBlogServletInitializer
 * @description: web容器中进行部署
 * @author: Dimple
 * @date: 10/22/19
 */
public class JiuanBlogServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JiuanBlogApplication.class);

    }
}

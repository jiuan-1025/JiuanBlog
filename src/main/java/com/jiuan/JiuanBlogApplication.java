package com.jiuan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @className: JiuanBlogApplication
 * @description: 启动程序
 * @author: Jiuan
 * @date: 10/22/19
 */
@SpringBootApplication
@Slf4j
public class JiuanBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiuanBlogApplication.class, args);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DimpleBlogApplication.class);
    }

}


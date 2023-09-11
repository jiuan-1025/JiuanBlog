package com.jiuan;

import com.jiuan.project.tool.mapper.QiNiuContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;

@Slf4j
@SpringBootTest
public class JiuanBlogTest {
    @Autowired
    QiNiuContentMapper qiNiuContentMapper;

    @Test
    public void testUpload(){
        ConfigurableApplicationContext run = SpringApplication.run(JiuanBlogApplication.class);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        System.out.println("=================");
        while (beanNamesIterator.hasNext()){
            System.out.println(beanNamesIterator.next());
        }
        System.out.println(222);
    }



}

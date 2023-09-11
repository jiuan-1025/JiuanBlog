package com.jiuan.ip2region;

import com.jiuan.common.utils.file.FileUtils;
import com.jiuan.framework.config.websocket.LoggerQueue;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: JiuAn
 * @Date: 2023-06-10-14:16
 * @Version 1.0 （版本号）
 * @Description:
 */

@Slf4j
@SpringBootTest
public class ipTest {
//    @Test
    public void getCityInfoByIp() {
//        String ip = "151.101.90.132";
        String ip = "110.242.68.66";
        try {
            String path = "ip2region/ip2region.db";
            String name = "ip2region.db";
            DbConfig config = new DbConfig();
            File file = FileUtils.inputStreamToFile(new ClassPathResource(path).getInputStream(), name);
            DbSearcher searcher = new DbSearcher(config, file.getPath());
            Method method;
            method = searcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock;
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            String address = dataBlock.getRegion().replace("0|", "");
            if (address.charAt(address.length() - 1) == '|') {
                address = address.substring(0, address.length() - 1);
            }
            log.info(address.equals("内网IP|内网IP") ? "内网IP" : address);
        } catch (Exception e) {
            log.error("Get Error In Get City Address ,{}", e.getMessage(), e);
        }
        log.error("错误");
    }

    @Autowired
    SimpMessagingTemplate messagingTemplate;

//    @Test
    public void getmessagingTemplate() {
        String TOPIC = "/topic/pullLogger";
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("WebSocketConfig1:"+LoggerQueue.getInstance().hashCode());
        int i = 0;
        while (i < 10000) {
                try {
                    Thread.sleep(1);
                    log.info("测试日志输出"+i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
}

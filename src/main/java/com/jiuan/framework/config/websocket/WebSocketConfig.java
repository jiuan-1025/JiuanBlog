package com.jiuan.framework.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className: WebSocketConfig
 * @description:
 * @author: Dimple
 * @date: 11/05/19
 */
@Configuration
@EnableWebSocketMessageBroker
@Slf4j
//public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*");
//                .setAllowedOrigins("http://localhost:5500")
//                .withSockJS();
    }

    /**
     * 推送日志到/topic/pullLogger
     */
    @PostConstruct
    public void pushLogger() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable runnable = () -> {
            while (true) {
                try {
//                    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                    LoggerMessage log = LoggerQueue.getInstance().poll();
                    if (log != null && messagingTemplate != null) {
                        messagingTemplate.convertAndSend("/topic/pullLogger", log);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        };
        executor.submit(runnable);
    }



}

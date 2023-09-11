package com.jiuan.framework.config.quartz;

import com.jiuan.framework.config.websocket.WebSocketConfig;
import com.jiuan.project.tool.domain.QuartzJob;
import com.jiuan.project.tool.service.QuartzJobService;
import com.jiuan.project.tool.utils.QuartzManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className: JobRunner
 * @description: Job Runner for load job from database-用于从数据库加载作业的作业运行程序
 * @author: Dimple
 * @date: 11/07/19
 */
@Component
@Slf4j
public class JobRunner implements ApplicationRunner {
    @Autowired
    QuartzManage quartzManage;
    @Autowired
    QuartzJobService quartzJobService;

    /**
     * get job from database when application run
     */
    @Autowired
    WebSocketConfig webSocketConfig;
    @Override
    public void run(ApplicationArguments args) {
        log.info("start inject task");
        List<QuartzJob> quartzJobList = quartzJobService.selectRunningQuartzJobList();
        quartzJobList.forEach(quartzManage::addJob);
        log.info("end inject task,the size of task {}", quartzJobList.size());

    }



}

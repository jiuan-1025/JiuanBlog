package com.jiuan.project.tool.task;

import com.jiuan.common.enums.CacheConstants;
import com.jiuan.framework.redis.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: JiuAn
 * @Date: 2023-06-13-16:29
 * @Version 1.0 （版本号）
 * @Description:
 */

@Slf4j
@Component
public class RedisCaCheTask {
    @Autowired
    RedisCacheService redisCacheService;

//    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT_CAROUSEL_LIST ,key = "'SimpleKey []'")
    public String deleteCaChe(){
        redisCacheService.deleteObject(CacheConstants.CACHE_NAME_FRONT_CAROUSEL_LIST);
        log.info("deleteCaChe执行成功，参数为： {}", "CacheConstants.CACHE_NAME_FRONT_CAROUSEL_LIST");
        return "deleteCaChe执行成功";
    }
    public String deleteCaChe(String tableName){
        log.info("执行成功，参数为： {}", tableName);
        return "不支持的table";
    }
}

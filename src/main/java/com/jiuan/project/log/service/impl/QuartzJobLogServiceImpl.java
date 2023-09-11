package com.jiuan.project.log.service.impl;

import com.jiuan.common.utils.ConvertUtils;
import com.jiuan.common.utils.SecurityUtils;
import com.jiuan.project.log.mapper.QuartzJobLogMapper;
import com.jiuan.project.log.service.QuartzJobLogService;
import com.jiuan.project.log.domain.QuartzJobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: QuartzJobLogServiceImpl
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Service
public class QuartzJobLogServiceImpl implements QuartzJobLogService {

    @Autowired
    QuartzJobLogMapper quartzJobLogMapper;

    @Override
    public int insertQuartzJobLog(QuartzJobLog quartzJobLog) {
        return quartzJobLogMapper.insertQuartzJobLog(quartzJobLog);
    }

    @Override
    public List<QuartzJobLog> selectQuartzJobLogList(QuartzJobLog quartzJobLog) {
        return quartzJobLogMapper.selectQuartzJobLogList(quartzJobLog);
    }

    @Override
    public int deleteQuartzJobLogByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return quartzJobLogMapper.deleteQuartzJobLogByIds(ConvertUtils.toLongArray(ids), username);
    }

    @Override
    public void cleanQuartzJobLog() {
        String username = SecurityUtils.getUsername();
        quartzJobLogMapper.cleanQuartzJobLog(username);
    }

    @Override
    public QuartzJobLog selectQuartzJobLogById(Long id) {
        return quartzJobLogMapper.selectQuartzJobLogById(id);
    }
}

package com.jiuan.project.system.service.impl;

import com.jiuan.common.enums.CacheConstants;
import com.jiuan.common.utils.ConvertUtils;
import com.jiuan.common.utils.SecurityUtils;
import com.jiuan.project.system.domain.Notice;
import com.jiuan.project.system.mapper.NoticeMapper;
import com.jiuan.project.system.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: NoticeServiceImpl
 * @description: 公告 服务层实现
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Notice selectNoticeById(Long noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    @Override
    public List<Notice> selectNoticeList(Notice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    @Override
    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT_NOTICE_LIST ,key = "'SimpleKey []'")
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT_NOTICE_LIST ,key = "'SimpleKey []'")
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT_NOTICE_LIST ,key = "'SimpleKey []'")
    public int deleteNoticeByIds(String ids) {
        String loginUsername = SecurityUtils.getUsername();
        return noticeMapper.deleteNoticeByIds(ConvertUtils.toLongArray(ids), loginUsername);
    }
}

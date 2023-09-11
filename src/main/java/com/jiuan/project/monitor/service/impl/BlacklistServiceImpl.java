package com.jiuan.project.monitor.service.impl;

import com.jiuan.common.utils.ConvertUtils;
import com.jiuan.common.utils.SecurityUtils;
import com.jiuan.project.monitor.domain.Blacklist;
import com.jiuan.project.monitor.mapper.BlacklistMapper;
import com.jiuan.project.monitor.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: BlacklistServiceImpl
 * @description:
 * @author: Dimpleb
 * @date: 10/24/19
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Autowired
    BlacklistMapper blacklistMapper;

    @Override
    public int deleteBlacklistByIds(String ids) {
        return blacklistMapper.deleteBlacklistByIds(ConvertUtils.toStrArray(ids), SecurityUtils.getUsername());
    }

    @Override
    public int updateBlacklist(Blacklist blacklist) {
        return blacklistMapper.updateBlacklist(blacklist);
    }

    @Override
    public int insertBlacklist(Blacklist blacklist) {
        return blacklistMapper.insertBlacklist(blacklist);
    }

    @Override
    public Blacklist selectBlacklistById(Long id) {
        return blacklistMapper.selectBlacklistById(id);
    }

    @Override
    public List<Blacklist> selectBlacklistList(Blacklist blacklist) {
        return blacklistMapper.selectBlacklistList(blacklist);
    }
}

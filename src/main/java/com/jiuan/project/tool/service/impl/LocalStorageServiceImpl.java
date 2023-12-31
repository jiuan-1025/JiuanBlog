package com.jiuan.project.tool.service.impl;

import com.jiuan.common.exception.CustomException;
import com.jiuan.common.utils.SecurityUtils;
import com.jiuan.common.utils.StringUtils;
import com.jiuan.common.utils.file.FileUtils;
import com.jiuan.framework.config.JiuanBlogConfig;
import com.jiuan.project.tool.domain.LocalStorage;
import com.jiuan.project.tool.mapper.LocalStorageMapper;
import com.jiuan.project.tool.service.LocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @className: LocalStorageServiceImpl
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Service
public class LocalStorageServiceImpl implements LocalStorageService {

    @Autowired
    LocalStorageMapper localStorageMapper;

    @Override
    public List<LocalStorage> selectLocalStorageList(LocalStorage localStorage) {
        return localStorageMapper.selectLocalStorageList(localStorage);
    }

    @Override
    public int upload(String name, MultipartFile multipartFile) {
        //检查大小
        //获取后缀
        String suffix = FileUtils.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtils.getFileType(suffix); //获取文件类型
        File file = FileUtils.upload(multipartFile, JiuanBlogConfig.getProfile() + type + File.separator);
        if (Objects.isNull(file)) {
            throw new CustomException("上传失败");
        }
        //防止异常出错
        try {
            name = StringUtils.isBlank(name) ? FileUtils.getFileNameNoExtension(multipartFile.getOriginalFilename()) : name;
            LocalStorage localStorage = new LocalStorage(file.getName(), name, suffix, file.getPath(), type, FileUtils.getSizeString(multipartFile.getSize()));
            localStorage.setCreateBy(SecurityUtils.getUsername());
            return localStorageMapper.insertLocalStorage(localStorage);
        } catch (Exception e) {
            FileUtils.del(file);
            throw e;
        }
    }

    @Override
    public int updateLocalStorage(LocalStorage localStorage) {
        localStorage.setUpdateBy(SecurityUtils.getUsername());
        return localStorageMapper.updateLocalStorage(localStorage);
    }

    @Override
    public int deleteLocalStorage(Long id) {
        String username = SecurityUtils.getUsername();
        LocalStorage localStorage = localStorageMapper.selectLocalStorageById(id);
        if (Objects.isNull(localStorage)) {
            throw new CustomException("文件不存在");
        }
        //删除文件
        String path = localStorage.getPath();
        FileUtils.del(path);
        return localStorageMapper.deleteLocalStorageById(id, username);
    }
}

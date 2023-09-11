package com.jiuan.project.log.controller;

import com.jiuan.framework.web.controller.BaseController;
import com.jiuan.framework.web.domain.AjaxResult;
import com.jiuan.framework.web.page.TableDataInfo;
import com.jiuan.project.log.domain.QuartzJobLog;
import com.jiuan.project.log.service.QuartzJobLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: QuartzJobLogController
 * @description:
 * @author: Dimple
 * @date: 11/08/19
 */
@RestController
@RequestMapping("/log/quartzLog")
public class QuartzJobLogController extends BaseController {

    final QuartzJobLogService quartzJobLogService;

    public QuartzJobLogController(QuartzJobLogService quartzJobLogService) {
        this.quartzJobLogService = quartzJobLogService;
    }

    @GetMapping("/list")
    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:list')")
    public TableDataInfo list(QuartzJobLog quartzJobLog) {
        startPage();
        List<QuartzJobLog> list = quartzJobLogService.selectQuartzJobLogList(quartzJobLog);
        return getDataTable(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:query')")
    public AjaxResult query(@PathVariable Long id) {
        return AjaxResult.success(quartzJobLogService.selectQuartzJobLogById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:quartzLog:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult deleteQuartzJobLogByIds(@PathVariable String ids) {
        return toAjax(quartzJobLogService.deleteQuartzJobLogByIds(ids));
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:quartzLog:remove')")
    @DeleteMapping("/clean")
    public AjaxResult cleanQuartzJobLog() {
        quartzJobLogService.cleanQuartzJobLog();
        return AjaxResult.success();
    }


}

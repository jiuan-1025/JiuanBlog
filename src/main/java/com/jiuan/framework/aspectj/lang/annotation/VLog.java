package com.jiuan.framework.aspectj.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: VLog
 * @description: an annotation for record user access log 用于记录用户访问日志的注释
 * @author: Dimple
 * @date: 2019/11/30
 */
@Target({ElementType.PACKAGE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VLog {
    /**
     * the module
     */
    String title() default "";

    /**
     * page Id
     */
    String pageId() default "";
}

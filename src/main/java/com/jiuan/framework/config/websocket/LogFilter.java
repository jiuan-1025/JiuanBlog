package com.jiuan.framework.config.websocket;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

/**
 * @className: LogFilter
 * @description:
 * @author: Dimple
 * @date: 11/05/19
 */
@Service
@Slf4j
public class LogFilter extends Filter<ILoggingEvent>{

    @Override
    public FilterReply decide(ILoggingEvent event) {
        
        StringBuilder exception = new StringBuilder();
        IThrowableProxy iThrowableProxy1 = event.getThrowableProxy();
        if (iThrowableProxy1 != null) {
            exception.append("<span class='excehtext'>" + iThrowableProxy1.getClassName() + " " + iThrowableProxy1.getMessage() + "</span></br>");
            for (int i = 0; i < iThrowableProxy1.getStackTraceElementProxyArray().length; i++) {
                exception.append("<span class='excetext'>" + iThrowableProxy1.getStackTraceElementProxyArray()[i].toString() + "</span></br>");
            }
        }
        LoggerMessage loggerMessage = new LoggerMessage(
//                event.getMessage(),
                event.getFormattedMessage(),
                DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())),
                event.getThreadName(),
                event.getLoggerName(),
                event.getLevel().levelStr,
                exception.toString(),
                ""
        );
        LoggerQueue.getInstance().push(loggerMessage);
        return FilterReply.ACCEPT;
    }

}

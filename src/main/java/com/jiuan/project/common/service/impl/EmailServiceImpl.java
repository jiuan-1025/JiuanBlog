package com.jiuan.project.common.service.impl;

import com.jiuan.common.constant.ConfigKey;
import com.jiuan.common.utils.StringUtils;
import com.jiuan.project.common.domain.ReplayEmail;
import com.jiuan.project.common.service.EmailService;
import com.jiuan.project.system.domain.EmailSetting;
import com.jiuan.project.system.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @className: EmailServiceImpl
 * @description: Email service
 * @author: Dimple
 * @date: 01/15/20
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private ConfigService configService;
    @Autowired
    private TemplateEngine templateEngine;


    //todo 发送email
    @Override
    public void sendHtmlMail(String to, String title, String content) {
        EmailSetting emailSetting = configService.selectConfigByConfigKey(ConfigKey.CONFIG_KEY_EMAIL_SETTING, EmailSetting.class);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(emailSetting.getUser());
        mailSender.setHost(emailSetting.getHost());
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setPassword(emailSetting.getPassword());
        mailSender.setPort(emailSetting.getPort());

        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailSetting.getHost());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
        properties.put("mail.smtp.port", emailSetting.getPort());
        properties.put("mail.smtp.socketFactory.port", emailSetting.getPort());
        properties.put("mail.smtp.ssl.enable", true);
        Session session = Session.getInstance(properties);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailSetting.getFromEmail());
            mimeMessageHelper.setTo(to);
            if (StringUtils.isNotEmpty(emailSetting.getStationmasterEmail())) {
                mimeMessageHelper.setBcc(emailSetting.getStationmasterEmail());
            }
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content, true);
            mailSender.setSession(session);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
           log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendReplyEmail(String url, String htmlContent, String nickName, String email, ReplayEmail replayEmail) {
        Context context = new Context();
        //回复的内容
        context.setVariables(replayEmail.toMap());
        String emailContent = templateEngine.process("/mail/ReplyEmail", context);
        String title = "JiuanBlog 留言回复通知!";
        sendHtmlMail(email, title, emailContent);
    }

    @Override
    public void sendLinkApplyResult(boolean success, String reason) {
        String title = "JiuanBlog 申请友链结果通知";
        String content = "";
    }

    public static void main(String[] args) {
        String to = "3489826731@qq.com";
        String title = "我是你叠";
        String content = "赶紧叫bb";

        EmailSetting emailSetting = new EmailSetting();
        emailSetting.setPassword("syuhnxipaycldggc");
        emailSetting.setFromEmail("2922545474@qq.com");
        emailSetting.setHost("smtp.qq.com");
        emailSetting.setPort(465);
        emailSetting.setUser("2922545474@qq.com");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(emailSetting.getUser());
        mailSender.setHost(emailSetting.getHost());
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setPassword(emailSetting.getPassword());
        mailSender.setPort(emailSetting.getPort());

        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailSetting.getHost());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
        properties.put("mail.smtp.port", emailSetting.getPort());
        properties.put("mail.smtp.socketFactory.port", emailSetting.getPort());
        properties.put("mail.smtp.ssl.enable", true);
        Session session = Session.getInstance(properties);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailSetting.getFromEmail());
            mimeMessageHelper.setTo(to);
            if (StringUtils.isNotEmpty(emailSetting.getStationmasterEmail())) {
                mimeMessageHelper.setBcc(emailSetting.getStationmasterEmail());
            }
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content, false);
            mailSender.setSession(session);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }
    }
}

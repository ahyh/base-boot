package com.yh.boot.boot.mail.service.impl;

import com.yh.boot.boot.mail.config.EmailConfig;
import com.yh.boot.boot.mail.service.EmailService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * EmailService实现
 *
 * @author yanhuan
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailConfig emailConfig;

    @Override
    public void sendEmail(String to, String subject, String htmlText) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            msgHelper.setFrom(emailConfig.getUsername());
            msgHelper.setTo(to);
            msgHelper.setSubject(subject);
            msgHelper.setText(htmlText, true);
            File file = new File("/tmp/hello.txt");
            FileUtils.write(file, "Hello World!", true);
            msgHelper.addAttachment("hello world.txt", file);
            javaMailSender.send(mimeMessage);
            file.delete();
        } catch (Exception e) {
            logger.error("EmailServiceImpl sendEmail error:{}", e);
            throw new RuntimeException("发送邮件失败!");
        }
    }
}

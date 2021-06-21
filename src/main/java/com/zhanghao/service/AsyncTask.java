package com.zhanghao.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghao
 * @data 2021/06/18
 */
@Slf4j
@Service
public class AsyncTask {

    @Value(value = "${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Async
    public void toSendMail(Exception e) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("Blog Exception Info");
        helper.setFrom(from);
        helper.setTo(from);
        Context context = new Context();
        context.setVariable("message",e.toString());
        String process = templateEngine.process("mail.html", context);
        helper.setText(process, true);
        javaMailSender.send(message);

    }

    @Async
    public void taskA() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        log.info("Thread name -> " + Thread.currentThread().getName());
    }
}
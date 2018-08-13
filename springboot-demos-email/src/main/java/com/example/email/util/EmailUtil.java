package com.example.email.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送工具类
 */
public class EmailUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    //邮箱主机
    private final static String EMAIL_HOST = "smtp.163.com";

    //发件人邮箱,你申请的邮箱
    private final static String FROM = "your@163.com";

    //网易邮箱授权码
    private final static String PASSWORD = "your_code";

    /**
     * @param email        收件人
     * @param validateCode 校验码
     * @Param content 邮件内容
     */
    public static void sendEmailMessage(String email, String validateCode) {

        try {
            Properties properties = System.getProperties();

            //设置邮箱服务器
            properties.put("mail.smtp.host", EMAIL_HOST);

            //设置session 这样才能通过验证
            properties.put("mail.smtp.auth", "true");
            BlogEmailAuthenticator blogEmailAuthenticator = new BlogEmailAuthenticator(FROM, PASSWORD);
            Session session = Session.getDefaultInstance(properties, blogEmailAuthenticator);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("博客系统邮件激活通知");

            message.setContent( "<a href=\"http://localhost:8080/activecode?email="+email+"&validateCode="+validateCode+"\" target=\"_blank\">请于24小时内点击激活</a>","text/html;charset=utf-8");
            message.saveChanges();

            Transport.send(message);

            LOGGER.info( "send validateCode to " + email);
        } catch (MessagingException e) {
            //e.printStackTrace();
            LOGGER.info( "Send Email Exception:"+e.getMessage());
        }

    }


}

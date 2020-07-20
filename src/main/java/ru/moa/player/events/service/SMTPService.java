package ru.moa.player.events.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
@Data
public class SMTPService {
    @Autowired
    private Environment env;

    private Properties props;

    private String userName;
    private String password;
    private String mailFrom;

    private boolean isTest;
    private String mailToTest;

    @PostConstruct
    private void init(){
        props = new Properties();

        //props.put("mail.store.protocol", "pop3");
        //props.put("mail.transport.protocol", "smtp");

        /*
        props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
        if (env.getProperty("mail.smtp.socketFactory.exists") != null && Boolean.parseBoolean(env.getProperty("mail.smtp.socketFactory.exists"))){
            props.put("mail.smtp.socketFactory.class", env.getProperty("mail.smtp.socketFactory.class"));
            props.put("mail.smtp.socketFactory.port", env.getProperty("mail.smtp.socketFactory.port"));
        }
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.userName") != null ? "true" : "false");
        props.put("mail.debug", "true");

        if (env.getProperty("mail.encoding") != null){
            props.put("mail.mime.charset", env.getProperty("mail.encoding"));
        }

         */


        props.put("mail.pop3.host", "pop.rambler.ru");
        props.put("mail.pop3.port", 995);

        // SSL setting
        props.setProperty("mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.socketFactory.port", String.valueOf(995));

        userName = env.getProperty("mail.smtp.userName");
        password = env.getProperty("mail.smtp.password");
        mailFrom = env.getProperty("mail.from");

        isTest = Boolean.parseBoolean(env.getProperty("mail.test"));
        if (env.getProperty("mail.test.to") != null){
            mailToTest = env.getProperty("mail.test.to");
        }
    }

    public Session getSession(){
        log.debug("props: {}", props);
        if (userName != null) {
            return Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(userName, password);
                        }
                    }
            );
        } else {
            return Session.getInstance(props);
        }
    }

    public void send(
            String subject,
            String text,
            String mailTo
    ) throws MessagingException {

        Session session = getSession();

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));

            if (isTest) {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailToTest));
            } else {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            }

            message.setSubject(subject);
            message.setText(text);

            //log.debug("message.getAllRecipients(): {}", message.getAllRecipients());
            log.debug("message: {}", message);

            Transport.send(message);

            log.debug("send mail done");

        } catch (MessagingException e) {
            log.error(e.getLocalizedMessage());
            throw new MessagingException(e.getMessage());
        }
    }

    public void send(
            String subject,
            String text,
            List<String> mailTo
    ) throws MessagingException {
        for (String item : mailTo) {
            send(subject, text, item);
        }
    }
}

package com.example.demo;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    private TemplateEngine templateEngine;

    @Autowired
    Environment env;

    @Autowired
    public EmailService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    private Properties GetProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));

        return props;

    }

    private Session GetSession() {

        //Email Account Credentials ( it will be supervisor's credentials )
        final String username = "apbootcamp2019@gmail.com";
        final String password = "java2019";

        //create session with username and passsword

        Session session = Session.getInstance(GetProperties(), new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        return session;
    }


    public String BuildTemplateWithContent(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailtemplate", context);
    }

    public void SendSimpleEmail (String recipient, String orderid) {
        try {
            Message message = new MimeMessage(GetSession());

            //email address you're sending from
            message.setFrom(new InternetAddress("apbootcamp2019"));

            //email address you're sending email to ( to user email )
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            //email  subject
            message.setSubject("Your order has been shipped.");

            //email content
            String content = "Your order number (" + orderid + ") has been shipped. \nThank you for shopping with us.";
            message.setText(content);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

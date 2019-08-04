package com.example.demo;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
        private String recipient;
        private String from;
        private String host;
        private String subject;
        private String message;


    public Email() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendEmail() {



        // Get system properties
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "localhost");
//        properties.setProperty("mail.smtp.port", "9000");
        properties.setProperty("mail.user", "apbootcamp2019@gmail.com");
        properties.setProperty("mail.password", "java2019");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            try {
                message.setFrom(new InternetAddress("apbootcamp2019@gmail.com"));
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("sueyoung.6311@gmail.com"));

            // Set Subject & text
            message.setSubject("This is the Subject Line!");
            message.setText("This is actual message");
            message.setDescription("This is description");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
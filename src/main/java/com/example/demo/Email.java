package com.example.demo;
/*
 * Java program to send email
 * Need to include below dependency in pom.xml
 * 		<dependency>
 * 			<groupId>org.springframework.boot</groupId>
 * 			<artifactId>spring-boot-starter-mail</artifactId>
 * 		</dependency>
 */

import org.springframework.stereotype.Service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

@Service
public class Email {

    public  void sendEmail(String recipient, String orderno)
    {
        // email ID of Recipient.
        //String recipient = "sueyoung.6311@gmail.com";

        // email ID of Sender.
        final String sender = "apbootcamp2019@gmail.com";

        // using host as localhost
        //String host = "127.0.0.1";
        String host="localhost";

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        try
        {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject("Order number " + orderno);

            // set body of the email.
            message.setText("Your order" + orderno + " has been shipped.\nThank you for shopping with us.");

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }
}

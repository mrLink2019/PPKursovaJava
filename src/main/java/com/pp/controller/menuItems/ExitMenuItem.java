package com.pp.controller.menuItems;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class ExitMenuItem extends MenuItem {
    private Logger logger = Logger.getLogger("generalLogger");
    private Logger errorLogger = Logger.getLogger("errorLogger");

    @Override
    public void execute() {
        System.out.println("Вихід з програми...");
        logger.info("called application exit");
        sendErrors();
        System.exit(0);
    }

    private void sendErrors() {
        try {
            logger.warn("trying to send errors log to email");
            File errorsFile =  new File(getClass().getClassLoader().getResource("logs/errors.log").toURI());
            if(errorsFile.exists() && errorsFile.length() != 0) {
                String receiverMail = "";
                final String username = "";
                final String password = "";

                Properties properties = System.getProperties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.starttls.enable", "true");
                //properties.put("mail.smtp.ssl.enable", "false");

                Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(receiverMail));
                    message.setSubject("Errors in PPLab78");

                    BodyPart messageBodyPart1 = new MimeBodyPart();
                    messageBodyPart1.setText("There are some errors in your project:");

                    MimeBodyPart messageBodyPart2 = new MimeBodyPart();
                    //String filename = errorsFile.getName();
                    DataSource source = new FileDataSource(errorsFile);
                    messageBodyPart2.setDataHandler(new DataHandler(source));
                    messageBodyPart2.setFileName("errors.log");

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart1);
                    multipart.addBodyPart(messageBodyPart2);
                    message.setContent(multipart );
                    Transport.send(message);
                    logger.warn("successfully sent errors log to email");
                } catch (MessagingException ex) {
                    errorLogger.error(ex.toString());
                    System.out.println(ex.getMessage());
                }
            } else {
                logger.warn("there are no errors");
            }
        } catch (Exception ex) {
            errorLogger.error(ex.toString());
            System.out.println(ex.getMessage());
        }
    }
}
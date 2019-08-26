package com.example.awssestest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@SpringBootApplication
public class AwsSesTestApplication {

    private final MailSender mailSender;

    @Autowired
    public AwsSesTestApplication(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AwsSesTestApplication.class, args);
        try {
            doSendEmailWith(context);
        } finally {
            context.close();
        }
    }

    private static void doSendEmailWith(ApplicationContext context) {
        MailSender sender = context.getBean(AwsSesTestApplication.class).mailSender;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("akashbpatil204@gmail.com");
        message.setTo("akashbpatil204@gmail.com");
        Double x=((Math.random()*(10000-1000))+1000);
        String y=Double.toString(x);
        message.setSubject("Hello from Sapient");
        message.setText(y);
        sender.send(message);
        System.out.println("Message Sent");
    }
}

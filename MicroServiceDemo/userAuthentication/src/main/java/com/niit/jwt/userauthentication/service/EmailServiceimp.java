package com.niit.jwt.userauthentication.service;

import com.niit.jwt.userauthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceimp implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    @Override
    public String sendSimpleMail(User user) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
            mailMessage.setText("Congratulation...!!! You Have SuccessFully Register POPCORN BUDDY Website !! ");
            mailMessage.setSubject("POPCORN BUDDY Website Registered !!");
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (Exception e) {return "Error while Sending Mail"; }
    }
}

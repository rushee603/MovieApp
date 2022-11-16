package com.niit.jwt.userauthentication.service;


import com.niit.jwt.userauthentication.model.User;

public interface EmailService {

    String sendSimpleMail(User user);

}

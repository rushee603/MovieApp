package com.niit.jwt.userauthentication.service;

import com.niit.jwt.userauthentication.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String,String> generateToken(User user);
}

package com.niit.jwt.userauthentication.service;

import com.niit.jwt.userauthentication.exception.UserAlreadyExistsException;
import com.niit.jwt.userauthentication.exception.UserNotFoundException;
import com.niit.jwt.userauthentication.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;

    User updateUser(String email,User user) throws UserNotFoundException;

    User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException;

    String getEmailBasedOnCredentials(String userName, String password);

//    boolean deleteUser(String userName) throws UserNotFoundException;

//    public User getUser(String userName) throws UserNotFoundException;

}

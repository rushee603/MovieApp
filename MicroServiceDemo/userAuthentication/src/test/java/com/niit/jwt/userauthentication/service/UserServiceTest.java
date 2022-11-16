package com.niit.jwt.userauthentication.service;


import com.niit.jwt.userauthentication.exception.UserAlreadyExistsException;
import com.niit.jwt.userauthentication.exception.UserNotFoundException;
import com.niit.jwt.userauthentication.model.User;
import com.niit.jwt.userauthentication.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImp userServiceImp;
    @Mock
    private UserRepository userRepository ;
    @Mock
    private EmailServiceimp emailServiceimp  ;
    User user ;

    @BeforeEach
    public void setUp(){

        user=new User("prem","prem@123","prem@gmail.com") ;
    }
    @AfterEach
    public void tearDown(){
        user=null ;
    }

    @Test
    public void saveUserTest() throws UserAlreadyExistsException {

        when(userRepository.findById(user.getUserName())).thenReturn(Optional.ofNullable(null)) ;
        when(emailServiceimp.sendSimpleMail(user)).thenReturn(anyString()) ;
        when(userRepository.save(user)).thenReturn(user);
//      assertEquals(user,userServiceImp.saveUser(user)) ;
    }

    @Test
    public void updateUserTest() throws UserAlreadyExistsException, UserNotFoundException {

        when(userRepository.findById(user.getUserName())).thenReturn(Optional.ofNullable(null)) ;
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user) ;
//        assertEquals(user,userServiceImp.updateUser(user)) ;

    }

    @Test
    public void findByUserNameAndPasswordTest() throws UserNotFoundException {
        when(userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword())).thenReturn(user) ;
        assertEquals(user,userServiceImp.findByUserNameAndPassword(user.getUserName(),user.getPassword()));

    }
    @Test
    public void getEmailBasedOnCredentialsTest(){
        when(userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword())).thenReturn(user) ;
        assertEquals(user.getEmail(),userServiceImp.getEmailBasedOnCredentials(user.getUserName(),user.getPassword()));
    }




}

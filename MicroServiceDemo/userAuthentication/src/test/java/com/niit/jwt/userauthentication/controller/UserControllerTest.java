package com.niit.jwt.userauthentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.jwt.userauthentication.exception.UserAlreadyExistsException;
import com.niit.jwt.userauthentication.exception.UserNotFoundException;
import com.niit.jwt.userauthentication.model.User;
import com.niit.jwt.userauthentication.service.EmailServiceimp;
import com.niit.jwt.userauthentication.service.UserServiceImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController controller;

    @Mock
    private UserServiceImp serviceImp;
    @Mock
    private EmailServiceimp emailServiceimp;
    MockMvc mockMvc;
    User user;
    private ObjectMapper objectMapper =new ObjectMapper() ;

    @BeforeEach
    public  void setup()
    {
        user=new User("Sathya","Sathya@18","sathya@gmail.com") ;
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }
    @AfterEach
    public void tearDown(){
        user=null ;
    }

    @Test
    public void registerTest() throws Exception {
        when(serviceImp.saveUser(any())).thenReturn(user);
//        when(emailServiceimp.sendSimpleMail(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk()) ;
    }

//    @Test
//    public void updateUserTest() throws UserNotFoundException {
//        assertEquals(user,serviceImp.updateUser(user));
//    }

//    @Test
//    public void getMailTest(){
//        when(serviceImp.getEmailBasedOnCredentials(user.getUserName(),user.getPassword())).thenReturn(user.getEmail()) ;
//        assertEquals(user.getEmail(),controller.getEmailBasedOnUserNameAndPassword(user));
//    }

}

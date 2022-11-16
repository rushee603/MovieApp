package com.niit.jwt.userauthentication.service;

import com.niit.jwt.userauthentication.exception.UserAlreadyExistsException;
import com.niit.jwt.userauthentication.exception.UserNotFoundException;
import com.niit.jwt.userauthentication.model.User;
import com.niit.jwt.userauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private EmailService emailService;
    @Autowired
    public UserServiceImp(UserRepository userRepository, EmailService emailService){
        this.userRepository=userRepository;
        this.emailService=emailService ;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getUserName()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        String status = emailService.sendSimpleMail(user);

        User user1= userRepository.save(user);
        return user1 ;
    }


    @Override
    public User updateUser(String email,User user) throws UserNotFoundException {
        User newUser=userRepository.findByEmail(email) ;
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return newUser;
    }


    @Override
    public User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
        User user= userRepository.findByUserNameAndPassword(userName,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public String getEmailBasedOnCredentials(String userName, String password) {
        User user= userRepository.findByUserNameAndPassword(userName,password);
        System.out.println(" Received user is ********************************** "+user);
        return user.getEmail();
    }

//    @Override
//    public boolean deleteUser(String userName) throws UserNotFoundException {
//        boolean flag =false ;
//        if(userRepository.findById(userName).isEmpty()){
//            throw new UserNotFoundException();
//        }
//
//        userRepository.deleteById(userName);
//        User user=userRepository.findByUserName(userName) ;
//        if(user==null){
//            flag=true ;
//        }
//        else{
//            flag=false ;
//        }
//        System.out.println(flag);
//        return flag ;
//    }

//    @Override
//    public User getUser(String userName) throws UserNotFoundException {
//        User user ;
//        if(userRepository.findById(userName).isEmpty()){
//            throw new UserNotFoundException();
//        }
//        user=userRepository.findByUserName(userName) ;
//        return user ;
//    }

//    @Override
//    public User getUser(String email) throws UserNotFoundException {
//        User user ;
//        if(userRepository.findById(email).isEmpty()){
//            throw new UserNotFoundException();
//        }
//        user=userRepository.findByEmail(email) ;
//        System.out.println(user.getEmail()+"!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        return user;
//    }


}

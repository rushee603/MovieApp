package com.niit.jwt.userauthentication.controller;
import com.niit.jwt.userauthentication.exception.UserAlreadyExistsException;
import com.niit.jwt.userauthentication.exception.UserNotFoundException;
import com.niit.jwt.userauthentication.model.User;
import com.niit.jwt.userauthentication.service.EmailService;
import com.niit.jwt.userauthentication.service.SecurityTokenGenerator;
import com.niit.jwt.userauthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ResponseEntity responseEntity;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    private EmailService emailService;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        User createUser = userService.saveUser(user);
//        String status = emailService.sendSimpleMail(user);
        return new ResponseEntity(createUser, HttpStatus.OK);
    }


    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email ,@RequestBody User user) {
        try {
            responseEntity = new ResponseEntity<>(userService.updateUser(email,user), HttpStatus.ACCEPTED);
        } catch (UserNotFoundException e) {
            System.out.println("Throwing userNotFound Exception");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Throwing Exception");
            System.out.println(e.getMessage());
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody User user) throws UserNotFoundException {

        Map<String, String> map = null;
        try {
            User userObj = userService.findByUserNameAndPassword(user.getUserName(), user.getPassword());
            if (userObj.getUserName().equals(user.getUserName())) {
                map = securityTokenGenerator.generateToken(userObj);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Try After SomeTime...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/getemail")
    public ResponseEntity<?> getEmailBasedOnUserNameAndPassword(@RequestBody User user) {
        return new ResponseEntity<>(userService.getEmailBasedOnCredentials(user.getUserName(), user.getPassword()), HttpStatus.OK);
    }

//    @DeleteMapping("/delete-user/{userName}")
//    public ResponseEntity<?> deleteUser(@PathVariable String userName) {
//        try {
//            responseEntity = new ResponseEntity<>(userService.deleteUser(userName), HttpStatus.OK);
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return responseEntity;
//
//    }

//    @GetMapping("/get/user/{userName}")
//    public ResponseEntity<?> getUserBasedOnUserName(@PathVariable String userName){
//        try{
//            responseEntity=new ResponseEntity<>(userService.getUser(userName),HttpStatus.FOUND) ;
//        }
//        catch (UserNotFoundException e) {
//            e.printStackTrace();
//        }
//        return responseEntity ;
//    }

}

package com.niit.usermovieservice.proxy;
import com.niit.usermovieservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="USER-AUTHENTICATION-SERVICE")
public interface UserAuthProxy {

    @PostMapping("/user/register")
     ResponseEntity<?> saveUser(@RequestBody User user);

    @PutMapping("/user/update/{email}")
     ResponseEntity<?> updateUser(@PathVariable String email,@RequestBody User user);

    @DeleteMapping("/delete-user/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName) ;

//    @GetMapping("/get/user/{userName}")
//    public ResponseEntity<?> getUserBasedOnUserName(@PathVariable String userName) ;
}

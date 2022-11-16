package com.niit.usermovieservice.controller;
import com.niit.usermovieservice.exception.MovieAlreadyExistsException;
import com.niit.usermovieservice.exception.UserAlreadyExistException;
import com.niit.usermovieservice.exception.UserNotFoundException;
import com.niit.usermovieservice.model.Movie;
import com.niit.usermovieservice.model.User;
import com.niit.usermovieservice.service.UserMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usermovie")
public class UserController {

    private UserMovieService userService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserMovieService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistException {
        try{
            User newUser=userService.registerUser(user) ;
            responseEntity = new ResponseEntity<>(newUser,HttpStatus.OK);
        }
        catch (UserAlreadyExistException e){
            throw new UserAlreadyExistException();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return responseEntity;
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email,@RequestBody User user) throws UserNotFoundException{
        try {
            responseEntity = new ResponseEntity<>(userService.updateUser(email,user), HttpStatus.ACCEPTED);
        }
        catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return responseEntity;
    }


    @PostMapping("/favouritelist/{email}")
    public ResponseEntity<?> addMovieToFavouriteList(@RequestBody Movie movie, @PathVariable String email) throws UserNotFoundException, MovieAlreadyExistsException {
        try{
            responseEntity=new ResponseEntity<>(userService.saveUserMoviesToList(movie, email),HttpStatus.ACCEPTED) ;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (MovieAlreadyExistsException e){
            System.out.println("Throwing MovieAlreadyExistsException");
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.ALREADY_REPORTED) ;
            throw new MovieAlreadyExistsException() ;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Ending controller method $$$$$$$$$$$$$$$$$$$$$$$$$");
        return responseEntity;
    }

    @GetMapping("/favouritemovielist/{email}")
    public ResponseEntity<?> getAllFavouriteMoviesOfAUser(@PathVariable String email) throws UserNotFoundException {

        try{
            responseEntity=new ResponseEntity<>(userService.getAllUserMovies(email),HttpStatus.OK);

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return responseEntity;
    }

    @DeleteMapping("/delete/{email}/{id}")
    public ResponseEntity<?> deleteMovieFromFavouriteList(@PathVariable String email,@PathVariable int id) throws UserNotFoundException {
        userService.deleteMovie(email,id) ;
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    @GetMapping("/get/user/{email}")
    public ResponseEntity<?> getUserBasedOnEmailId(@PathVariable String email){
        try{
            responseEntity=new ResponseEntity<>(userService.getUser(email),HttpStatus.OK) ;
        }
        catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return responseEntity ;
    }

//    @DeleteMapping("/delete-user/{email}")
//    public ResponseEntity<?> deleteAccount(@PathVariable String email) {
//        try{
//            responseEntity=new ResponseEntity<>(userService.deleteUser(email),HttpStatus.OK) ;
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//        }
//        return responseEntity ;
//    }

}

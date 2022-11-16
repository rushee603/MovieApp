package com.niit.usermovieservice.service;

import com.niit.usermovieservice.exception.MovieAlreadyExistsException;
import com.niit.usermovieservice.exception.UserAlreadyExistException;
import com.niit.usermovieservice.exception.UserNotFoundException;
import com.niit.usermovieservice.model.Movie;
import com.niit.usermovieservice.model.User;
import com.niit.usermovieservice.proxy.UserAuthProxy;
import com.niit.usermovieservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserMovieServiceImp implements UserMovieService
{
    private UserRepository userRepository;
    private UserAuthProxy userAuthProxy;

    @Autowired
    public UserMovieServiceImp(UserRepository userRepository,UserAuthProxy userAuthProxy){
        this.userRepository=userRepository;
        this.userAuthProxy=userAuthProxy;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }

        userAuthProxy.saveUser(user);
        return userRepository.save(user) ;
    }

    @Override
    public User updateUser(String email,User user) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user1=userRepository.findByEmail(email);
        user1.setPassword(user.getPassword());
        user1.setPhoneNo(user.getPhoneNo());
        ResponseEntity<?> responseEntity=userAuthProxy.updateUser(email,user);
        if(responseEntity.getStatusCode().value()==202){
            return userRepository.save(user1);
        }
       else {
           return null;
        }

    }

    @Override
    public User saveUserMoviesToList(Movie movie, String email) throws UserNotFoundException, MovieAlreadyExistsException {

        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user=userRepository.findByEmail(email);

        if(user.getFavMovieList()==null){
            user.setFavMovieList(Arrays.asList(movie));
        }
        else{
            List<Movie> movies=user.getFavMovieList();

            if(movies.removeIf(x->x.getId()==movie.getId())){
                System.out.println("Having Duplicate Movies***********************");
                throw new MovieAlreadyExistsException() ;
            }
            else{
                System.out.println("Not Having Duplicate Movies***********************");
                movies.add(movie);
                user.setFavMovieList(movies);
            }
        }
        System.out.println("Method is ending***********************");
        return userRepository.save(user);
    }

    @Override
    public List<Movie> getAllUserMovies(String email) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(email).get().getFavMovieList();
    }

    List<Movie> favMovieList=new ArrayList<>() ;

    @Override
    public void deleteMovie(String email, int id) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user=userRepository.findByEmail(email);
        favMovieList=getAllUserMovies(email) ;
        System.out.println("Before removing*************************** ");
        System.out.println(favMovieList);
        favMovieList.removeIf(obj->obj.getId()==id);
        user.setFavMovieList(favMovieList);
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) throws UserNotFoundException {
        User user ;
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }

        user=userRepository.findByEmail(email) ;

        System.out.println(user.getEmail()+"!!!!!!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return user;
    }

//    @Override
//    public boolean deleteUser(String email) throws UserNotFoundException {
//        boolean flag =false ;
//        if(userRepository.findById(email).isEmpty()){
//            throw new UserNotFoundException();
//        }
//
//        User user=userRepository.findByEmail(email) ;
//        userRepository.deleteById(email);
//        userAuthProxy.deleteUser(user.getUserName()) ;
//
//        User user1=userRepository.findByEmail(email) ;
//        if(user1==null){
//            flag=true ;
//        }
//        else{
//            flag= false ;
//        }
//        System.out.println(flag+"####################################");
//        return flag ;
//    }

}

package com.niit.usermovieservice.service;


import com.niit.usermovieservice.exception.MovieAlreadyExistsException;
import com.niit.usermovieservice.exception.UserAlreadyExistException;
import com.niit.usermovieservice.exception.UserNotFoundException;
import com.niit.usermovieservice.model.Movie;
import com.niit.usermovieservice.model.User;

import java.util.List;

public interface UserMovieService {

    User registerUser(User user) throws UserAlreadyExistException;

    User updateUser(String email,User user) throws UserNotFoundException;

    User saveUserMoviesToList(Movie movie, String email) throws UserNotFoundException, MovieAlreadyExistsException;

    List<Movie> getAllUserMovies(String email) throws UserNotFoundException;

    public void deleteMovie(String email, int movieId) throws UserNotFoundException;

    public User getUser(String user) throws UserNotFoundException;

//    public boolean deleteUser(String email) throws UserNotFoundException;

}

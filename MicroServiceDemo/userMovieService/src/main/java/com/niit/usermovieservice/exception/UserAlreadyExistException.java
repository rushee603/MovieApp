package com.niit.usermovieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "User Already Exist")
public class UserAlreadyExistException extends Exception{
}

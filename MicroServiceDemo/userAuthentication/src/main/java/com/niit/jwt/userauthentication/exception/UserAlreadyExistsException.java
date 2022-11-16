package com.niit.jwt.userauthentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "User Already Exists")
public class UserAlreadyExistsException extends Exception{
}

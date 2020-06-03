package com.stackhack.taskmanagement.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stackhack.taskmanagement.exception.SignUpException;
import com.stackhack.taskmanagement.response.Response;

@RestControllerAdvice
public class SignUpExceptionHandler {
	
	@ExceptionHandler({SignUpException.class})
    public ResponseEntity<Response> notFound(SignUpException ex){
        return new ResponseEntity<Response>(
            new Response(ex.getMessage(), 404) , HttpStatus.NOT_FOUND);
    }
}

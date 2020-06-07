package com.stackhack.taskmanagement.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stackhack.taskmanagement.exception.AllException;
import com.stackhack.taskmanagement.response.Response;

@RestControllerAdvice
public class AllExceptionHandler {
	
	@ExceptionHandler({AllException.class})
    public ResponseEntity<Response> notFound(AllException ex){
        return new ResponseEntity<Response>(
            new Response(ex.getMessage(), 404) , HttpStatus.NOT_FOUND);
    }
}

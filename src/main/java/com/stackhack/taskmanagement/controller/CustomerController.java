package com.stackhack.taskmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.exception.SignUpException;
import com.stackhack.taskmanagement.response.Response;
import com.stackhack.taskmanagement.service.CustomerService;

@CrossOrigin
@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping(value="/signup")
	public ResponseEntity<?> saveContactDetails(@Valid @RequestBody Customer customer) {
		try {
			customerService.signUp(customer);
			Response success=new Response("Successfull",200);
			return new ResponseEntity<Response>(success,HttpStatus.OK);
		}
		catch (Exception e){
			throw new SignUpException("Failure");
		}
	}
}

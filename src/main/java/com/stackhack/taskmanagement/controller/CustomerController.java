package com.stackhack.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
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
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ResponseEntity<?> saveSignUpDetails(@Valid @RequestBody Customer customer) {
		try {
			boolean flag=false;
			ResponseEntity<Response> apiresponse=null;
			flag=customerService.signUp(customer);
			System.out.println(flag);
			if(flag==true) {
				Response success=new Response("Successfull",200);
				apiresponse= new ResponseEntity<Response>(success,HttpStatus.OK);
			}
			else if(flag==false) {
				Response emailexists=new Response("Email Already Exists",409);
				apiresponse= new ResponseEntity<Response>(emailexists,HttpStatus.CONFLICT);
			}
			return apiresponse;
		}
		catch (Exception e){
			throw new SignUpException("Failure");
		}
	}
}

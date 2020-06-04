package com.stackhack.taskmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.entity.Tasks;
import com.stackhack.taskmanagement.exception.SignUpException;
import com.stackhack.taskmanagement.response.Response;
import com.stackhack.taskmanagement.service.CustomerService;



@CrossOrigin(origins="*")
@RestController
public class CustomerController {
	
	@Autowired

	CustomerService customerService;
	
	
	
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<?> saveContactDetails(@Valid @RequestBody Customer customer) {
		try {
			Customer cust = customerService.FindUserbyEmail(customer.getEmail());
			
			
			if(cust != null)
			{
				Response conflict=new Response("Email already exists", 409);
				return new ResponseEntity<Response>(conflict,HttpStatus.CONFLICT);
			}
			else {
			customerService.signUp(customer);
			Response success=new Response("Successfull",200);
			return new ResponseEntity<Response>(success,HttpStatus.OK);
			}
		}
		
		catch (Exception e){
			throw new SignUpException("Failure");
		}
	}
	@RequestMapping(value="/login/{email}/{password}")
	public ResponseEntity<?> LogIn(@PathVariable String email, @PathVariable String password)
	{
		
		try {
		Customer cust = customerService.FindUserbyEmail(email);
		
		if(cust == null)
		{
			Response notfound = new Response("email not found", 404);
			return new ResponseEntity<Response>(notfound,HttpStatus.NOT_FOUND);
		}
		
		if(!cust.getPassword().equals(password))
		{
			Response notauth = new Response("password wrong", 404);
			customerService.Login(email, password);
			return new ResponseEntity<Response>(notauth,HttpStatus.NOT_FOUND);
		}
		else
		{
			
			//Response success=new Response("Successfull",200);
			List<Tasks> task =	customerService.Login(email, password);
			return new ResponseEntity<List<Tasks>>(task, HttpStatus.OK);
		}
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");
		}
		
		
	}
	
}

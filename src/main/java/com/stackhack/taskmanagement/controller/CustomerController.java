package com.stackhack.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.service.CustomerService;
@CrossOrigin
@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	@RequestMapping(method = RequestMethod.POST,  value="/signup")
	public void SingUp(@RequestBody Customer customer)
	{
		customerservice.SignUp(customer);
	}
}

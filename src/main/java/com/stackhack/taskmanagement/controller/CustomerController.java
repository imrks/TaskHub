package com.stackhack.taskmanagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.service.CustomerService;
@CrossOrigin("*")
@RestController
public class CustomerController {
	
	CustomerService customerservice;
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public void SingUp(Customer customer)
	{
		customerservice.SignUp(customer);
	}
}

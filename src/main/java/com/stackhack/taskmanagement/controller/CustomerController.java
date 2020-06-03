package com.stackhack.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.repo.CustomerRepo;

@CrossOrigin
@RestController
public class CustomerController {
	@Autowired
	CustomerRepo custRepo;
	
	@PostMapping(value="/signup")
	public void signUp(@RequestBody Customer customer) {
		try {
			custRepo.save(customer);
		}
		catch (Exception e){
			return;
		}
	}
}

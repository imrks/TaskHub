package com.stackhack.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerrepo;
	
	public void SignUp(Customer customer)
	{
		System.out.println(customer.getName());
		customerrepo.save(customer);
	}
}

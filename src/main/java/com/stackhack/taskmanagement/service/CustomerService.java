package com.stackhack.taskmanagement.service;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.repo.CustomerRepo;

public class CustomerService {

	CustomerRepo customerrepo;
	public void SignUp(Customer customer)
	{
		customerrepo.save(customer);
	}
}

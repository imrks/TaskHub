package com.stackhack.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo custRepo;
	public void signUp(Customer customer) {
		try {
			custRepo.save(customer);
		}
		catch (Exception e){
			return;
		}
	}
}
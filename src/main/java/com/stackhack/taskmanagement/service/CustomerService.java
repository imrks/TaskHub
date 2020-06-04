package com.stackhack.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo custRepo;
	public boolean signUp(Customer customer) {
		boolean flag=false;
		try {
			Customer c=custRepo.findByEmail(customer.getEmail());
			if(custRepo.findByEmail(customer.getEmail())==null) {
				custRepo.save(customer);
				flag=true;
			}
			else if(custRepo.findByEmail(customer.getEmail())!=null) {
				flag=false;
			}
		}
		catch (Exception e){
		}
		return flag;
	}
}
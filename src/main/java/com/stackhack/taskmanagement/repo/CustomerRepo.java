package com.stackhack.taskmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackhack.taskmanagement.entity.Customer;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository <Customer, Long> {
	public Customer findCustomerByid(long id);
}

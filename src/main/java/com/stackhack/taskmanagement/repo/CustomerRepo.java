package com.stackhack.taskmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackhack.taskmanagement.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}

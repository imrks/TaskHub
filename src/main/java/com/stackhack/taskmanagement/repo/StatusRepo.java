package com.stackhack.taskmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackhack.taskmanagement.entity.Status;


public interface StatusRepo extends JpaRepository<Status, Long> {
	public Status findStatusById(long id);

}

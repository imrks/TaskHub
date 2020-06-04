package com.stackhack.taskmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackhack.taskmanagement.entity.Tasks;

public interface TasksRepo extends JpaRepository<Tasks, Long> {
	public List<Tasks> findAllTaskByCustomer_id(long cid);
}

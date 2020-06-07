package com.stackhack.taskmanagement.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stackhack.taskmanagement.entity.Tasks;

public interface TasksRepo extends JpaRepository<Tasks, Long>, JpaSpecificationExecutor<Tasks> {
	public Tasks findById(long id);
	public void deleteById(long id);
	
	

}

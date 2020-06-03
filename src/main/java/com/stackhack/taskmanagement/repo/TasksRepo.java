package com.stackhack.taskmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackhack.taskmanagement.entity.Tasks;

public interface TasksRepo extends JpaRepository<Tasks, Long> {

}

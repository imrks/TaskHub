package com.stackhack.taskmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackhack.taskmanagement.entity.Label;


public interface LabelRepo extends JpaRepository<Label, Long> {

}

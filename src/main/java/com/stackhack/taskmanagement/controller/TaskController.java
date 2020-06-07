package com.stackhack.taskmanagement.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackhack.taskmanagement.entity.Label;
import com.stackhack.taskmanagement.entity.Status;
import com.stackhack.taskmanagement.entity.Tasks;
import com.stackhack.taskmanagement.exception.SignUpException;
import com.stackhack.taskmanagement.service.CustomerService;


@CrossOrigin(origins="*")
@RestController
public class TaskController {


	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/gettask/{customer_id}", method = RequestMethod.GET)
	public ResponseEntity<?> myTasks(@PathVariable long customer_id)
	{
		try {
		List<Tasks> task=customerService.myTaskLists(customer_id);
		return new ResponseEntity<List<Tasks>>(task, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");
		}
	}
	
	@RequestMapping(value="/addtask/{customer_id}/{status_id}/{label_id}", method = RequestMethod.POST)
	public ResponseEntity<?> AddTask(@RequestBody Tasks task, @PathVariable long customer_id, @PathVariable long status_id, @PathVariable long label_id)
	{
		try {
			if(status_id == 3)
			{
				return new ResponseEntity<>("status cannot be complete", HttpStatus.NOT_ACCEPTABLE);
			}
			else {	
			customerService.AddTask(task,customer_id,status_id,label_id);
			return new ResponseEntity<>("success", HttpStatus.OK);
			}
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		}
	}
	
	@RequestMapping(value="/edittask/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> EditTask(@RequestBody Tasks task, @PathVariable long id)
	{
		customerService.EditTask(task,id);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@RequestMapping(value="/getlabel")
	public ResponseEntity<?> GetLabel()
	{
		try {
			
		List<Label> label =	customerService.GetLabel();
		return new ResponseEntity<List<Label>>(label, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
			}
	}
	
	@RequestMapping(value="/getstatus")
	public ResponseEntity<?> GetStatus()
	{
		try {
			
		List<Status> status =	customerService.GetStatus();
		return new ResponseEntity<List<Status>>(status, HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
		}
	
	}
	@RequestMapping(value="/deletetask/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> DeleteTask(@PathVariable long id)
	{
		try {
		customerService.deleteTask(id);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
		}
		
	}
}

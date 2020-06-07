package com.stackhack.taskmanagement.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.stackhack.taskmanagement.entity.Customer;
import com.stackhack.taskmanagement.entity.Label;
import com.stackhack.taskmanagement.entity.Status;
import com.stackhack.taskmanagement.entity.Tasks;
import com.stackhack.taskmanagement.exception.SignUpException;
import com.stackhack.taskmanagement.repo.CustomerRepo;
import com.stackhack.taskmanagement.repo.LabelRepo;
import com.stackhack.taskmanagement.repo.StatusRepo;
import com.stackhack.taskmanagement.repo.TasksRepo;


@Service
public class CustomerService {

	@Autowired
	CustomerRepo custRepo;
	@Autowired
	TasksRepo taskRepo;
	@Autowired
	StatusRepo statusRepo;
	@Autowired
	LabelRepo labelRepo;
	
	public Customer FindUserbyEmail(String email) {
		// TODO Auto-generated method stub
		try {
		return custRepo.findCustomerByEmail(email);
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
			}
	}
	
	public void signUp(Customer customer) {
		try {
			custRepo.save(customer);
		}
		
		catch (Exception e){
			throw new SignUpException("Failure");
		}
	}
	
	
	public List<Tasks> myTaskLists(long cid, Long status_id, Long label_id, Date duedate) {
		// TODO Auto-generated method stub
	try {
				
				List<Tasks> task = taskRepo.findAll(
					 new Specification<Tasks>(){

					@Override
					public Predicate toPredicate(Root<Tasks> root, CriteriaQuery<?> cq,
							CriteriaBuilder cb) {
						// TODO Auto-generated method stub
						Predicate p = cb.conjunction();
						p = cb.and(p, cb.equal(root.get("customer"), cid));
						if(Objects.nonNull(duedate))
						{
							System.out.println(duedate);
								p = cb.and(p, cb.equal(root.get("dueDate"), duedate ));
							 
							
							
							
						}
						
						if(Objects.nonNull(status_id))
						{
							
							p = cb.and(p, cb.equal(root.get("status"), status_id));
						}
						if(Objects.nonNull(label_id))
						{
							p = cb.and(p, cb.equal(root.get("label"), label_id));
						}
						return p;
						
					}
				});
				return task;
		 
			}
			catch(Exception e)
			{
				throw new SignUpException("Failure");
			}
	}

	public void AddTask(Tasks task, long customer_id, long status_id, long label_id) {
		// TODO Auto-generated method stub
		try {
		task.getLabel().setId(label_id);
		task.getStatus().setId(status_id);
		task.getCustomer().setId(customer_id);
		taskRepo.save(task);
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
			}
	}

	public void EditTask(Tasks task, long id) {
		// TODO Auto-generated method stub
		try {
		Tasks task1 = taskRepo.findById(id);
		Status status = statusRepo.findStatusById(task.getStatus().getId());
		Label label = labelRepo.findLabelById(task.getLabel().getId());
		task1.setDate(task.getDate());
		task1.setDueDate(task.getDueDate());
		task1.setTask_desc(task.getTask_desc());
		task1.setStatus(status);
		task1.setLabel(label);
		if(task.getStatus().getId() == 3)
		{
			task1.setArchiveStatus(true);
		}
		
		taskRepo.save(task1);
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
			}
	}
	public void deleteTask(long id)
	{
		try {
		taskRepo.deleteById(id);
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
			}
	}

	public List<Label> GetLabel() {
		// TODO Auto-generated method stub
		try {
		return labelRepo.findAll();
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
			}
	}
	public List<Status> GetStatus()
	{
		try {
		return statusRepo.findAll();
		}
		catch(Exception e)
		{
			throw new SignUpException("Failure");		
			}
	}
}
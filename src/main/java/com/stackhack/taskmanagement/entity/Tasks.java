package com.stackhack.taskmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "Task")
public class Tasks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message="Task Desc should not be empty")
	@Column(name = "task_desc")
	private String task_desc;
	
	@Column(name="Date")
	private Date Date;
	
	@Column(name="Due_Date")
	private Date dueDate;
	
	@Column(name="archive_status",columnDefinition="BOOLEAN DEFAULT false")
	private boolean archiveStatus;
	
	@Column(name="overdue_status",columnDefinition="BOOLEAN DEFAULT false")
	private boolean overdueStatus;
	
	@ManyToOne
	Status status;
	
	@ManyToOne
	Label label;
	
	@ManyToOne
	Customer customer;
	
	public Tasks(long id, @NotEmpty(message = "Task Desc should not be empty") String task_desc, Date date,
			Date dueDate, boolean archiveStatus, boolean overdueStatus, Status status, Label label,
			Customer customer) {
		super();
		this.id = id;
		this.task_desc = task_desc;
		Date = date;
		this.dueDate = dueDate;
		this.archiveStatus = archiveStatus;
		this.overdueStatus = overdueStatus;
		this.status = status;
		this.label = label;
		this.customer = customer;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTask_desc() {
		return task_desc;
	}

	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(boolean archiveStatus) {
		this.archiveStatus = archiveStatus;
	}

	public boolean isOverdueStatus() {
		return overdueStatus;
	}

	public void setOverdueStatus(boolean overdueStatus) {
		this.overdueStatus = overdueStatus;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}

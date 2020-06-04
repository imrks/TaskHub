package com.stackhack.taskmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message="Name should not be empty")
	@Size(min=2, message="Name should have atleast 2 characters")
	@Column(name = "name")
	private String name;
	
	@NotEmpty(message="Email should not be empty")
	@Email(regexp="^(.+)@(.+)$",message="Invalid Email Pattern")
	@Column(name = "email")
	private String email;
	
	@NotEmpty(message="Password should not be empty")
	@Column(name = "password")
	@Size(min=6, message="Password should have atleast 6 characters")
	private String password;
	public Customer() {
		
	}
	
	public Customer(long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

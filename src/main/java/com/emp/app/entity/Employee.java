package com.emp.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Size(max = 20, min = 3, message = "Invalid name. Size should be between 3 to 20.")
    @NotEmpty(message = "Please enter your name.")
	@Column(name = "first_name")
	private String firstName;
	
	@Size(max = 20, min = 3, message = "Invalid name. Size should be between 3 to 20.")
    @NotEmpty(message = "Please enter your name.")
	@Column(name = "last_name")
	private String lastName;
	
	@Email(message = "Invalid email address. Please enter a proper email ID.")
    @NotEmpty(message = "Please enter your email ID.")
	@Column(name = "email")
	private String email;
	
	@Size(max = 10, min = 10, message = "Invalid Phone Number. Phone number should be 10 digits.")
	@Column(name = "phbone_number")
	private String phoneNumber;
	
	@Column(name = "date_of_joining")
	private Date dateOfJoining;
	
	@Digits(fraction = 0, integer = 20)
	@Column(name = "salary")
	private Double salary;
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
}

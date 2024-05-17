package com.emp.app.entity;

public class TaxDeductionDto {
	
	private Long empCode;
	private String firstName;
	private String lastName;
	private Double yearlySalary;
	private Double taxAmount;
	private Double cessAmounty;
	
	public Long getEmpCode() {
		return empCode;
	}
	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
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
	public Double getYearlySalary() {
		return yearlySalary;
	}
	public void setYearlySalary(Double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Double getCessAmounty() {
		return cessAmounty;
	}
	public void setCessAmounty(Double cessAmounty) {
		this.cessAmounty = cessAmounty;
	}
	
}

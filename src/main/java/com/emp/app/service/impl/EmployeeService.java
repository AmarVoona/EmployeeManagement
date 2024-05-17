package com.emp.app.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.app.Constants;
import com.emp.app.entity.Employee;
import com.emp.app.entity.TaxDeductionDto;
import com.emp.app.repo.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	// Persisting Employee Details
	public Employee saveEmployee(Employee emp) {

		log.info("Saving Employee Details of : " + emp.getFirstName());
		return empRepository.save(emp);
	}

	// Tax Deduction Caluclation
	public TaxDeductionDto calculateTaxDeduction(Long empId) throws ParseException {

		Optional<Employee> findById = empRepository.findById(empId);

		TaxDeductionDto taxDto = new TaxDeductionDto();
		if (findById.isEmpty()) {
			return taxDto;
		}
		Employee employee = findById.get();
		Double ySalry = yearlySalaryCalculation(employee.getDateOfJoining(), employee.getSalary());

		taxDto.setEmpCode(empId);
		taxDto.setFirstName(employee.getFirstName());
		taxDto.setLastName(employee.getLastName());
		taxDto.setYearlySalary(ySalry);

		if (ySalry <= Constants.NO_TAX) {
			taxDto.setTaxAmount(0.0);
			taxDto.setCessAmounty(0.0);

		} else if (ySalry <= Constants.SLAB_FIVE && ySalry > Constants.NO_TAX) {
			Double taxableSalary = ySalry - Constants.NO_TAX;
			taxDto.setTaxAmount(slabFive(taxableSalary));
			taxDto.setYearlySalary(ySalry - slabFive(taxableSalary));

		} else if (ySalry <= Constants.SLAB_TEN && ySalry > Constants.SLAB_FIVE) {
			Double taxableSalary = ySalry - Constants.SLAB_FIVE - Constants.NO_TAX;
			Double taxbleAmt = slabFive(Constants.SLAB_FIVE) + slabTen(taxableSalary);
			taxDto.setTaxAmount(taxbleAmt);
			taxDto.setYearlySalary(ySalry - taxbleAmt);

		} else if (ySalry > Constants.SLAB_TEN) {
			Double taxableSalary = ySalry - Constants.SLAB_TEN - Constants.SLAB_FIVE - Constants.NO_TAX;
			Double taxbleAmt = slabFive(Constants.SLAB_FIVE) + slabTen(Constants.SLAB_TEN) + slabTwenty(taxableSalary);
			taxDto.setTaxAmount(taxbleAmt);
			taxDto.setYearlySalary(ySalry - taxbleAmt);
			if(ySalry > Constants.SLAB_CESS) {
				
				Double  cessTaxAmt = ySalry - Constants.SLAB_CESS;
				taxDto.setCessAmounty(cessTaxAmt * 0.02);
				taxDto.setYearlySalary(taxDto.getYearlySalary() - (cessTaxAmt * 0.02));
			}

		}
		return taxDto;

	}

	private Double slabFive(Double taxableAmt) {

		return taxableAmt * 0.05;
	}

	private Double slabTen(Double taxableAmt) {

		return taxableAmt * 0.1;
	}

	private Double slabTwenty(Double taxableAmt) {

		return taxableAmt * 0.2;
	}

	private Double yearlySalaryCalculation(Date doj, Double salary) throws ParseException {

		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date joinDate = sdformat.parse("2024-05-16");
		Date dbDate = sdformat.parse(doj.toString());
		Double ySalary = 0.0;
		if (joinDate.compareTo(dbDate) == 0) {
			ySalary = salary * 10 + salary / 2;
		} else {
			ySalary = salary * 12;
		}
		return ySalary;
	}
}

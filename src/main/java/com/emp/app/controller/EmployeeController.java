package com.emp.app.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.app.entity.Employee;
import com.emp.app.entity.TaxDeductionDto;
import com.emp.app.exception.CustomException;
import com.emp.app.service.impl.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/saveEmp")
	private ResponseEntity<?> saveEmployee(@RequestBody Employee employee) throws CustomException {	
		log.info("Persisting Employee");
		try {
			
			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();
			Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
			
			if (violations.isEmpty()) {
				log.error("Unable Save Employee Details");
				return new ResponseEntity<>(empService.saveEmployee(employee),HttpStatus.OK);
			} else {
				ArrayList<String> arrayList = new ArrayList<>();
				for (ConstraintViolation<Employee> violation : violations) {
					System.out.println(violation.getMessage());
					arrayList.add(violation.getMessage());
				}
				return new ResponseEntity<>(arrayList, HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new CustomException("Unable Persist Employee Data");
		}
			
	}

	// Get Individual Employee Tax Deduction for the Current Financial Year
	@GetMapping("/emp/{empId}")
	private TaxDeductionDto getTaxDeduction(@PathVariable("empId") Long empId) throws ParseException {
		log.info("Return Tax Deduction for the Current Financial Year...");
		
		return empService.calculateTaxDeduction(empId);
	}
}

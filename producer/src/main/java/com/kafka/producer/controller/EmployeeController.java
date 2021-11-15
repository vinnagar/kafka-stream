package com.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.model.EmployeeModel;
import com.kafka.producer.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<?> createEmp(@RequestBody EmployeeModel employeeModel){
		employeeService.producerService(employeeModel);
		return ResponseEntity.ok("Message Sent");
	}
}

package com.kafka.consumer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "permanentEmployees", schema = "kafka")
@Entity
public class EmployeeEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long EmployeeId;
	
	private String name;
	private String emailId;
	private boolean is_temp;
	public Long getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(Long employeeId) {
		EmployeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public boolean isIs_temp() {
		return is_temp;
	}
	public void setIs_temp(boolean is_temp) {
		this.is_temp = is_temp;
	}

}

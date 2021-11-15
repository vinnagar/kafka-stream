package com.kafka.consumer.model;

public class EmployeeModel {
	
	private String name;
	private String emailId;
	private boolean is_temp;
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

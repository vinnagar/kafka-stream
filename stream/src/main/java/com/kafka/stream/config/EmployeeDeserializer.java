package com.kafka.stream.config;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.stream.model.EmployeeModel;

public class EmployeeDeserializer implements Deserializer<EmployeeModel>{

	@Override
	public EmployeeModel deserialize(String arg0, byte[] arg1) {
		ObjectMapper mapper = new ObjectMapper();
		   EmployeeModel employeeModel = null;
		   try {
			   employeeModel = mapper.readValue(arg1, EmployeeModel.class);
		   } catch (Exception e) {
		     e.printStackTrace();
		   }
		   return employeeModel;
	}

}

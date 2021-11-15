package com.kafka.stream.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.stream.model.EmployeeModel;

public class EmployeeSerializer implements Serializer<EmployeeModel>{
	
	@Override
	public byte[] serialize(String arg0, EmployeeModel arg1) {
		byte[] retVal = null;
		   ObjectMapper objectMapper = new ObjectMapper();
		   try {
		     retVal = objectMapper.writeValueAsString(arg1).getBytes();
		   } catch (Exception e) {
		     e.printStackTrace();
		   }
		   return retVal;
	}

	
	
}

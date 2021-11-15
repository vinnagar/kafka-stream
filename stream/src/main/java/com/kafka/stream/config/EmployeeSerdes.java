package com.kafka.stream.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.kafka.stream.model.EmployeeModel;

public class EmployeeSerdes implements Serde<EmployeeModel> {
	
	private EmployeeSerializer serializer = new EmployeeSerializer();
	private EmployeeDeserializer deserializer = new EmployeeDeserializer();
	
	@Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        serializer.configure(configs, isKey);
        deserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        serializer.close();
        deserializer.close();
    }

	@Override
	public Serializer<EmployeeModel> serializer() {
		// TODO Auto-generated method stub
		return serializer;
	}

	@Override
	public Deserializer<EmployeeModel> deserializer() {
		// TODO Auto-generated method stub
		return deserializer;
	}

}

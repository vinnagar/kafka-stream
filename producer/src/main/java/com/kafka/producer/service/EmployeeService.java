package com.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kafka.producer.model.EmployeeModel;

@Service
public class EmployeeService {
	
	@Value(value = "${spring.kafka.template.producer-topic}")
    private String producerTopicName;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void producerService(EmployeeModel employeeModel) {
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(producerTopicName, employeeModel.getName(), employeeModel);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				System.out.println("OFFSET : " + result.getRecordMetadata().offset());
				System.out.println(employeeModel.getEmailId());
				System.out.println(employeeModel.getName());
				System.out.println(employeeModel.isIs_temp());
				System.out.println(result.getProducerRecord().key());
				
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("FAILED!! : " + ex.getMessage());
				
			}
			
		});
	}
}

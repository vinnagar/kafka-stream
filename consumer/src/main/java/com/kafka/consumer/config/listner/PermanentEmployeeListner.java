package com.kafka.consumer.config.listner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

import com.kafka.consumer.entity.EmployeeEntity;
import com.kafka.consumer.model.EmployeeModel;
import com.kafka.consumer.repository.EmployeeRepository;

@Service
public class PermanentEmployeeListner {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@KafkaListener(topics = "${spring.kafka.template.permanent-employee-topic}", containerFactory = "permanentEmployeeListenerContainerFactory")
    public void listenFirstTopicWithDetails(ConsumerRecord<String, EmployeeModel> consumerRecord,
                                            @Payload EmployeeModel messageEntity,
                                            @Header(KafkaHeaders.GROUP_ID) String groupId,
                                            @Header(KafkaHeaders.OFFSET) int offset,
                                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition)
    {
        System.out.println("Received message with below details:");
        System.out.println(consumerRecord); //We can also reach to all details from consumerRecord.
        System.out.println(messageEntity.getEmailId());
        System.out.println(messageEntity.getName());
        System.out.println(messageEntity.isIs_temp());//This is value.
        System.out.println(groupId); //This will be groupId1.
        System.out.println(offset); //Current record offset.
        System.out.println(partition); //We used only one partition, so this will be 0 for each message.
        
        createEmployeeToDB(messageEntity);
    }

	private void createEmployeeToDB(EmployeeModel messageEntity) {
		// TODO Auto-generated method stub
		employeeRepository.save(toEntity(messageEntity));
	}

	private EmployeeEntity toEntity(EmployeeModel messageEntity) {
		// TODO Auto-generated method stub
		EmployeeEntity entity = new EmployeeEntity();
		entity.setEmailId(messageEntity.getEmailId());
		entity.setIs_temp(messageEntity.isIs_temp());
		entity.setName(messageEntity.getName());
		return entity;
	}
	
	

}

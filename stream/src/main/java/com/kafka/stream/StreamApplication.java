package com.kafka.stream;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kafka.stream.config.EmployeeSerdes;
import com.kafka.stream.model.EmployeeModel;

@SpringBootApplication
public class StreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamApplication.class, args);
		
		Properties config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "empolyee-stream-application");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");		
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, EmployeeSerdes.class);
		
		StreamsBuilder builder = new StreamsBuilder();
		
		KStream<String, EmployeeModel> employeeStreamIn = builder.stream("employee-producer");
		
		KStream<String, EmployeeModel>[] employeeStreamOut = employeeStreamIn.selectKey((key,value) -> value.getName())
																				.branch(
																						(key,value) -> {
																							if(value.isIs_temp() == true) {
																								return true;
																							}
																							else {
																								return false;
																							}
																						},
																						(key,value) -> {
																							if(value.isIs_temp() == false) {
																								return true;
																							}
																							else {
																								return false;
																							}
																						}
																						);
		
		employeeStreamOut[1].to("employee-consumer-permanent");
		employeeStreamOut[0].to("employee-consumer-temporary");
		
		KafkaStreams streams = new KafkaStreams(builder.build(), config);
		streams.start();
		System.out.println(streams.toString());
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

	}
}

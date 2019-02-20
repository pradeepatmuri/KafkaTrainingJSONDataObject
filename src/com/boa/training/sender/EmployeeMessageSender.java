package com.boa.training.sender;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.boa.training.domain.Address;
import com.boa.training.domain.Employee;



public class EmployeeMessageSender {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty("value.serializer","com.boa.training.serializer.EmployeeSerializer");
		//props.setProperty("partitioner.class", "com.boa.training.custompartitioner.MessagePartitioner");
		KafkaProducer<String,Employee> producer = new KafkaProducer<>(props);
		
		String topicName="emp-topicwith4";
		List<Employee> list =  Arrays.asList(new Employee(1,"Venu","Architect",Arrays.asList(new Address("Panjagutta","Hyd"),new Address("madhapur","Hyd"))),new Employee(2,"Venu","Architect",Arrays.asList(new Address("Panjagutta","Hyd"),new Address("madhapur","Hyd"))),new Employee(3,"Ram","UI Developer",Arrays.asList(new Address("Panjagutta","Hyd"),new Address("madhapur","Hyd"))),new Employee(4,"Prem","Accountent",Arrays.asList(new Address("Panjagutta","Hyd"),new Address("madhapur","Hyd"))),new Employee(5,"Prem","Developer",Arrays.asList(new Address("Panjagutta","Hyd"),new Address("madhapur","Hyd"))));
		for(Employee emp :list) {
		ProducerRecord<String,Employee> record = new ProducerRecord<>(topicName,"employee-1",emp);
		
		Future<RecordMetadata> future = producer.send(record);
		try {
			RecordMetadata rmd=future.get();
			System.out.println("message delivered to parition: "+rmd.partition()+" at offset: "+rmd.offset());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
		producer.close();
		System.out.println("Employee object sent");
	}

}

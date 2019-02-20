package com.boa.training.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.boa.training.domain.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeSerializer  implements Serializer<Employee>{

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String topic, Employee obj) {
		// TODO Auto-generated method stub
		byte[] array = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonContent = mapper.writeValueAsString(obj);
			System.out.println("Converted to JSON "+ jsonContent);
			array =jsonContent.getBytes();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}

}

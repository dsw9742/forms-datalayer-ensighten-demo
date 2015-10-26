package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DigitalDataAdapterImpl implements DigitalDataAdapter {
	
	private ObjectMapper mapper;
	
	public DigitalDataAdapterImpl() {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	@Override
	public String adapt(DigitalData digitalData) {
		String output = null;
		try {
			output = mapper.writeValueAsString(digitalData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return output;
	}

}

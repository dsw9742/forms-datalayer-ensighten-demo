package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DigitalDataToStringAdapterImpl implements DigitalDataToStringAdapter {
	
	private ObjectMapper mapper;
	
	public DigitalDataToStringAdapterImpl() {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	@Override
	public String adapt(final DigitalData digitalData) {
		String output = null;
		try {
			output = mapper.writeValueAsString(digitalData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return output;
	}

}

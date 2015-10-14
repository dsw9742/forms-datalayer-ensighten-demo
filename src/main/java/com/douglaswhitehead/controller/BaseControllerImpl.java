package com.douglaswhitehead.controller;

import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseControllerImpl implements BaseController {
	
	protected String toString(final DigitalData data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String output = null;
		try {
			output = mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return output;
	}

}

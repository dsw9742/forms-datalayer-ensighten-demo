package com.douglaswhitehead.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.douglaswhitehead.service.DataLayerServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseControllerImpl implements BaseController {
	
	@Autowired
	protected DataLayerServiceImpl dataLayerService;
	
	protected String jackson(final DigitalData data) {
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

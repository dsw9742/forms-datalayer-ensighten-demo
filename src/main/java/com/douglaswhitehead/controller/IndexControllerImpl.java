package com.douglaswhitehead.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.douglaswhitehead.service.DataLayerServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class IndexControllerImpl {
	
	@Autowired
	private DataLayerServiceImpl datalayerservice;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(final HttpServletRequest request, final Device device, Model model) {
		model.addAttribute("key", "value");
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("mapkey1", "mapvalue1");
		map.put("mapkey2", "mapvalue2");
		model.addAttribute("map", map);
		
		model.addAttribute("digitalData", jackson(datalayerservice.get("index", request, device)));
		
		return "index";
	}
	
	private String jackson(DigitalData data) {
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

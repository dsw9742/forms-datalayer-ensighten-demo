package com.douglaswhitehead.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import com.douglaswhitehead.model.digitaldata.DigitalData;

public interface DataLayerService {
	
	public DigitalData get(String pageName, HttpServletRequest request, Device device);

}

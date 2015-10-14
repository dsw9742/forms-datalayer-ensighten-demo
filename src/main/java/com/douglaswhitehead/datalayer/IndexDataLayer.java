package com.douglaswhitehead.datalayer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import com.douglaswhitehead.model.digitaldata.DigitalData;

public interface IndexDataLayer {

	public DigitalData index(HttpServletRequest request, Device device);
	
}
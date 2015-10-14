package com.douglaswhitehead.datalayer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import com.douglaswhitehead.model.digitaldata.DigitalData;

public interface ProductsDataLayer {

	public DigitalData list(HttpServletRequest request, Device device);
		
}

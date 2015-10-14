package com.douglaswhitehead.datalayer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.digitaldata.DigitalData;

@Component
public class ProductsDataLayerImpl implements ProductsDataLayer {

	@Override
	public DigitalData list(HttpServletRequest request, Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DigitalData listByCategory(String category, HttpServletRequest request, Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DigitalData get(long id, HttpServletRequest request, Device device) {
		// TODO Auto-generated method stub
		return null;
	}

}
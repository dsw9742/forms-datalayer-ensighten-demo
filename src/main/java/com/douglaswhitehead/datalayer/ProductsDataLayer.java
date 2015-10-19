package com.douglaswhitehead.datalayer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import com.douglaswhitehead.model.Product;
import com.douglaswhitehead.model.digitaldata.DigitalData;

public interface ProductsDataLayer {

	public DigitalData list(List<Product> products, HttpServletRequest request, Device device);
	
	public DigitalData listByCategory(String category, List<Product> products, HttpServletRequest request, Device device);
	
	public DigitalData get(Product product, HttpServletRequest request, Device device);
		
}
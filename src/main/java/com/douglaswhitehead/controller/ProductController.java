package com.douglaswhitehead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;

import com.douglaswhitehead.model.Product;

public interface ProductController {
	
	public String list(HttpServletRequest request, Device device, Model model);

	public List<Product> listByCategory(String category, HttpServletRequest request, Device device, Model model);
	
	public Product get(long id, HttpServletRequest request, Device device, Model model);

}

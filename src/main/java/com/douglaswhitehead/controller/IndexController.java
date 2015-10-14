package com.douglaswhitehead.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;

public interface IndexController {
	
	public String index(HttpServletRequest request, Device device, Model model);

}
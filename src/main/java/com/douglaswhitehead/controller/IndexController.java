package com.douglaswhitehead.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;

public interface IndexController {
	
	public String index(HttpServletRequest request, Principal principal, Device device, HttpServletResponse response, Model model);

}
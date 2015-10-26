package com.douglaswhitehead.datalayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.User;
import com.douglaswhitehead.model.digitaldata.DigitalData;

public interface ShoppingCartDataLayer {

	public DigitalData get(HttpServletRequest request, HttpServletResponse response, Device device, Model model, ShoppingCart cart, User user);
	
}

package com.douglaswhitehead.datalayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.User;
import com.douglaswhitehead.model.digitaldata.DigitalData;

@Component
public class ErrorDataLayerImpl implements ErrorDataLayer {

	@Override
	public DigitalData error(String error, String status, HttpServletRequest request, HttpServletResponse response,
			Device device, Model model, ShoppingCart cart, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}

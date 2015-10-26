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
public class LoginDataLayerImpl extends AbstractDataLayer implements LoginDataLayer {

	@Override
	public DigitalData login(final String error, final HttpServletRequest request, final HttpServletResponse response, 
			final Device device, final Model model, final ShoppingCart cart, final User user) {
		// TODO Auto-generated method stub
		return null;
	}

}

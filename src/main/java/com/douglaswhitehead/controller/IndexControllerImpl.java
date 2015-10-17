package com.douglaswhitehead.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglaswhitehead.datalayer.IndexDataLayer;

@Controller
@RequestMapping("/")
public class IndexControllerImpl extends BaseControllerImpl implements IndexController {
	
	@Autowired
	private IndexDataLayer dataLayer;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(final HttpServletRequest request, final Principal principal, final Device device, final HttpServletResponse response, final Model model) {
		boolean auth = isAuthenticated(principal);

		doCookies(request, response);
		
		String digitalData = toString(dataLayer.index(request, device));
		
		model.addAttribute("isAuthenticated", auth);
		model.addAttribute("cartId", this.cartId);
		model.addAttribute("cartSize", this.cartSize);
		model.addAttribute("digitalData", digitalData);
		
		return "index";
	}

}
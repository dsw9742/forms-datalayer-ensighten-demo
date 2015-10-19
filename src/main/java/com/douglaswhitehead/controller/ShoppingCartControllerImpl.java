package com.douglaswhitehead.controller;

import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.douglaswhitehead.service.ShoppingCartService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/cart")
public class ShoppingCartControllerImpl extends BaseControllerImpl implements ShoppingCartController {
	
	@Autowired
	private ShoppingCartService service;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public String get(@PathVariable("id") final UUID id, final HttpServletRequest request, final Principal principal, final Device device, final HttpServletResponse response, final Model model) {
		boolean auth = isAuthenticated(principal);

		doCookies(request, response);
		
		ShoppingCart scart = service.get(id);
		String cart = cartToString(scart);
		
		model.addAttribute("cart", cart);
		model.addAttribute("digitalData", "");
		
		return "cart/view";
	}

	public String addToCart(final long productId, final HttpServletRequest request, final Principal principal, final Device device, final HttpServletResponse response, final Model model) {
		return null; // TODO:
	}
	
	public String removeFromCart(final long productId, final HttpServletRequest request, final Principal principal, final Device device, final HttpServletResponse response, final Model model) {
		return null; // TODO:
	}
	
	public String checkout(final UUID cartId, final HttpServletRequest request, final Principal principal, final Device device, final HttpServletResponse response, final Model model) {
		return null; // TODO:
	}
	
	// TEMP
	protected String cartToString(final ShoppingCart cart) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String output = null;
		try {
			output = mapper.writeValueAsString(cart);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return output;
	}
	
}

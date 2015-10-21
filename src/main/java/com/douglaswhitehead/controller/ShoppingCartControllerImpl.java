package com.douglaswhitehead.controller;

import java.security.Principal;
import java.util.List;
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
import com.douglaswhitehead.model.ShoppingCartItem;
import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.douglaswhitehead.service.ShoppingCartService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/carts")
public class ShoppingCartControllerImpl extends BaseControllerImpl implements ShoppingCartController {
	
	@Autowired
	private ShoppingCartService service;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public String get(@PathVariable("id") final UUID id, final HttpServletRequest request, final Device device, final HttpServletResponse response, final Model model) {
		boolean auth = isAuthenticated(request.getUserPrincipal());

		doCookies(request, response);
		
		ShoppingCart cart = service.get(id);
		
		model.addAttribute("cart", cart);
		model.addAttribute("digitalData", ""); // TODO:
	
		return "cart/view";
	}

	@RequestMapping(value = "/{id}/addToCart/{productId}", method=RequestMethod.GET)
	public String addToCart(@PathVariable("id") final UUID id, @PathVariable("productId") final long productId, final HttpServletRequest request, final Device device, final HttpServletResponse response, final Model model) {
		boolean auth = isAuthenticated(request.getUserPrincipal());

		doCookies(request, response);
		
		ShoppingCart cart = service.addToCart(id, productId);
		
		model.addAttribute("cart", cart);
		model.addAttribute("digitalData", ""); // TODO:
	
		return "redirect:/carts/"+id.toString();
	}
	
	@RequestMapping(value = "/{id}/removeFromCart/{productId}", method=RequestMethod.GET)
	public String removeFromCart(@PathVariable("id") final UUID id, @PathVariable("productId") final long productId, final HttpServletRequest request, final Device device, final HttpServletResponse response, final Model model) {
		return null; // TODO:
	}
	
	public String checkout(final UUID cartId, final HttpServletRequest request, final Device device, final HttpServletResponse response, final Model model) {
		return null; // TODO:
	}
	
}

package com.douglaswhitehead.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglaswhitehead.model.Order;
import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.ShoppingCartItem;
import com.douglaswhitehead.model.User;
import com.douglaswhitehead.service.ShoppingCartService;

@Controller
@RequestMapping("/orders")
public class OrderControllerImpl extends BaseControllerImpl implements OrderController {
	
	@Autowired
	private ShoppingCartService cartService;
	
	// TODO: add component/datalayer, model/digitalData

	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	@Override
	public String payment(final HttpServletRequest request, final Device device, final HttpServletResponse response, final Model model) {
		boolean auth = isAuthenticated();
		String cartId;

		if (!checkCartIdCookie(request)) {
			return "redirect:/orders/error";
		} else {
			Cookie cookie = getCartIdCookie(request);
			cartId = cookie.getValue();
		}
		
		ShoppingCart cart = cartService.get(UUID.fromString(cartId));
		User user = null;
		if (auth) {
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		model.addAttribute("isAuthenticated", auth);
		model.addAttribute("cartId", cart.getId().toString());
		model.addAttribute("cartSize", calculateCartSize(cart));
		model.addAttribute("digitalData", ""); // TODO:
		
		return "orders/pay";
	}
	
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	@Override
	public String complete(final HttpServletRequest request, final Device device, final HttpServletResponse response, final Model model) {
		boolean auth = isAuthenticated();
		String cartId;

		if (!checkCartIdCookie(request)) {
			String error = "Order error.";
			return "redirect:/error?error="+error;
		} else {
			Cookie cookie = getCartIdCookie(request);
			cartId = cookie.getValue();
		}
		
		ShoppingCart cart = cartService.get(UUID.fromString(cartId));
		User user = null;
		if (auth) {
			user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		// simulates placing an order
		// in a prod env, this would be replaced with a real order service and back end
		try {
			// remove items from shopping cart, since they are being purchased
			List<ShoppingCartItem> itemsToRemove = new ArrayList<ShoppingCartItem>();
			if (cart != null) {
				for (ShoppingCartItem item: cart.getCartItems()) {
					for (int i=1;i<=item.getQuantity();i++) {
						itemsToRemove.add(item);	
					}
				}
			}
			for (ShoppingCartItem item: itemsToRemove) {
				cartService.removeFromCart(cart.getId(), item.getId());
			}
			
		    Thread.sleep(5000); // simulate long-running purchase transaction
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		// simulate order service response, which would return an order 
		Order order = new Order(UUID.randomUUID(), cart.getBasePrice(), cart.getVoucherCode(), cart.getVoucherDiscount(), 
					  cart.getCurrency(), cart.getTaxRate(), cart.getShipping(), cart.getShippingMethod(), 
					  cart.getPriceWithTax(), cart.getCartTotal(), cart.getCartItems());
		
		model.addAttribute("isAuthenticated", auth);
		model.addAttribute("cartId", cart.getId().toString());
		model.addAttribute("cartSize", 0); // cart items is now 0
		model.addAttribute("order", order);
		model.addAttribute("digitalData", ""); // TODO:
		
		return "orders/complete";
	}
	
}
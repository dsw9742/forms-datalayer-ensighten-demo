package com.douglaswhitehead.controller;

import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseControllerImpl implements BaseController {
	
	/**
	 * id of cart
	 */
	protected UUID cartId;
	
	/**
	 * # of items in the cart
	 */
	protected int cartSize;
	
	/**
	 * Converts DigitalData data to a JavaScript string.
	 * Uses Jackson library. 
	 * 
	 * @param DigitalData data
	 * @return String
	 */
	protected String toString(final DigitalData data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String output = null;
		try {
			output = mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * Returns true if a principal ("user") is authenticated, false otherwise.
	 * 
	 * @param Principal principal
	 * @return boolean
	 */
	protected boolean isAuthenticated(final Principal principal) {
		if ((principal != null) && (principal instanceof Authentication)) {
			return ((Authentication)principal).isAuthenticated();
		}
		return false;
	}
	
	/**
	 * Wrapper method for cookie-related methods.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	protected void doCookies(final HttpServletRequest request, final HttpServletResponse response) {
		cartIdCookie(request, response);
		cartSizeCookie(request, response);
	}

	/**
	 * Creates shopping cart cookie, persists the id of the cart, and ensures it exists for
	 * another 60 days.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	private void cartIdCookie(final HttpServletRequest request, final HttpServletResponse response) {
		Cookie cartIdCookie;
		String cookieName = "shoppingCartId";
		int expiry = 60 * 60 * 24 * 60; // 60 days
		
		// check for cookie
		boolean check = false;
		for (Cookie cookie : request.getCookies()) {
			// if cartCookie is found, extend the expiration date another 60 days
			if (cookie.getName().equals(cookieName)) {
				cartIdCookie = cookie;
				cartIdCookie.setMaxAge(expiry);
				cartIdCookie.setPath("/");
				response.addCookie(cartIdCookie);
				check = true;
				this.cartId =  UUID.fromString(cartIdCookie.getValue()); // set id of cart
				break;
			}
		}
		
		// if cartCookie is not found, create it
		if (check == false) {
			UUID cartId = UUID.randomUUID();
			cartIdCookie = new Cookie(cookieName, cartId.toString());
			cartIdCookie.setMaxAge(expiry);
			cartIdCookie.setPath("/");
			response.addCookie(cartIdCookie);
			this.cartId = cartId;
		}
		
	}
	
	/**
	 * Creates shopping cart size cookie, persists the value of the cart size, and ensures it exists for
	 * another 60 days.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	private void cartSizeCookie(final HttpServletRequest request, final HttpServletResponse response) {
		Cookie cartSizeCookie;
		String cookieName = "shoppingCartSize";
		int expiry = 60 * 60 * 24 * 60; // 60 days
		
		// check for cookie
		boolean check = false;
		for (Cookie cookie : request.getCookies()) {
			// if cartSizeCookie is found, extend the expiration date another 60 days
			if (cookie.getName().equals(cookieName)) {
				cartSizeCookie = cookie;
				cartSizeCookie.setMaxAge(expiry);
				cartSizeCookie.setPath("/");
				check = true;
				this.cartSize = Integer.parseInt(cartSizeCookie.getValue()); // set size of cart
				break;
			}
		}
		
		// if cartCookie is not found, create it
		if (check == false) {
			int size = 0;
			cartSizeCookie = new Cookie(cookieName,Integer.toString(size));
			cartSizeCookie.setMaxAge(expiry);
			response.addCookie(cartSizeCookie);
			this.cartSize = 0; // set size of cart
		}
		
	}
	
}

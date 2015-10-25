package com.douglaswhitehead.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.ShoppingCartItem;
import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseControllerImpl implements BaseController {
	
	private static final String CART_ID_COOKIE_NAME = "shoppingCartId";
	
	private static final int CART_ID_COOKIE_EXPIRY = 60 * 60 * 24 * 60; // 60 days
	
	private static final String CART_ID_COOKIE_PATH = "/";
	
	
	/**
	 * Converts DigitalData data to a JavaScript string.
	 * Uses Jackson library. 
	 * 
	 * @param DigitalData data
	 * @return String
	 */
	protected String digitalDataToString(final DigitalData data) {
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
	 * Returns true if a principal is not anonymous, false otherwise.
	 * 
	 * @return boolean
	 */
	protected boolean isAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			if (auth instanceof AnonymousAuthenticationToken) {
				return false;
			}	
			if (auth instanceof UsernamePasswordAuthenticationToken) {
				return true;
			}
		}		
		return false;
	}
	
	protected boolean checkCartIdCookie(final HttpServletRequest request) {
		boolean flag = false;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(CART_ID_COOKIE_NAME)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	protected String setNewCartIdCookie(final HttpServletRequest request, final HttpServletResponse response) {
		Cookie cartIdCookie = new Cookie(CART_ID_COOKIE_NAME, UUID.randomUUID().toString());
		cartIdCookie.setMaxAge(CART_ID_COOKIE_EXPIRY);
		cartIdCookie.setPath(CART_ID_COOKIE_PATH);
		response.addCookie(cartIdCookie);
		
		return cartIdCookie.getValue();
	}
	
	protected Cookie getCartIdCookie(final HttpServletRequest request) {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(CART_ID_COOKIE_NAME)) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	protected int setCartIdCookieExpiry(final Cookie cookie) {
		cookie.setMaxAge(CART_ID_COOKIE_EXPIRY);
		return CART_ID_COOKIE_EXPIRY;
	}
	
	protected int calculateCartSize(final ShoppingCart cart) {
		int cartSize = 0;
		if (cart != null) {
			for (ShoppingCartItem item : cart.getCartItems()) {
				cartSize += item.getQuantity();
			}
		}
		return cartSize;
	}
	
}

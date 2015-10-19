package com.douglaswhitehead.service;

import java.util.UUID;

import com.douglaswhitehead.model.ShoppingCart;

public interface ShoppingCartService {

	public ShoppingCart get(UUID id);
	
	public ShoppingCart addToCart(UUID id, long productId);
	
	public ShoppingCart removeFromCart(UUID id, long productId);
	
	public UUID delete(UUID id);
	
}

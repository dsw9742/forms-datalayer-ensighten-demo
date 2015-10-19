package com.douglaswhitehead.repository;

import java.util.UUID;

import com.douglaswhitehead.model.ShoppingCart;

public interface ShoppingCartRepository {
	
	public ShoppingCart get(UUID id);
	
	public ShoppingCart create(ShoppingCart cart);
	
	public ShoppingCart update(ShoppingCart cart);
	
	public UUID delete(UUID id);
	
}
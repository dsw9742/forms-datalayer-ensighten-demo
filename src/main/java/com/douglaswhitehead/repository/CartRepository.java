package com.douglaswhitehead.repository;

import com.douglaswhitehead.model.ShoppingCart;

public interface CartRepository {
	
	public ShoppingCart get(long id);
	
	public ShoppingCart create(ShoppingCart cart);
	
	public ShoppingCart update(ShoppingCart cart);
	
	public ShoppingCart delete(long id);
	
}
package com.douglaswhitehead.repository;

import com.douglaswhitehead.model.Cart;

public interface CartRepository {
	
	public Cart get(long id);
	
	public Cart create(Cart cart);
	
	public Cart update(Cart cart);
	
	public Cart delete(long id);
	
}
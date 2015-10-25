package com.douglaswhitehead.adapter;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.digitaldata.cart.Cart;

public interface ShoppingCartAdapter {

	public Cart adapt(ShoppingCart cart);
	
}

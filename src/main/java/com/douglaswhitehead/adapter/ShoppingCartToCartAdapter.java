package com.douglaswhitehead.adapter;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.digitaldata.cart.Cart;

public interface ShoppingCartToCartAdapter {

	public Cart adapt(ShoppingCart cart);
	
}

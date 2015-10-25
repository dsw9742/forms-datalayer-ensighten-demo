package com.douglaswhitehead.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.douglaswhitehead.model.Product;
import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.ShoppingCartItem;
import com.douglaswhitehead.repository.ProductRepository;
import com.douglaswhitehead.repository.ShoppingCartRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private ShoppingCartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Cacheable(value="cart")
	public ShoppingCart get(final UUID id) {
		return getOrCreateCart(id);
	}
	
	@Override
	@CacheEvict(value="cart", allEntries=false, key="#id")
	public ShoppingCart addToCart(final UUID id, final long productId) {
		ShoppingCart cart = getOrCreateCart(id);
		Product product = productRepository.get(productId);
		
		List<ShoppingCartItem> items = cart.getCartItems();
		boolean match = false;
		for(ShoppingCartItem item : items) {
			if (item.getId() == productId) {
				item.setQuantity(item.getQuantity()+1);
				match = true;
				break;
			} 
			
		}
		if (!match) {
			items.add(createItem(id, product));
		}
		cart.setCartItems(items);
		
		cart = cartRepository.update(cart);
		
		return cart;
	}
	
	@Override
	@CacheEvict(value="cart", allEntries=false, key="#id")
	public ShoppingCart removeFromCart(final UUID id, final long productId) {
		ShoppingCart cart = getOrCreateCart(id);
		
		List<ShoppingCartItem> items = cart.getCartItems();
		List<ShoppingCartItem> itemsToRemove = new ArrayList<ShoppingCartItem>();
		for(ShoppingCartItem item : items) {
			if (item.getId() == productId) {
				item.setQuantity(item.getQuantity()-1);
				if (item.getQuantity() == 0) {
					itemsToRemove.add(item);
				}
			}
		}
		if (itemsToRemove.size() > 0) {
			items.removeAll(itemsToRemove);
		}
		cart.setCartItems(items);
		
		cart = cartRepository.update(cart);
		
		return cart;
	}

	@Override
	public UUID delete(final UUID id) {
		return cartRepository.delete(id);
	}
	
	private ShoppingCart getOrCreateCart(final UUID id) {  
		ShoppingCart cart = cartRepository.get(id);  
        if (cart == null) {
        	cart = new ShoppingCart();
        	cart.setId(id);
            cart = cartRepository.create(cart);  
        }
        return cart;  
    }
	
	private ShoppingCartItem createItem(final UUID cartId, final Product product) {
		return new ShoppingCartItem(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getProductUrl(),
				product.getImageUrl(),
				product.getThumbnailUrl(),
				product.getManufacturer(),
				product.getSku(),
				product.getColor(),
				product.getPrice(),
				product.getSize(),
				product.getCategory(),
				1,
				cartId
		);
	}

}

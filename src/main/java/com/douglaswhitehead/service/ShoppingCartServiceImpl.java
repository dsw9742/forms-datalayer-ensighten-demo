package com.douglaswhitehead.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ShoppingCart get(final UUID id) {
		return cartRepository.get(id);
	}

	//@Override
	public ShoppingCart addToCart2(final UUID id, final long productId) {
		
		boolean createFlag = true;
		
		Product product = productRepository.get(productId);
		ShoppingCart cart = cartRepository.get(id);
		
		if (cart == null) {
			cart = new ShoppingCart();
			cart.setId(UUID.randomUUID());	
		} else {
			createFlag = false;
		}
		
		List<ShoppingCartItem> items = cart.getCartItems();
		for (ShoppingCartItem item : items) {
			if (item.getId() == productId) {
				item.setQuantity(item.getQuantity()+1);
			} else {
				ShoppingCartItem newItem = new ShoppingCartItem(
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
					cart.getId()
				);
				items.add(newItem);
			}
		}
		cart.setCartItems(items);
		
		if (createFlag == true) {
			cart = cartRepository.create(cart);
		} else {
			cart = cartRepository.update(cart);
		}
		
		return cart;
		
	}
	
	@Override
	public ShoppingCart addToCart(final UUID id, final long productId) {
		ShoppingCart cart = getOrCreateCart(id);
		Product product = productRepository.get(productId);
		
		List<ShoppingCartItem> items = cart.getCartItems();
		for (ShoppingCartItem item : items) {
			if (item.getId() == productId) {
				item.setQuantity(item.getQuantity()+1);
			} else {
				ShoppingCartItem newItem = new ShoppingCartItem(
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
					cart.getId()
				);
				items.add(newItem);
			}
		}
		cart.setCartItems(items);
		
		cart = cartRepository.update(cart);
		
		return cart;
	}

	@Override
	public ShoppingCart removeFromCart(final UUID id, final long productId) {
		
		boolean createFlag = true;
		
		Product product = productRepository.get(productId);
		ShoppingCart cart = cartRepository.get(id);
		
		if (cart == null) {
			cart = new ShoppingCart();
			cart.setId(UUID.randomUUID());	
		} else {
			createFlag = false;
		}
		
		List<ShoppingCartItem> items = cart.getCartItems();
		for (ShoppingCartItem item : items) {
			if (item.getId() == productId) {
				item.setQuantity(item.getQuantity()-1);
				if (item.getQuantity() == 0) {
					items.remove(item);
				}
			} else {
				ShoppingCartItem newItem = new ShoppingCartItem(
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
					cart.getId()
				);
				items.add(newItem);
			}
		}
		cart.setCartItems(items);
		
		if (createFlag == true) {
			cart = cartRepository.create(cart);
		} else {
			cart = cartRepository.update(cart);
		}
		
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

}

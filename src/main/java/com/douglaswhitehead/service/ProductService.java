package com.douglaswhitehead.service;

import java.util.List;

import com.douglaswhitehead.model.Product;

public interface ProductService {
	
	public List<Product> list();

	public List<Product> listByCategory(String category);
	
	public Product get(long id);

}
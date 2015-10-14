package com.douglaswhitehead.repository;

import java.util.List;

import com.douglaswhitehead.model.Product;

public interface ProductRepository {
	
	public List<Product> list();

	public Product get(long id);
	
	public boolean create(Product product);
	
	public boolean update(Product product);
	
	public boolean delete(long id);
	
}
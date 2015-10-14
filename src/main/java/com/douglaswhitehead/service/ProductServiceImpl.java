package com.douglaswhitehead.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglaswhitehead.model.Product;
import com.douglaswhitehead.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> list() {
		return repository.list();
	}

	@Override
	public List<Product> listByCategory(final String category) {
		return repository.listByCategory(category);
	}

	@Override
	public Product get(long id) {
		return repository.get(id);
	}

}

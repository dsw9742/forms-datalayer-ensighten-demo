package com.douglaswhitehead.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglaswhitehead.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Product> listByCategory(final String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.douglaswhitehead.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.douglaswhitehead.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Product> list() {
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM PRODUCTS";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Product product = new Product(
				(Long)row.get("ID"),
				(String)row.get("NAME"),
				(String)row.get("DESCRIPTION"),
				(String)row.get("PRODUCT_URL"),
				(String)row.get("IMAGE_URL"),
				(String)row.get("THUMBNAIL_URL"),
				(String)row.get("MANUFACTURER"),
				(String)row.get("SKU"),
				(String)row.get("COLOR"),
				(BigDecimal)row.get("PRICE"),
				(String)row.get("SIZE"),
				(String)row.get("CATEGORY")
			);
			products.add(product);
		}
		return products;
	}
	
	@Override
	public List<Product> listByCategory(final String category) {
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM PRODUCTS WHERE CATEGORY = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{category});
		for (Map<String, Object> row : rows) {
			Product product = new Product(
				(Long)row.get("ID"),
				(String)row.get("NAME"),
				(String)row.get("DESCRIPTION"),
				(String)row.get("PRODUCT_URL"),
				(String)row.get("IMAGE_URL"),
				(String)row.get("THUMBNAIL_URL"),
				(String)row.get("MANUFACTURER"),
				(String)row.get("SKU"),
				(String)row.get("COLOR"),
				(BigDecimal)row.get("PRICE"),
				(String)row.get("SIZE"),
				(String)row.get("CATEGORY")
			);
			products.add(product);
		}
		return products;
	}

	@Override
	public Product get(final long id) {
		String sql = "SELECT * FROM PRODUCTS WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
	}
	
	public class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Product(
				rs.getLong("ID"),
				rs.getString("NAME"),
				rs.getString("DESCRIPTION"),
				rs.getString("PRODUCT_URL"),
				rs.getString("IMAGE_URL"),
				rs.getString("THUMBNAIL_URL"),
				rs.getString("MANUFACTURER"),
				rs.getString("SKU"),
				rs.getString("COLOR"),
				rs.getBigDecimal("PRICE"),
				rs.getString("SIZE"),
				rs.getString("CATEGORY")
			);
		}
	}

}

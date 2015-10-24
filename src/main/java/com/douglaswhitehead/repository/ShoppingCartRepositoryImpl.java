package com.douglaswhitehead.repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.ShoppingCartItem;


@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
	
	private static final String CURRENCY = "USD";
	private static final BigDecimal TAX_RATE = new BigDecimal("0.10");
	private static final BigDecimal SHIPPING = new BigDecimal("9.95");
	private static final String SHIPPING_METHOD = "UPS";
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public ShoppingCart get(final UUID id) {
		String sql1 = "SELECT * FROM SHOPPING_CARTS WHERE ID = ?";
		List<ShoppingCart> carts = jdbcTemplate.query(sql1, new Object[]{id.toString()}, new ShoppingCartRowMapper());

		if (carts.size() == 0) {
			return null;
		}
		
		ShoppingCart cart = carts.get(0);
		
		List<ShoppingCartItem> cartItems = new ArrayList<ShoppingCartItem>();
		String sql2 = "SELECT p.*, i.cart_id, i.quantity FROM PRODUCTS AS p "
				+ "JOIN SHOPPING_CART_ITEMS AS i ON p.id = i.product_id "
				+ "WHERE i.cart_id = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql2, new Object[]{id.toString()});
		for (Map<String, Object> row : rows) {
			ShoppingCartItem cartItem = new ShoppingCartItem(
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
				(String)row.get("CATEGORY"),
				(Integer)row.get("QUANTITY"),
				(UUID)UUID.fromString((String)row.get("CART_ID"))
			);
			cartItems.add(cartItem);
		}
		
		cart.setCartItems(cartItems);

		return cart;
	}

	@Override
	public ShoppingCart create(final ShoppingCart cart) {
		
		// perform calculations
		
		final ShoppingCart insertCart = calculateCartItems(cart, cart.getCartItems());
		insertCart.setId(cart.getId());
		
		// insert new cart
		
		String sql1 = "INSERT INTO SHOPPING_CARTS(ID, BASE_PRICE, VOUCHER_CODE, VOUCHER_DISCOUNT, CURRENCY, TAX_RATE, "
				+ "SHIPPING, SHIPPING_METHOD, PRICE_WITH_TAX, CART_TOTAL, UPDATED) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Object[] params1 = new Object[]{insertCart.getId().toString(), insertCart.getBasePrice(), insertCart.getVoucherCode(), insertCart.getVoucherDiscount(), insertCart.getCurrency(), insertCart.getTaxRate(),
											insertCart.getShipping(), insertCart.getShippingMethod(), insertCart.getPriceWithTax(), insertCart.getCartTotal(), new Timestamp(new Date().getTime())
										};
		
		int[] types1 = new int[]{Types.CHAR, Types.DECIMAL, Types.VARCHAR, Types.DECIMAL, Types.VARCHAR, Types.DECIMAL,
									Types.DECIMAL, Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.TIMESTAMP
								};
		
		jdbcTemplate.update(sql1, params1, types1);
		
		// insert new cart items
		
		final List<ShoppingCartItem> items = cart.getCartItems();

		String sql2 = "INSERT INTO SHOPPING_CART_ITEMS(CART_ID, PRODUCT_ID, QUANTITY) VALUES(?, ?, ?)";
		
		jdbcTemplate.batchUpdate(sql2, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ShoppingCartItem item = items.get(i);
				ps.setString(1, insertCart.getId().toString());
				ps.setLong(2, item.getId());
				ps.setInt(3, item.getQuantity());
			}
			
			@Override
			public int getBatchSize() {
				return items.size();
			}
			
		});
		
		return insertCart;
	}

	@Override
	public ShoppingCart update(final ShoppingCart cart) {
		
		// perform calculations
		
		final ShoppingCart updateCart = calculateCartItems(cart, cart.getCartItems());
		updateCart.setId(cart.getId());
				
		// update existing cart
		
		String sql1 = "UPDATE SHOPPING_CARTS SET BASE_PRICE = ?, VOUCHER_CODE = ?, VOUCHER_DISCOUNT = ?, CURRENCY = ?, TAX_RATE = ?, "
				+ "SHIPPING = ?, SHIPPING_METHOD = ?, PRICE_WITH_TAX = ?, CART_TOTAL = ?, UPDATED = ? "
				+ "WHERE ID = ?";
		
		Object[] params1 = new Object[]{updateCart.getBasePrice(), updateCart.getVoucherCode(), updateCart.getVoucherDiscount(), updateCart.getCurrency(), updateCart.getTaxRate(), updateCart.getShipping(), 
											updateCart.getShippingMethod(), updateCart.getPriceWithTax(), updateCart.getCartTotal(), new Timestamp(new Date().getTime()), updateCart.getId().toString()
										};
		
		int[] types1 = new int[]{Types.DECIMAL, Types.VARCHAR, Types.DECIMAL, Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, 
									Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.TIMESTAMP, Types.CHAR
								};
		
		jdbcTemplate.update(sql1, params1, types1);
		
		// delete old cart items
		
		String sql2 = "DELETE FROM SHOPPING_CART_ITEMS WHERE CART_ID = ?";
		
		jdbcTemplate.update(sql2, new Object[]{updateCart.getId().toString()});
		
		// insert new cart items
		
		final List<ShoppingCartItem> items = cart.getCartItems();

		String sql3 = "INSERT INTO SHOPPING_CART_ITEMS(CART_ID, PRODUCT_ID, QUANTITY) VALUES(?, ?, ?)";
		
		jdbcTemplate.batchUpdate(sql3, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ShoppingCartItem item = items.get(i);
				ps.setString(1, updateCart.getId().toString());
				ps.setLong(2, item.getId());
				ps.setInt(3, item.getQuantity());
			}
			
			@Override
			public int getBatchSize() {
				return items.size();
			}
			
		});
		
		return updateCart;
	}

	@Override
	public UUID delete(final UUID id) {
		
		// delete existing cart items
		
		String sql2 = "DELETE FROM SHOPPING_CART_ITEMS WHERE CART_ID = ?";
				
		jdbcTemplate.update(sql2, new Object[]{id.toString()});
		
		// delete existing cart
		
		String sql1 = "DELETE FROM SHOPPING_CARTS WHERE ID = ?";
		
		jdbcTemplate.update(sql1, new Object[]{id.toString()});
		
		return id;
	}
	
	public class ShoppingCartRowMapper implements RowMapper<ShoppingCart> {
		@Override
		public ShoppingCart mapRow(ResultSet rs, int rowNum) throws SQLException {
			ShoppingCart cart = new ShoppingCart();
			cart.setId((UUID)UUID.fromString((String)rs.getString("ID")));
			cart.setBasePrice((BigDecimal)rs.getBigDecimal("BASE_PRICE"));
			cart.setVoucherCode((String)rs.getString("VOUCHER_CODE"));
			cart.setVoucherDiscount((BigDecimal)rs.getBigDecimal("VOUCHER_DISCOUNT"));
			cart.setCurrency((String)rs.getString("CURRENCY"));
			cart.setTaxRate((BigDecimal)rs.getBigDecimal("TAX_RATE"));
			cart.setShipping((BigDecimal)rs.getBigDecimal("SHIPPING"));
			cart.setShippingMethod((String)rs.getString("SHIPPING_METHOD"));
			cart.setPriceWithTax((BigDecimal)rs.getBigDecimal("PRICE_WITH_TAX"));
			cart.setCartTotal((BigDecimal)rs.getBigDecimal("CART_TOTAL"));
			return cart;
		}
	}
	
	private ShoppingCart calculateCartItems(final ShoppingCart cart, final List<ShoppingCartItem> items) {
		ShoppingCart newCart = new ShoppingCart();
		
		BigDecimal basePrice = new BigDecimal("0");
		BigDecimal voucherDiscount = new BigDecimal("1"); // this is hard-coded at 1 , which means 100%, 
														  // which in effect means there is no discount, and it
														  // would likely be different in a real e-commerce system
		BigDecimal priceWithTax = new BigDecimal("0");
		BigDecimal cartTotal = new BigDecimal("0");
		
		for (ShoppingCartItem item : items) {
			BigDecimal price = item.getPrice();
			BigDecimal quantity = new BigDecimal(item.getQuantity());
			
			basePrice = basePrice.add(price.multiply(quantity));
		}
		newCart.setBasePrice(basePrice);
		
		newCart.setVoucherCode(cart.getVoucherCode());
		newCart.setVoucherDiscount(voucherDiscount);
		newCart.setCurrency(CURRENCY);
		newCart.setTaxRate(TAX_RATE);
		newCart.setShipping(SHIPPING);
		newCart.setShippingMethod(SHIPPING_METHOD);
		
		priceWithTax = basePrice.add(
							priceWithTax.add(basePrice
									.multiply(voucherDiscount)
										.multiply(TAX_RATE)));
		newCart.setPriceWithTax(priceWithTax);
		
		cartTotal = cartTotal.add(priceWithTax.add(SHIPPING));
		newCart.setCartTotal(cartTotal);
		
		newCart.setCartItems(items);
		
		return newCart;
	}

}
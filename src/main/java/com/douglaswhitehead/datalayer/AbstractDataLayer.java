package com.douglaswhitehead.datalayer;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglaswhitehead.adapter.OrderToTransactionAdapter;
import com.douglaswhitehead.adapter.PrivacyAdapter;
import com.douglaswhitehead.adapter.ProductsToProductsAdapter;
import com.douglaswhitehead.adapter.ShoppingCartToCartAdapter;
import com.douglaswhitehead.adapter.UsersToUsersAdapter;
import com.douglaswhitehead.utility.DeviceDetector;

public abstract class AbstractDataLayer {
	
	protected static final String VERSION = "1.0";
	
	@Autowired
	protected DeviceDetector detector;
	
	@Autowired
	protected ProductsToProductsAdapter productsAdapter;
	
	@Autowired
	protected ShoppingCartToCartAdapter cartAdapter;
	
	@Autowired
	protected OrderToTransactionAdapter orderAdapter;
	
	@Autowired
	protected UsersToUsersAdapter usersAdapter;
	
	@Autowired
	protected PrivacyAdapter privacyAdapter;

}
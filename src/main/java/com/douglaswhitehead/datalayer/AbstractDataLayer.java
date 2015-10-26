package com.douglaswhitehead.datalayer;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglaswhitehead.adapter.OrderAdapter;
import com.douglaswhitehead.adapter.PrivacyAdapter;
import com.douglaswhitehead.adapter.ProductsAdapter;
import com.douglaswhitehead.adapter.ShoppingCartAdapter;
import com.douglaswhitehead.adapter.UsersAdapter;
import com.douglaswhitehead.utility.DeviceDetector;

public abstract class AbstractDataLayer {
	
	protected static final String VERSION = "1.0";
	
	@Autowired
	protected DeviceDetector detector;
	
	@Autowired
	protected ProductsAdapter productsAdapter;
	
	@Autowired
	protected ShoppingCartAdapter cartAdapter;
	
	@Autowired
	protected OrderAdapter orderAdapter;
	
	@Autowired
	protected UsersAdapter usersAdapter;
	
	@Autowired
	protected PrivacyAdapter privacyAdapter;

}
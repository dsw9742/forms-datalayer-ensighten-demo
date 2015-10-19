package com.douglaswhitehead.datalayer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.Product;
import com.douglaswhitehead.model.digitaldata.DigitalData;

@Component
public class ProductsDataLayerImpl implements ProductsDataLayer {

	@Override
	public DigitalData list(final List<Product> products, final HttpServletRequest request, final Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DigitalData listByCategory(final String category, final List<Product> products, final HttpServletRequest request, final Device device) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DigitalData get(final Product product, final HttpServletRequest request, final Device device) {
		// TODO Auto-generated method stub
		return null;
	}

}
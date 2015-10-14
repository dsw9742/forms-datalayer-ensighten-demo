package com.douglaswhitehead.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import com.douglaswhitehead.components.datalayer.IndexDataLayerImpl;
import com.douglaswhitehead.model.digitaldata.DigitalData;

@Service
public class DataLayerServiceImpl implements DataLayerService {
	
	@Autowired
	private IndexDataLayerImpl indexDataLayer;
	
	@Autowired
	private ProductsDataLayerImpl productsDataLayer;
	
	public DigitalData get(final String pageName, final HttpServletRequest request, final Device device) {
		switch (pageName) {
			case "index":
				return indexDataLayer.data(request, device);
			case "products":
				return productsDataLayer.data(request, device);
			default:
				return null;
		}
	}

}

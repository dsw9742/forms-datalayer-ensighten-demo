package com.douglaswhitehead.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import com.douglaswhitehead.model.datalayer.IndexDataLayerImpl;
import com.douglaswhitehead.model.digitaldata.DigitalData;

@Service
public class DataLayerServiceImpl {
	
	@Autowired
	private IndexDataLayerImpl indexDataLayer;
	
	public DigitalData get(final String pageName, final HttpServletRequest request, final Device device) {
		switch (pageName) {
			case "index":
				return index(request, device);
			default:
				return null;
		}
	}
	
	private DigitalData index(final HttpServletRequest request, final Device device) {
		return indexDataLayer.data(request, device);
	}

}

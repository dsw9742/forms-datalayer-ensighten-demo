package com.douglaswhitehead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglaswhitehead.datalayer.ProductsDataLayer;
import com.douglaswhitehead.model.Product;
import com.douglaswhitehead.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductControllerImpl extends BaseControllerImpl implements ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductsDataLayer dataLayer;

	@Override
	@RequestMapping(method=RequestMethod.GET)
	public String list(final HttpServletRequest request, final Device device, final Model model) {
		List<Product> products = service.list();
		String digitalData = toString(dataLayer.list(request, device));
		
		model.addAttribute("products", products);
		model.addAttribute("digitalData", digitalData);
		
		return "products/list";
	}

	@Override
	@RequestMapping(value="/{category}", method=RequestMethod.GET)
	public String listByCategory(@PathVariable("category") final String category, final HttpServletRequest request, final Device device, final Model model) {
		List<Product> products = service.listByCategory(category);
		String digitalData = toString(dataLayer.listByCategory(category, request, device));

		model.addAttribute("products", products);
		model.addAttribute("digitalData", digitalData);

		return "products/listByCategory";
	}

	@Override
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String get(@PathVariable("id") final long id, final HttpServletRequest request, final Device device, final Model model) {
		Product product = null;
		try {
			product = service.get(id);
		} catch (Exception e) {
			
		}
		String digitalData = toString(dataLayer.get(id, request, device));
		
		model.addAttribute("product", product);
		model.addAttribute("digitalData", digitalData);
		
		return "products/view";
	}

}

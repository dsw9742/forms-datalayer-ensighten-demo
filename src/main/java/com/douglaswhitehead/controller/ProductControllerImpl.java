package com.douglaswhitehead.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglaswhitehead.model.Product;
import com.douglaswhitehead.service.ProductService;

@Controller
@RequestMapping("products")
public class ProductControllerImpl extends BaseControllerImpl implements ProductController {
	
	@Autowired
	private ProductService productService;

	@Override
	@RequestMapping(method=RequestMethod.GET)
	public String list(final HttpServletRequest request, final Device device, final Model model) {
		List<Product> products = productService.list();
		String digitalData = jackson(dataLayerService.get("products", request, device));
		
		model.addAttribute("products", products);
		model.addAttribute("digitalData", digitalData);
		
		return "products";
	}

	@Override
	
	public List<Product> listByCategory(final String category, final HttpServletRequest request, final Device device, final Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	
	public Product get(final long id, final HttpServletRequest request, final Device device, final Model model) {
		// TODO Auto-generated method stub
		return null;
	}

}

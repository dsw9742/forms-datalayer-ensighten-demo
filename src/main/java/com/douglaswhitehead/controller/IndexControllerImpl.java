package com.douglaswhitehead.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexControllerImpl extends BaseControllerImpl implements IndexController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(final HttpServletRequest request, final Device device, final Model model) {
		String digitalData = jackson(dataLayerService.get("index", request, device));
		
		model.addAttribute("digitalData", digitalData);
		
		return "index";
	}

}

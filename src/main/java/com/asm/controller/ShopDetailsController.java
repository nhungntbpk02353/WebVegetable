package com.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopDetailsController {
	@GetMapping("shop-details")
	//@GetMapping("/home")
	public String showIndex() {
		return "shop-details";
	}
	
	
}

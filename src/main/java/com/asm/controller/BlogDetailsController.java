package com.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogDetailsController {
	@GetMapping("blog-details")
	//@GetMapping("/home")
	public String showIndex() {
		return "blog-details";
	}
}


package com.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.asm.Repository.ProductRepository;
import com.asm.domain.Product;
import com.asm.service.ProductService;

@Controller
public class HomeController {
	 @Autowired
	    private ProductRepository productRepository;
	 @Autowired
		private ProductService productService;

	@GetMapping("index")
	public String showindex(Model model) {
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);
		model.addAttribute("product", new Product());
		return "index";
	}
	
	@GetMapping("/index/{id}")
	  public String getProductDetail(@PathVariable("id") Integer id, Model model) {
	    // Lấy thông tin chi tiết sản phẩm từ ProductService dựa trên productId
	    Product product = productService.getProductById(id);
	    
	    // Đặt thông tin sản phẩm vào Model để truyền cho trang chi tiết sản phẩm
	    model.addAttribute("product", product);
	    
	    // Trả về tên trang chi tiết sản phẩm
	    return "shop-details";
	  }
	
}

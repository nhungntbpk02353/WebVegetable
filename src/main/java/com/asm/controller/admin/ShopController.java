package com.asm.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.Repository.ProductRepository;
import com.asm.domain.Product;

import com.asm.service.ProductService;
import com.asm.service.SessionService;

@Controller
public class ShopController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	SessionService session;

	@GetMapping("/shop")
	public String index(Model model) {
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);
		model.addAttribute("product", new Product());
		return "shop";
	}

	@PostMapping("/search")
	public String searchProducts(@RequestParam("keywords") String keywords, Model model) {
		List<Product> searchResults = productService.searchProducts(keywords);
		model.addAttribute("products", searchResults);
		model.addAttribute("product", new Product());
		return "shop";
	}
	@GetMapping("/product/{id}")
	  public String getProductDetail(@PathVariable("id") Integer id, Model model) {
	    // Lấy thông tin chi tiết sản phẩm từ ProductService dựa trên productId
	    Product product = productService.getProductById(id);
	    
	    // Đặt thông tin sản phẩm vào Model để truyền cho trang chi tiết sản phẩm
	    model.addAttribute("product", product);
	    
	    // Trả về tên trang chi tiết sản phẩm
	    return "shop-details";
	  }
	@RequestMapping("/sort")
	public String sort(Model model, @RequestParam("field") Optional<String> field) {
	    Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
	    model.addAttribute("field", field.orElse("price").toUpperCase());
	    List<Product> products = productRepository.findAll(sort);
	    model.addAttribute("products", products);
	    return "shop";
	}
	
}

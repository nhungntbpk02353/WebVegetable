package com.asm.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.asm.Repository.ProductRepository;
import com.asm.domain.Product;
import com.asm.domain.User;
import com.asm.service.ProductService;

@Controller
@RequestMapping("/AdminProduct")
public class ProductController {
    @Autowired
    private ProductService productService;
    private final ProductRepository productRepository;
    
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
	/*
	 * @GetMapping public String showProductList(Model model) {
	 * model.addAttribute("products", productRepository.findAll());
	 * model.addAttribute("product", new Product()); return "AdminProduct"; }
	 */
    @GetMapping("")
    public String index(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Số lượng sản phẩm hiển thị trên mỗi trang
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productsPage = productRepository.findAll(pageable);
        
        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("product", new Product());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productsPage.getTotalPages());
        
        return "AdminProduct";
    }
  
    
    @GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Product item = productRepository.findById(id).orElse(null);
		model.addAttribute("product", item);
		List<Product> items = productRepository.findAll();
		model.addAttribute("products", items);
		return "AdminProduct";
	}
    
    @PostMapping("/save")
    public String saveProduct(@Valid  @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Lỗi khi thêm sản phẩm");
            return "AdminProduct";
        }

        productRepository.save(product);
        return "redirect:/AdminProduct";
    }
    
    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        productRepository.delete(product);
        return "redirect:/AdminProduct";
    }
    
    
    @PostMapping("/update")
	public String update(@Valid Product item , BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Lỗi khi cập nhật sản phẩm");
            return "AdminProduct";
        }else {
        	 productRepository.save(item);
             return "redirect:/AdminProduct";
		}

       
    }
   
	
    
}

package com.asm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asm.Repository.CartRepository;
import com.asm.Repository.ProductRepository;
import com.asm.Repository.UserRepository;
import com.asm.domain.Cart;
import com.asm.domain.Product;
import com.asm.domain.User;
import com.asm.service.CartService;



@Controller

public class ShopingCartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HttpSession session;
    @GetMapping("/cart")
	public String cart(Model model) {
    	int id=(int) session.getAttribute("userId");
        List<Cart> carts = cartRepository.findByCustomerId(id);
        model.addAttribute("carts", carts);
        
        //tính tổng tiền
        double total = 0.0;
        for (Cart cartItem : carts) {
            double itemTotal = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            total += itemTotal;
            
        }
        model.addAttribute("total", total);
		return "shoping-cart";
	}
    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam Integer productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart"; // Chuyển hướng về trang giỏ hàng sau khi xóa
    }
    
    @RequestMapping("add-to-cart/{id}")
	public String Add(@PathVariable("id") int id, Model model) {
		int cusId = (int) session.getAttribute("userId");
		String cusName = (String) session.getAttribute("username");
		System.out.println(cusId + "  " + id);
		
		Cart cart1 = cartRepository.findByCustomerIdAndProductId(cusId, id);
		
		
		User cus = (User) userRepository.findByName(cusName);
		Product prod = productRepository.getById(id);
		System.out.println("check "+cart1 == null+"  ");
		if (cart1 == null) {
			Cart newCart = new Cart(cus, prod, 1);
			cartRepository.save(newCart);
		}else {
			System.out.println("sửa");
			System.out.println("check1 "+cart1 == null+"  ");
			Cart newCart = new Cart(cart1.getId(),cus, prod, cart1.getQuantity()+1);
			cartRepository.save(newCart);
		}
//        CartDto carts = (CartDto) httpSession.getAttribute("cart");
		return "redirect:/cart";
	}
    
   
}

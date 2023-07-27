package com.asm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.Repository.CartRepository;
import com.asm.domain.Cart;
import com.asm.domain.Product;
import com.asm.domain.User;

@Service
public class CartService {
	 @Autowired
	    private CartRepository cartRepository;

	    public void addToCart(Integer customerId, Integer productId, Integer quantity) {
	        // Tạo một đối tượng Cart mới
	        Cart cart = new Cart();
	        
	        // Thiết lập thông tin khách hàng
	        User user = new User();
	        user.setId(customerId);
	        cart.setCustomer(user);
	        
	        // Thiết lập thông tin sản phẩm
	        Product product = new Product();
	        product.setId(productId);
	        cart.setProduct(product);
	        
	        // Thiết lập số lượng
	        cart.setQuantity(quantity);
	        
	        // Lưu đối tượng Cart vào cơ sở dữ liệu
	        cartRepository.save(cart);
	    }
	    
		/*
		 * public List<Cart> getCartItems(Integer id) { // Truy vấn cơ sở dữ liệu để lấy
		 * danh sách các mục trong giỏ hàng return cartRepository.findByCustomer_Id(id);
		 * }
		 */
	    
	    public void removeFromCart(Integer productId) {
	        // Tìm cart item có productId trong giỏ hàng
	        Cart cartItem = cartRepository.findByProductId(productId);
	        
	        if (cartItem != null) {
	            // Xóa cart item khỏi giỏ hàng
	            cartRepository.delete(cartItem);
	        }
	    }

}

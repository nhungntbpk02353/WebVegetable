package com.asm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asm.domain.Cart;
import com.asm.domain.Product;
import com.asm.domain.User;
@Repository
public interface CartRepository extends JpaRepository <Cart, Integer> {
    // Custom methods for Cart repository
	
	Cart findByCustomerAndProduct(User customer, Product product);
	
    List<Cart> findByCustomer(User customer);
    List<Cart> findByCustomerId(Integer customerId);
    Cart findByProductId(Integer productId);
    Cart findByCustomerIdAndProductId(int customerId, int productId);
}
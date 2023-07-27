package com.asm.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.asm.Repository.ProductRepository;
import com.asm.domain.Product;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public List<Product> searchProducts(String keywords) {
        return productRepository.findByNameContainingIgnoreCase(keywords);
    }
    public Iterable<Product> findAll(Sort sort) {
    	return productRepository.findAll(sort);
    	}
    
    
    public Product getProductById(int id) {
        // Gọi phương thức findById() của ProductRepository để lấy thông tin sản phẩm theo productId
        Optional<Product> productOptional = productRepository.findById(id);
        
        // Kiểm tra xem sản phẩm có tồn tại hay không
        if (productOptional.isPresent()) {
          return productOptional.get();
        }
        
        // Nếu không tìm thấy sản phẩm, bạn có thể xử lý theo ý muốn, ví dụ: ném một ngoại lệ, trả về null, vv.
        
        return null;
      }
}
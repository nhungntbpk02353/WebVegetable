package com.asm.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	 Page<Product> findAll(Pageable pageable);
	 //ở đây có làm phân trang k ? có // có nhìu cái lộn xộn quá  tìm theo tên upercase các thứ nưuã :((((((((((
    // Custom methods for Product repository
	/*
	 * @Override List<Product> findAll();
	 */
	 List<Product> findByNameContainingIgnoreCase(String name);
	
}
package com.asm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.domain.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom methods for Order repository
}
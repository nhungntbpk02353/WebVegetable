package com.asm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.domain.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Custom methods for Category repository
}
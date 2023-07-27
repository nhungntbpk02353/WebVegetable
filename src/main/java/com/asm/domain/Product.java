package com.asm.domain;



import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Nhập danh mục")
    @Valid
    private Category category;
	@NotBlank(message = "{NotBlank.product.name}")
    @Column(name = "name", nullable = false)
    private String name;
	@NotNull(message = "{NotNull.product.price}")
    @Column(name = "price")
    private Double price;
	@NotBlank(message = "{NotBlank.product.description}")
    @Column(name = "description")
    private String description;
	@NotBlank(message = "{NotBlank.product.img}")
    @Column(name = "image_url")
    private String imageUrl;
    
    // Constructors, getters, setters
   
}
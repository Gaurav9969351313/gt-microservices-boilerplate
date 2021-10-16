package com.rebit.productsservice.core.repositories;

import com.rebit.productsservice.core.model.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {
	
	ProductEntity findByProductId(String productId);
	ProductEntity findByProductIdOrTitle(String productId, String title);

}

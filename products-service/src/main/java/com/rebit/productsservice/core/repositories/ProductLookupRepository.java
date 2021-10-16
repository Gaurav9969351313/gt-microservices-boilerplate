package com.rebit.productsservice.core.repositories;

import com.rebit.productsservice.core.model.ProductLookupEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
	ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}

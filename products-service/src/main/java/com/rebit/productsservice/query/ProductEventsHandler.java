package com.rebit.productsservice.query;

import com.rebit.productsservice.core.events.ProductCreatedEvent;
import com.rebit.productsservice.core.model.ProductEntity;
import com.rebit.productsservice.core.repositories.ProductsRepository;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    
    private final ProductsRepository productsRepository;

    public ProductEventsHandler(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

    @EventHandler
	public void on(ProductCreatedEvent event) {

		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);

		try {
			productsRepository.save(productEntity);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}

	}
}

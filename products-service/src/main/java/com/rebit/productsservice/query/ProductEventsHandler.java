package com.rebit.productsservice.query;

import com.rebit.productsservice.core.events.ProductCreatedEvent;
import com.rebit.productsservice.core.model.ProductEntity;
import com.rebit.productsservice.core.repositories.ProductsRepository;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {
    
    private final ProductsRepository productsRepository;

    public ProductEventsHandler(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

	@ExceptionHandler(resultType=Exception.class)
	public void handle(Exception exception) throws Exception {
		throw exception;
	}
	
	@ExceptionHandler(resultType=IllegalArgumentException.class)
	public void handle(IllegalArgumentException exception) {
		// Log error message
	}

    @EventHandler
	public void on(ProductCreatedEvent event) throws Exception {

		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);

		try {
			productsRepository.save(productEntity);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}

		if (true) {
			throw new Exception("Forcing exception in event handler class"); 
		}
	}
}

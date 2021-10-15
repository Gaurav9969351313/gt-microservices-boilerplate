package com.rebit.productsservice.rest.controller;

import java.util.UUID;

import com.rebit.productsservice.command.CreateProductCommand;
import com.rebit.productsservice.rest.model.CreateProductRestModel;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {

	private final Environment env;
	private final CommandGateway commandGateway;

	public ProductsController(Environment env, CommandGateway commandGateway) {
		this.env = env;
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {
		CreateProductCommand cpc = CreateProductCommand.builder()
				.price(createProductRestModel.getPrice())
				.quantity(createProductRestModel.getQuantity())
				.title(createProductRestModel.getTitle())
				.productId(UUID.randomUUID().toString())
				.build();
		
		String returnValue;
		returnValue = commandGateway.sendAndWait(cpc);
		System.out.println("==============>" + returnValue);

		return returnValue.toString();
	}

	@GetMapping
	public String getProduct() {
		return "HTTP GET Handled " + env.getProperty("local.server.port");
	}

	@PutMapping
	public String updateProduct() {
		return "HTTP PUT Handled";
	}

	@DeleteMapping
	public String deleteProduct() {
		return "HTTP DELETE handled";
	}
}

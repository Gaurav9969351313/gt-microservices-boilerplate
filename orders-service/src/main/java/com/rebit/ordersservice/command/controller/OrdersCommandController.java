package com.rebit.ordersservice.command.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.rebit.ordersservice.command.commands.CreateOrderCommand;
import com.rebit.ordersservice.command.models.OrderCreateRest;
import com.rebit.ordersservice.core.models.OrderStatus;
import com.rebit.ordersservice.core.models.OrderSummary;
import com.rebit.ordersservice.query.FindOrderQuery;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;

	public OrdersCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}

	@PostMapping
	public String createOrder(@Valid @RequestBody OrderCreateRest order) {

		String userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";
		String orderId = UUID.randomUUID().toString();

		CreateOrderCommand createOrderCommand = CreateOrderCommand.builder().addressId(order.getAddressId())
				.productId(order.getProductId()).userId(userId).quantity(order.getQuantity()).orderId(orderId)
				.orderStatus(OrderStatus.CREATED).build();

		// SubscriptionQueryResult<OrderSummary, OrderSummary> queryResult = queryGateway.subscriptionQuery(
		// 		new FindOrderQuery(orderId), ResponseTypes.instanceOf(OrderSummary.class),
		// 		ResponseTypes.instanceOf(OrderSummary.class));

		String returnValue;
		try {
			returnValue = commandGateway.sendAndWait(createOrderCommand);
			return returnValue;// queryResult.updates().blockFirst();
		} finally {
			// queryResult.close();
		}

	}

}
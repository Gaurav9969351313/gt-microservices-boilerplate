package com.rebit.ordersservice.query;

import com.rebit.ordersservice.core.models.OrderEntity;
import com.rebit.ordersservice.core.models.OrderSummary;
import com.rebit.ordersservice.core.repositories.OrdersRepository;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class OrderQueriesHandler {

	OrdersRepository ordersRepository;

	public OrderQueriesHandler(OrdersRepository ordersRepository) {
		this.ordersRepository = ordersRepository;
	}

	@QueryHandler
	public OrderSummary findOrder(FindOrderQuery findOrderQuery) {
		OrderEntity orderEntity = ordersRepository.findByOrderId(findOrderQuery.getOrderId());
		return new OrderSummary(orderEntity.getOrderId(), 
				orderEntity.getOrderStatus(), "");
	}

}
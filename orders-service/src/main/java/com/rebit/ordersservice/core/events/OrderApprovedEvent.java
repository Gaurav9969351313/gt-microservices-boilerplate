package com.rebit.ordersservice.core.events;

import com.rebit.ordersservice.core.models.OrderStatus;

import lombok.Value;

@Value
public class OrderApprovedEvent {

	private final String orderId;
	private final OrderStatus orderStatus = OrderStatus.APPROVED;
	
}
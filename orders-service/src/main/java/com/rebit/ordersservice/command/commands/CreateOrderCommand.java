package com.rebit.ordersservice.command.commands;

import com.rebit.ordersservice.core.models.OrderStatus;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateOrderCommand {
        
    @TargetAggregateIdentifier
    public final String orderId;
    
    private final String userId;
    private final String productId;
    private final int quantity;
    private final String addressId; 
    private final OrderStatus orderStatus;
}
package com.rebit.ordersservice.query;

import com.rebit.ordersservice.core.events.OrderApprovedEvent;
import com.rebit.ordersservice.core.events.OrderCreatedEvent;
import com.rebit.ordersservice.core.events.OrderRejectedEvent;
import com.rebit.ordersservice.core.models.OrderEntity;
import com.rebit.ordersservice.core.repositories.OrdersRepository;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")
public class OrderEventsHandler {
    
    private final OrdersRepository ordersRepository;
    
    public OrderEventsHandler(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) throws Exception {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
 
        ordersRepository.save(orderEntity);
    }
    
    
    @EventHandler
    public void on(OrderApprovedEvent orderApprovedEvent) {
    	OrderEntity orderEntity = ordersRepository.findByOrderId(orderApprovedEvent.getOrderId());
   
    	if(orderEntity == null) {
    		// TODO: Do something about it
    		return;
    	}
    	
    	orderEntity.setOrderStatus(orderApprovedEvent.getOrderStatus());
    	
    	ordersRepository.save(orderEntity);
    
    }
    
    @EventHandler
    public void on(OrderRejectedEvent orderRejectedEvent) {
    	OrderEntity orderEntity = ordersRepository.findByOrderId(orderRejectedEvent.getOrderId());
    	orderEntity.setOrderStatus(orderRejectedEvent.getOrderStatus());
    	ordersRepository.save(orderEntity);
    }
    
}

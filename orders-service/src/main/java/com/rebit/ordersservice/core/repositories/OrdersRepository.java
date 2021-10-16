package com.rebit.ordersservice.core.repositories;

import com.rebit.ordersservice.core.models.OrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository <OrderEntity, String>{
	OrderEntity findByOrderId(String orderId);
}
package com.radient.ftsapp.service;

import com.radient.ftsapp.model.Order;
import com.radient.ftsapp.model.OrderItem;
import com.radient.ftsapp.repository.OrderRepository;
import com.radient.ftsapp.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
 
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order createOrder(Order order, List<OrderItem> items) {
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        for (OrderItem item : items) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return (Order) orderRepository.findByOrderId(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}

package com.radient.ftsapp.service;

import com.radient.ftsapp.model.Items;
import com.radient.ftsapp.model.Order;
import com.radient.ftsapp.model.OrderItem;
import com.radient.ftsapp.repository.ItemsRepository;
import com.radient.ftsapp.repository.OrderRepository;
import com.radient.ftsapp.repository.OrderItemRepository;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemsRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemsRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public ResponseObject<Integer> insertOrder(Order orderReq) {

        // Check if the order already exists
        if (orderRepository.findByOrderId(orderReq.getOrderId()).isPresent()) {
            return new ResponseObject<>(false, "Order ID already exists", null);
        }
        try{
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.setCustomerId(orderReq.getCustomerId());
            order.setOrderId(orderReq.getOrderId());
            order.setFoodtruckId(orderReq.getFoodtruckId());
            order.setTotalPrice(orderReq.getTotalPrice());

            Order savedOrder = orderRepository.save(order);
            int count=0;
            for(Items itemReq:orderReq.getItems()){
                Items items = new Items();
                items.setOrderId(orderReq.getOrderId());
                items.setItemId(itemReq.getItemId());
                items.setQty(itemReq.getQty());
                items.setItemPrice(itemReq.getItemPrice());
                items.setOrder(savedOrder);

                orderItemRepository.save(items);
                count++;
            }
            return new ResponseObject<>(true, "Order Inserted Successfully", count);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseObject<>(false, "Database integrity violation: " + e.getMessage(), null);
        } catch (ConstraintViolationException e) {
            return new ResponseObject<>(false, "Validation error: " + e.getMessage(), null);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }

    public List<Order> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).toList();
    }

    public Order getOrderById(String id) {
        return (Order) orderRepository.findByOrderId(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}

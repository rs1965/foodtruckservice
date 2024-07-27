package com.radient.ftsapp.repository;

import com.radient.ftsapp.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findByOrderId(String id);
}

package com.radient.ftsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.radient.ftsapp.model.Order;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Object> findByOrderId(UUID id);
}

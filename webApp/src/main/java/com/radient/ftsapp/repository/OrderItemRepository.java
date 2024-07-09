package com.radient.ftsapp.repository;

import com.radient.ftsapp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}

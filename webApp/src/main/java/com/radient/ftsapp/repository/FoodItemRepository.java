package com.radient.ftsapp.repository;

import com.radient.ftsapp.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface FoodItemRepository extends CrudRepository<FoodItem, UUID> {
}

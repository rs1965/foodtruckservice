package com.radient.ftsapp.service;

import com.radient.ftsapp.model.FoodItem;
import com.radient.ftsapp.model.Items;
import com.radient.ftsapp.model.Order;
import com.radient.ftsapp.repository.FoodItemRepository;
import com.radient.ftsapp.repository.ItemsRepository;
import com.radient.ftsapp.repository.OrderRepository;
import com.radient.ftsapp.utils.ResponseObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @Transactional
    public ResponseObject<Integer> insertItem(FoodItem foodItem) {
        try{
            FoodItem item = new FoodItem();
            item.setItemPrice(foodItem.getItemPrice());
            item.setItemDescription(foodItem.getItemDescription());
            item.setItemName(foodItem.getItemName());
            item.setItemImages(foodItem.getItemImages());
            item.setFoodTruckId(foodItem.getFoodTruckId());

            foodItemRepository.save(item);

            return new ResponseObject<>(true, "Item Inserted Successfully", 1);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "Database integrity violation: " + e.getMessage(), null);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return new ResponseObject<>(false, "Validation error: " + e.getMessage(), null);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseObject<>(false, "Unexpected error: " + e.getMessage(), null);
        }
    }

}

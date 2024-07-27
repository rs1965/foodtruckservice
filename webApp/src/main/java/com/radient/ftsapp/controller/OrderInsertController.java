package com.radient.ftsapp.controller;

import com.radient.ftsapp.config.ApiResponsesConfig;
import com.radient.ftsapp.model.OrderInsert;
import com.radient.ftsapp.service.OrderInsertService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderInsertController {

    private final OrderInsertService orderService;

    public OrderInsertController(OrderInsertService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/orders/insertOrder/delete")
    public ResponseEntity<Object> insertOrder(@Valid @RequestBody OrderInsert order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            ApiResponsesConfig response = new ApiResponsesConfig(false, errorMessage);
            return ResponseEntity.badRequest().body(response);
        }

        OrderInsert createdOrder = orderService.createOrder(order);
        ApiResponsesConfig response = new ApiResponsesConfig(true, "Data successfully stored");
        return ResponseEntity.ok(response);
    }
}

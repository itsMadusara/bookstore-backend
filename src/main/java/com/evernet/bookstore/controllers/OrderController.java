package com.evernet.bookstore.controllers;

import com.evernet.bookstore.Services.OrderService;
import com.evernet.bookstore.dto.OrderRequestDTO;
import com.evernet.bookstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestDTO request) {
        Optional<Order> order = orderService.placeOrder(request);
        return order.map(o -> ResponseEntity.ok("Order placed successfully with ID: " + o.getId()))
                .orElse(ResponseEntity.badRequest().body("Failed to place order"));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
}

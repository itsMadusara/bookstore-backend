package com.evernet.bookstore.model;

import com.evernet.bookstore.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders") // Avoid SQL reserved word conflict
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime orderDate;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Enum: PENDING, SHIPPED, DELIVERED

    // Relationships
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}

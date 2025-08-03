package com.evernet.bookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private double price;
    private int stockQuantity;
    private String description;

    // Relationships
    @OneToMany(mappedBy = "book")
    private List<OrderItem> orderItems;
}
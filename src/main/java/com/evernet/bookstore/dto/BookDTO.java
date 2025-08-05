package com.evernet.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int stockQuantity;
    private String description;
}

package com.evernet.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {
    private Long userId;
    private List<OrderItemRequestDTO> items;
}

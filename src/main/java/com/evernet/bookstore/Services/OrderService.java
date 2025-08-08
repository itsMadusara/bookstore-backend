package com.evernet.bookstore.Services;

import com.evernet.bookstore.dto.OrderItemRequestDTO;
import com.evernet.bookstore.dto.OrderRequestDTO;
import com.evernet.bookstore.model.Book;
import com.evernet.bookstore.model.Order;
import com.evernet.bookstore.model.OrderItem;
import com.evernet.bookstore.model.User;
import com.evernet.bookstore.repository.BookRepo;
import com.evernet.bookstore.repository.OrderItemRepo;
import com.evernet.bookstore.repository.OrderRepo;
import com.evernet.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    public Optional<Order> placeOrder(OrderRequestDTO orderRequestDTO){
        Optional<User> userOpt = userRepo.findById(orderRequestDTO.getUserId());
        if (userOpt.isEmpty()) return Optional.empty();

        Order order = new Order();
        order.setUser(userOpt.get());
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestDTO itemReq : orderRequestDTO.getItems()) {
            Optional<Book> bookOpt = bookRepo.findById(itemReq.getBookId());
            if (bookOpt.isEmpty()) return Optional.empty();

            Book book = bookOpt.get();

            if (book.getStockQuantity() < itemReq.getQuantity()) return Optional.empty();

            OrderItem item = new OrderItem();
            item.setBook(book);
            item.setQuantity(itemReq.getQuantity());
            item.setOrder(order);

            // Update stock
            book.setStockQuantity(book.getStockQuantity() - itemReq.getQuantity());
            bookRepo.save(book);

            orderItems.add(item);
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepo.save(order);
        orderItemRepo.saveAll(orderItems);

        return Optional.of(savedOrder);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepo.findByUserId(userId);
    }
}

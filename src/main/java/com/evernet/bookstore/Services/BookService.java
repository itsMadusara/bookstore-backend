package com.evernet.bookstore.Services;

import com.evernet.bookstore.dto.BookDTO;
import com.evernet.bookstore.dto.CreateBookRequestDTO;
import com.evernet.bookstore.model.Book;
import com.evernet.bookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public BookDTO addBook(CreateBookRequestDTO createBookRequestDTO){
        Book book = new Book();
        book.setTitle(createBookRequestDTO.getTitle());
        book.setAuthor(createBookRequestDTO.getAuthor());
        book.setIsbn(createBookRequestDTO.getIsbn());
        book.setPrice(createBookRequestDTO.getPrice());
        book.setDescription(createBookRequestDTO.getDescription());
        book.setStockQuantity(createBookRequestDTO.getStockQuantity());

        Book savedBook = bookRepo.save(book);

        return toDTO(savedBook);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPrice(book.getPrice());
        dto.setStockQuantity(book.getStockQuantity());
        dto.setDescription(book.getDescription());
        return dto;
    }
}

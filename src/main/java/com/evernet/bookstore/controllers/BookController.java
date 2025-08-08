package com.evernet.bookstore.controllers;

import com.evernet.bookstore.Services.BookService;
import com.evernet.bookstore.dto.BookDTO;
import com.evernet.bookstore.dto.CreateBookRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody CreateBookRequestDTO createBookRequestDTO){
        BookDTO bookDTO = bookService.addBook(createBookRequestDTO);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/getbooks")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}

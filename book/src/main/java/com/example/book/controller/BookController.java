package com.example.book.controller;

import com.example.book.model.Book;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public @ResponseBody List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody @Validated Book book) {
        bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @Validated @RequestBody Book book) {
        bookService.updateBook(id, book);
        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }


    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

}

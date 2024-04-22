package com.example.book.service;

import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Transactional
@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    public final BookRepository bookRepository;

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitleContainingIgnoreCase(title);

    }

    public List<Book> getAllBooks() {
        logger.info("Getting all books");
        List<Book> bookList = new ArrayList<>();
        Iterable<Book> bookIterable = bookRepository.findAll();
        for (Book book : bookIterable) {
            bookList.add(book);
        }
        logger.debug("Found {} books", bookList.size());
        return bookList;
    }


    public void saveBook(Book book) {
        logger.info("Saving book: {}", book);
        bookRepository.save(book);
        logger.info("Book saved successfully");
    }


    public void updateBook(int id, Book book) {
        logger.info("Updating book with id {}: {}", id, book);
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            book.setId(id);
            bookRepository.save(book);
            logger.info("Book updated successfully");
        }


        else { logger.error("Book with id {} not found", id);
               throw new EntityNotFoundException("Book with id " + id + " not found");
        }
    }


    public void deleteBook(int id) {
        logger.info("Deleting book with id {}", id);
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            logger.info("Book deleted successfully");
        }
       else { logger.error("Book with id {} not found", id);
              throw new EntityNotFoundException("Book with id " + id + " not found");
        }
    }


}

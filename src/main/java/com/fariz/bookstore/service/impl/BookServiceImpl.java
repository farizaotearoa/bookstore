package com.fariz.bookstore.service.impl;

import com.fariz.bookstore.entity.Book;
import com.fariz.bookstore.exception.BookNotFoundException;
import com.fariz.bookstore.repository.BookRepository;
import com.fariz.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    @Override
    public Book createBook(Book book) {
        UUID uuid = UUID.randomUUID();
        book.setId(uuid);
        return bookRepository.saveAndFlush(book);
    }

    @Transactional
    @Override
    public Book updateBook(UUID id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.saveAndFlush(book);
    }

    @Transactional
    @Override
    public void deleteBook(UUID id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book != null) {
            bookRepository.delete(book);
        }
    }
}

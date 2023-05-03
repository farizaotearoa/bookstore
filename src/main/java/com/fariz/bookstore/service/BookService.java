package com.fariz.bookstore.service;

import com.fariz.bookstore.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(UUID id);

    Book createBook(Book book);

    Book updateBook(UUID id, Book updatedBook);

    void deleteBook(UUID id);
}

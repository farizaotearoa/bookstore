package com.fariz.bookstore.exception;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(UUID id) {
        super("Book with id: " + id + " not found");
    }
}

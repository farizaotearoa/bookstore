package com.fariz.bookstore;

import com.fariz.bookstore.controller.BookController;
import com.fariz.bookstore.entity.Book;
import com.fariz.bookstore.exception.BookNotFoundException;
import com.fariz.bookstore.service.BookService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    private static List<Book> bookList;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @BeforeAll
    public static void setup() {
        bookList = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(UUID.randomUUID());
        book1.setTitle("Skip and Loafer Vol. 1");
        book1.setAuthor("Misaki Takamatsu");
        book1.setPrice(225000L);
        book1.setIsbn("9781648275883");
        Book book2 = new Book();
        book2.setId(UUID.randomUUID());
        book2.setTitle("Vinland Saga Vol. 1");
        book2.setAuthor("Makoto Yukimura");
        book2.setPrice(490000L);
        book2.setIsbn("9781612624204");
        bookList.add(book1);
        bookList.add(book2);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(bookList);

        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(bookList.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].title").value("Skip and Loafer Vol. 1"))
                .andExpect(jsonPath("$[0].author").value("Misaki Takamatsu"))
                .andExpect(jsonPath("$[0].price").value(225000L))
                .andExpect(jsonPath("$[0].isbn").value("9781648275883"))
                .andExpect(jsonPath("$[1].id").value(bookList.get(1).getId().toString()))
                .andExpect(jsonPath("$[1].title").value("Vinland Saga Vol. 1"))
                .andExpect(jsonPath("$[1].author").value("Makoto Yukimura"))
                .andExpect(jsonPath("$[1].price").value(490000L))
                .andExpect(jsonPath("$[1].isbn").value("9781612624204"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = bookList.get(1);
        UUID id = book.getId();

        when(bookService.getBookById(id)).thenReturn(book);

        mockMvc.perform(get("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Vinland Saga Vol. 1"))
                .andExpect(jsonPath("$.author").value("Makoto Yukimura"))
                .andExpect(jsonPath("$.price").value(490000L))
                .andExpect(jsonPath("$.isbn").value("9781612624204"));
    }

    @Test
    public void testGetBookByIdNotFound() {
        UUID id = UUID.randomUUID();

        when(bookService.getBookById(id)).thenThrow(new BookNotFoundException(id));

        assertThrows(BookNotFoundException.class, () ->
                bookService.getBookById(id)
        );
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book();
        book.setTitle("Berserk Deluxe Vol. 1");
        book.setAuthor("Kentaro Miura");
        book.setPrice(1250000L);
        book.setIsbn("9781506711980");

        when(bookService.createBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(book, Book.class)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Berserk Deluxe Vol. 1"))
                .andExpect(jsonPath("$.author").value("Kentaro Miura"))
                .andExpect(jsonPath("$.price").value(1250000L))
                .andExpect(jsonPath("$.isbn").value("9781506711980"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book existingBook = bookList.get(0);
        Book updatedBook = new Book();
        updatedBook.setId(existingBook.getId());
        updatedBook.setTitle("Berserk Deluxe Vol. 1");
        updatedBook.setAuthor("Kentaro Miura");
        updatedBook.setPrice(1250000L);
        updatedBook.setIsbn("9781506711980");

        when(bookService.updateBook(any(UUID.class), any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/books/{id}", existingBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(updatedBook, Book.class)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingBook.getId().toString()))
                .andExpect(jsonPath("$.title").value("Berserk Deluxe Vol. 1"))
                .andExpect(jsonPath("$.author").value("Kentaro Miura"))
                .andExpect(jsonPath("$.price").value(1250000L))
                .andExpect(jsonPath("$.isbn").value("9781506711980"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = bookList.get(0);

        when(bookService.getBookById(book.getId())).thenReturn(book);
        doNothing().when(bookService).deleteBook(book.getId());

        mockMvc.perform(delete("/books/{id}", book.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
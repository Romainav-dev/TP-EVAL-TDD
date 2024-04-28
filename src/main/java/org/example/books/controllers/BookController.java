package org.example.books.controllers;

import org.example.books.services.BookService;
import org.example.books.validator.BookValidator;
import org.example.books.models.Book;
import org.example.books.repositories.BookRepository;

public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void addBook(String isbn, String title, String author, String editor, String format, boolean available) {
        if (!BookValidator.validateFormat(format)) {
            throw new IllegalArgumentException("Format invalide");
        }

        bookService.addBook(isbn, title, author, editor, format, available);
    }

    public void updateBook(String isbn, String title, String author, String editor, String format, boolean available) {
        bookService.updateBook(isbn, title, author, editor, format, available);
    }

    public void deleteBook(String isbn) {
        bookService.deleteBook(isbn);
    }

    public Book displayBookByIsbn(String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    public Book displayBookByTitle(String title) {
        return bookService.getBookByTitle(title);
    }

    public Book displayBookByAuthor(String author) {
        return bookService.getBookByAuthor(author);
    }

}

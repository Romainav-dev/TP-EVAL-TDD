package org.example.controllers;

import org.example.books.BookValidator;
import org.example.models.Book;
import org.example.repositories.BookRepository;

public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String isbn, String title, String author, String editor, String format, boolean available) {
        if (!BookValidator.validateFormat(format)) {
            throw new IllegalArgumentException("Format invalide");
        }

        Book book = new Book(isbn, title, author, editor, format, available);
        bookRepository.save(book);
    }

    public void updateBook(String isbn, String title, String author, String editor, String format, boolean available) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setEditor(editor);
            book.setFormat(format);
            book.setAvailable(available);
            bookRepository.save(book);
        } else {
            System.out.println("Le livre avec l'ISBN " + isbn + " n'existe pas.");
        }
    }

    public void deleteBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book != null) {
            bookRepository.delete(book);
        } else {
            System.out.println("Le livre avec l'ISBN " + isbn + " n'existe pas.");
        }
    }

    public void displayBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Le livre avec l'ISBN " + isbn + " n'existe pas.");
        }
    }

    public void displayBookByTitle(String title){
        Book book = bookRepository.findByTitle(title);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Le livre avec le titre " + title + " n'existe pas.");
        }
    }

    public void displayBookByAuthor(String author){
        Book book = bookRepository.findByAuthor(author);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Le livre avec l'auteur : " + author + " n'existe pas.");
        }
    }
}

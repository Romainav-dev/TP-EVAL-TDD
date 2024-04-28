package org.example.books.services;

import org.example.books.models.Book;
import org.example.books.repositories.BookRepository;
import org.example.books.validator.BookValidator;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
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

    public Book getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Le livre avec l'ISBN " + isbn + " n'existe pas.");
        }
        return book;
    }

    public Book getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        if (book == null) {
            throw new IllegalArgumentException("Le livre avec le titre " + title + " n'existe pas.");
        }
        return book;
    }

    public Book getBookByAuthor(String author) {
        Book book = bookRepository.findByAuthor(author);
        if (book == null) {
            throw new IllegalArgumentException("Le livre avec l'auteur : " + author + " n'existe pas.");
        }
        return book;
    }
}

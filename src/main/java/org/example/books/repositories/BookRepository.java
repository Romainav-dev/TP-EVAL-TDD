package org.example.books.repositories;
import org.example.books.models.Book;

public interface BookRepository {
    Book findByIsbn(String isbn);
    Book findByTitle(String title);
    Book findByAuthor(String author);
    void save(Book book);
    void delete(Book book);
}


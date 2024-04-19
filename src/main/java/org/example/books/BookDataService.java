package org.example.books;

import org.example.exceptions.BookNotFoundException;
import org.example.models.Book;

public interface BookDataService {
    Book fetchBook(String isbn) throws BookNotFoundException;
}

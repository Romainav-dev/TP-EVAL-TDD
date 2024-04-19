package org.example.books;

import org.example.exceptions.BookNotFoundException;
import org.example.models.Book;

public class BookInfoService {

    private final BookDataService databaseService;
    private final BookDataService webService;
    private final BookDatabaseUpdater databaseUpdater;

    public BookInfoService(BookDataService databaseService, BookDataService webService, BookDatabaseUpdater databaseUpdater){
        this.databaseService = databaseService;
        this.webService = webService;
        this.databaseUpdater = databaseUpdater;
    }

    public Book getBookInfo(String isbn) throws BookNotFoundException {
        try {
            return databaseService.fetchBook(isbn);
        } catch (BookNotFoundException e) {
            System.out.println("Book not found in database: " + e.getMessage());
        }
        Book webServiceBook = webService.fetchBook(isbn);
        databaseUpdater.updateBook(webServiceBook);
        return webServiceBook;
    }

    /*public void createBookInDatabase(String isbn, String title, String author){
        Book book = new Book(isbn, title, author);
        databaseUpdater.updateBook(book);
    }*/
}

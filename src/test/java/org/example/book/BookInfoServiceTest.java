package org.example.book;

import org.example.books.BookDataService;
import org.example.books.BookDatabaseUpdater;
import org.example.books.BookInfoService;
import org.example.exceptions.BookNotFoundException;
import org.example.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookInfoServiceTest {

    private BookInfoService bookInfoService;
    private BookDataService mockDatabaseService;
    private BookDataService mockWebService;
    private BookDatabaseUpdater mockDatabaseUpdater;

    @BeforeEach
    public void initBeforeEach() {
        mockDatabaseService = mock(BookDataService.class);
        mockWebService = mock(BookDataService.class);
        mockDatabaseUpdater = mock(BookDatabaseUpdater.class);
        bookInfoService = new BookInfoService(mockDatabaseService, mockWebService, mockDatabaseUpdater);
    }

    @Test
    public void getBookFromWebserviceIfMissingFromDatabase() throws BookNotFoundException {
        // Given
        String isbn = "2210765528";
        Book expectedBook = new Book(isbn, "Solo leveling", "Chu-Gong", "A-1 Pictures", "Grand Format", true);

        when(mockDatabaseService.fetchBook(isbn)).thenThrow(new BookNotFoundException());

        when(mockWebService.fetchBook(isbn)).thenReturn(expectedBook);

        Book foundBook = bookInfoService.getBookInfo(isbn);

        assertEquals(expectedBook, foundBook);
    }

    @Test
    public void onlyDatabaseIsCalledIfDatabaseIsPresentInDatabase() throws BookNotFoundException {
        // Given
        String isbn = "2210765528";
        Book expectedBook = new Book(isbn, "Solo leveling", "Chu-Gong", "A-1 Pictures", "Grand Format", true);

        when(mockDatabaseService.fetchBook(isbn)).thenReturn(expectedBook);

        when(mockWebService.fetchBook(isbn)).thenReturn(expectedBook);

        Book foundBook = bookInfoService.getBookInfo(isbn);

        assertEquals(expectedBook, foundBook);
        verifyNoInteractions(mockWebService);
    }

    @Test
    public void absentBookShouldThrowException() throws BookNotFoundException {
        // Given
        String isbn = "2210765528";
        Book expectedBook = new Book(isbn, "Solo leveling", "Chu-Gong", "A-1 Pictures", "Grand Format", true);

        when(mockDatabaseService.fetchBook(isbn)).thenThrow(new BookNotFoundException());

        when(mockWebService.fetchBook(isbn)).thenThrow(new BookNotFoundException());

        assertThrows(BookNotFoundException.class, () -> {
            bookInfoService.getBookInfo(isbn);
        });
    }

    @Test
    public void bookIsStoredInDatabaseWhenFetchFromWebservice() throws BookNotFoundException {
        // Given
        String isbn = "2210765528";
        Book expectedBook = new Book(isbn, "Solo leveling", "Chu-Gong", "A-1 Pictures", "Grand Format", true);

        when(mockDatabaseService.fetchBook(isbn)).thenThrow(new BookNotFoundException());

        when(mockWebService.fetchBook(isbn)).thenReturn(expectedBook);

        Book foundBook = bookInfoService.getBookInfo(isbn);

        verify(mockDatabaseUpdater).updateBook(expectedBook);
    }
}

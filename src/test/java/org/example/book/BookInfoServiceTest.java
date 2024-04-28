package org.example.book;

import org.example.books.BookDataService;
import org.example.books.BookDatabaseUpdater;
import org.example.books.services.BookInfoService;
import org.example.exceptions.BookNotFoundException;
import org.example.books.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
        String isbn = "2210765528";
        Book expectedBook = new Book(isbn, "Solo leveling", "Chu-Gong", "A-1 Pictures", "Grand Format", true);

        when(mockDatabaseService.fetchBook(isbn)).thenThrow(new BookNotFoundException());

        when(mockWebService.fetchBook(isbn)).thenReturn(expectedBook);

        Book foundBook = bookInfoService.getBookInfo(isbn);

        assertEquals(expectedBook, foundBook);
    }

    @Test
    public void onlyDatabaseIsCalledIfDatabaseIsPresentInDatabase() throws BookNotFoundException {
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
        String isbn = "2210765528";
        Book expectedBook = new Book(isbn, "Solo leveling", "Chu-Gong", "A-1 Pictures", "Grand Format", true);

        when(mockDatabaseService.fetchBook(isbn)).thenThrow(new BookNotFoundException());

        when(mockWebService.fetchBook(isbn)).thenReturn(expectedBook);

        Book foundBook = bookInfoService.getBookInfo(isbn);

        verify(mockDatabaseUpdater).updateBook(expectedBook);
    }

    @Test
    public void testRetrieveMissingInformationFromWebService() throws BookNotFoundException {
        // Préparation des données
        String isbn = "2210765528"; // ISBN d'un livre avec des informations manquantes
        Book expectedBook = new Book(isbn, "Solo leveling", "Chu-Gong", "A-1 Pictures", "Grand Format", true); // Livre avec des informations manquantes

        // Mock du service web
        BookDataService webService = mock(BookDataService.class);
        // Définir le comportement du mock pour cet ISBN spécifique
        when(webService.fetchBook(isbn))
                .thenReturn(new Book(isbn, "Solo leveling", "Chu-Gong", null, null, false)); // Seules les informations manquantes sont incluses

        // Création du service d'informations sur les livres avec le mock du service web
        BookInfoService bookInfoService = new BookInfoService(mockDatabaseService, webService, mockDatabaseUpdater);

        // Appel de la méthode à tester
        Book retrievedBook = bookInfoService.getBookInfo(isbn);

        // Vérification des résultats
        assertNotNull(retrievedBook);
        assertEquals(expectedBook, retrievedBook);
        // Vérifiez spécifiquement que les informations manquantes ont été récupérées correctement
        assertEquals(expectedBook.getTitle(), retrievedBook.getTitle());
        assertEquals(expectedBook.getAuthor(), retrievedBook.getAuthor());
    }
}

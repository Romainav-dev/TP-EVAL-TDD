package org.example.book;

import org.example.books.models.Book;
import org.example.books.repositories.BookRepository;
import org.example.books.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void testAddBookWithAllInfo() {
        String isbn = "1234567890";
        String title = "Title";
        String author = "Author";
        String editor = "Editor";
        String format = "Poche";
        boolean available = true;

        bookService.addBook(isbn, title, author, editor, format, available);

        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void testAddBookWithInvalidFormat() {
        String isbn = "1234567890";
        String title = "Title";
        String author = "Author";
        String editor = "Editor";
        String format = "Invalid Format";
        boolean available = true;

        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(isbn, title, author, editor, format, available));

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void testUpdateExistingBook() {
        // Préparer les données de test
        String isbn = "1234567890";
        String title = "Ancien titre";
        String author = "Auteur";
        String editor = "Editeur";
        String format = "Poche";
        boolean available = true;
        Book existingBook = new Book(isbn, title, author, editor, format, available);

        String newTitle = "Nouveau titre";
        String newAuthor = "Nouvel auteur";
        String newEditor = "Nouvel éditeur";
        String newFormat = "Broché";
        boolean newAvailable = false;

        when(bookRepository.findByIsbn(isbn)).thenReturn(existingBook);

        bookService.updateBook(isbn, newTitle, newAuthor, newEditor, newFormat, newAvailable);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captor.capture());

        Book capturedBook = captor.getValue();

        assertEquals(newTitle, capturedBook.getTitle());
        assertEquals(newAuthor, capturedBook.getAuthor());
        assertEquals(newEditor, capturedBook.getEditor());
        assertEquals(newFormat, capturedBook.getFormat());
        assertEquals(newAvailable, capturedBook.getAvailable());
    }

    @Test
    public void testUpdateNoExistingBook() {
        String isbn = "1234567890";
        String title = "Ancien titre";
        String author = "Auteur";
        String editor = "Editeur";
        String format = "Poche";
        boolean available = true;

        when(bookRepository.findByIsbn(isbn)).thenReturn(null);

        String newTitle = "Nouveau titre";
        String newAuthor = "Nouvel auteur";
        String newEditor = "Nouvel éditeur";
        String newFormat = "Broché";
        boolean newAvailable = false;

        bookService.updateBook(isbn, newTitle, newAuthor, newEditor, newFormat, newAvailable);

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void testDeleteExistingBook() {
        String isbn = "1234567890";
        String title = "Ancien titre";
        String author = "Auteur";
        String editor = "Editeur";
        String format = "Poche";
        boolean available = true;
        Book existingBook = new Book(isbn, title, author, editor, format, available);

        when(bookRepository.findByIsbn(isbn)).thenReturn(existingBook);

        bookService.deleteBook(isbn);

        verify(bookRepository).delete(existingBook);
    }

    @Test
    public void testDeleteNoExistingBook() {
        String isbn = "1234567890";

        when(bookRepository.findByIsbn(isbn)).thenReturn(null);

        bookService.deleteBook(isbn);

        verify(bookRepository, never()).delete(any(Book.class));
    }

    @Test
    public void testFindBookByISBN(){
        String isbn = "1234567890";
        String title = "Ancien titre";
        String author = "Auteur";
        String editor = "Editeur";
        String format = "Poche";
        boolean available = true;
        Book existingBook = new Book(isbn, title, author, editor, format, available);

        when(bookRepository.findByIsbn(isbn)).thenReturn(existingBook);

        bookService.getBookByIsbn(isbn);

        verify(bookRepository).findByIsbn(isbn);
    }

    @Test
    public void testFindNoExistingBookByISBN(){
        String isbn = "1234567890";

        when(bookRepository.findByIsbn(isbn)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> bookService.getBookByIsbn(isbn));
    }

    @Test
    public void testFindBookByTitle(){
        String isbn = "1234567890";
        String title = "Ancien titre";
        String author = "Auteur";
        String editor = "Editeur";
        String format = "Poche";
        boolean available = true;
        Book existingBook = new Book(isbn, title, author, editor, format, available);

        when(bookRepository.findByTitle(title)).thenReturn(existingBook);

        bookService.getBookByTitle(title);

        verify(bookRepository).findByTitle(title);
    }

    @Test
    public void testFindNoExistingBookByTitle(){
        String title = "Ancien titre";

        when(bookRepository.findByTitle(title)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> bookService.getBookByTitle(title));
    }

    @Test
    public void testFindBookByAuthor(){
        String isbn = "1234567890";
        String title = "Ancien titre";
        String author = "Auteur";
        String editor = "Editeur";
        String format = "Poche";
        boolean available = true;
        Book existingBook = new Book(isbn, title, author, editor, format, available);

        when(bookRepository.findByAuthor(author)).thenReturn(existingBook);

        bookService.getBookByAuthor(author);

        verify(bookRepository).findByAuthor(author);
    }

    @Test
    public void testFindNoExistingBookByAuthor(){
        String author = "Auteur";

        when(bookRepository.findByAuthor(author)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> bookService.getBookByAuthor(author));
    }
}


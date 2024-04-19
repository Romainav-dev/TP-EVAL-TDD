package org.example.book;

import org.example.controllers.BookController;
import org.example.models.Book;
import org.example.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookController = new BookController(bookRepository);
    }

    @Test
    public void testAddBookWithAllInfo() {
        String isbn = "1234567890";
        String title = "Title";
        String author = "Author";
        String editor = "Editor";
        String format = "Poche";
        boolean available = true;

        bookController.addBook(isbn, title, author, editor, format, available);

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

        assertThrows(IllegalArgumentException.class, () -> bookController.addBook(isbn, title, author, editor, format, available));

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void testUpdateExistingBook() {
        String isbn = "1234567890";
        String title = "Ancien titre";
        String author = "Auteur";
        String editor = "Editeur";
        String format = "Poche";
        boolean available = true;
        Book existingBook = new Book(isbn, title, author, editor, format, available);

        when(bookRepository.findByIsbn(isbn)).thenReturn(existingBook);

        String newTitle = "Nouveau titre";
        String newAuthor = "Nouvel auteur";
        String newEditor = "Nouvel éditeur";
        String newFormat = "Broché";
        boolean newAvailable = false;

        bookController.updateBook(isbn, newTitle, newAuthor, newEditor, newFormat, newAvailable);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captor.capture());

        Book capturedBook = captor.getAllValues().get(captor.getAllValues().size() - 1);

        assertEquals(newTitle, capturedBook.getTitle());
        assertEquals(newAuthor, capturedBook.getAuthor());
        assertEquals(newEditor, capturedBook.getEditor());
        assertEquals(newFormat, capturedBook.getFormat());
        assertEquals(newAvailable, capturedBook.getAvailable());
    }

    @Test
    public void testUpdateNonExistingBook() {
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

        bookController.updateBook(isbn, newTitle, newAuthor, newEditor, newFormat, newAvailable);

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

        bookController.deleteBook(isbn);

        verify(bookRepository).delete(existingBook);
    }

    @Test
    public void testDeleteNonExistingBook() {
        String isbn = "1234567890";

        when(bookRepository.findByIsbn(isbn)).thenReturn(null);

        bookController.deleteBook(isbn);

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

        bookController.displayBookByIsbn(isbn);

        verify(bookRepository).findByIsbn(isbn);
    }

    @Test
    public void testFindNonExistingBookByISBN(){
        String isbn = "1234567890";

        when(bookRepository.findByIsbn(isbn)).thenReturn(null);

        bookController.displayBookByIsbn(isbn);

        verify(bookRepository).findByIsbn(isbn);
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

        bookController.displayBookByTitle(title);

        verify(bookRepository).findByTitle(title);
    }

    @Test
    public void testFindNonExistingBookByTitle(){
        String title = "Ancien titre";

        when(bookRepository.findByTitle(title)).thenReturn(null);

        bookController.displayBookByTitle(title);

        verify(bookRepository).findByTitle(title);
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

        bookController.displayBookByAuthor(author);

        verify(bookRepository).findByAuthor(author);
    }

    @Test
    public void testFindNonExistingBookByAuthor(){
        String author = "Auteur";

        when(bookRepository.findByAuthor(author)).thenReturn(null);

        bookController.displayBookByAuthor(author);

        verify(bookRepository).findByAuthor(author);
    }
}


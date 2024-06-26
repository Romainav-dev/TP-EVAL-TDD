package org.example.book;

import org.example.books.models.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    public void testAddBookWithoutISBN() {
        Book book = new Book("", "Title", "Author", "Editor", "Format", true);
        assertThrows(IllegalArgumentException.class, book::validate);
    }

    @Test
    public void testAddBookWithoutTitle() {
        Book book = new Book("1234567890", "", "Author", "Editor", "Format", true);
        assertThrows(IllegalArgumentException.class, book::validate);
    }

    @Test
    public void testAddBookWithoutAuthor() {
        Book book = new Book("1234567890", "Title", "", "Editor", "Format", true);
        assertThrows(IllegalArgumentException.class, book::validate);
    }

    @Test
    public void testAddBookWithoutEditor() {
        Book book = new Book("1234567890", "Title", "Author", "", "Format", true);
        assertThrows(IllegalArgumentException.class, book::validate);
    }

    // Test du format du livre
    @Test
    public void testAddBookWithoutFormat() {
        Book book = new Book("1234567890", "Title", "Author", "Editor", "", true);
        assertThrows(IllegalArgumentException.class, book::validate);
    }

    @Test
    public void testBookInvalidFormat() {
        Book bookInvalidFormat = new Book("1234567890", "Title", "Author", "Editor", "Invalid Format", true);
        assertThrows(IllegalArgumentException.class, bookInvalidFormat::validate);
    }

    @Test
    public void testBookValidFormatPoche() {
        Book bookValidFormatPoche = new Book("1234567890", "Title", "Author", "Editor", "Poche", true);
        assertDoesNotThrow(bookValidFormatPoche::validate);
    }

    @Test
    public void testBookValidFormatBroche() {
        Book bookValidFormatBroche = new Book("1234567890", "Title", "Author", "Editor", "Broché", true);
        assertDoesNotThrow(bookValidFormatBroche::validate);
    }

    @Test
    public void testBookValidFormatGrandFormat() {
        Book bookValidFormatGrandFormat = new Book("1234567890", "Title", "Author", "Editor", "Grand format", true);
        assertDoesNotThrow(bookValidFormatGrandFormat::validate);
    }

    @Test
    public void testAddValidBook() {
        Book book = new Book("1234567890", "Title", "Author", "Editor", "Poche", true);
        assertDoesNotThrow(book::validate);
    }

    @Test
    public void testTitreLongueurMaximaleAutoriseeDepasse() {
        Book book = new Book("1234567890", "Les jolies petite fleurs dans la forêt", "Thibault", "Gallimard", "Poche", true);
        assertThrows(IllegalArgumentException.class, book::validate);
    }

    @Test
    public void testTitreLongueurValide() {
        Book book = new Book("1234567890", "Les jolies petites fleurs", "Thibault", "Gallimard", "Poche", true);
        assertDoesNotThrow(book::validate);
    }

    @Test
    public void testNomAuteurLongueurMaximaleAutoriseeDepasse() {
        Book book = new Book("1234567890", "Title", "Jean Pierre Louis", "Gallimard", "Poche", true);
        assertThrows(IllegalArgumentException.class, book::validate);
    }

    @Test
    public void testNomAuteurLongueurValide() {
        Book book = new Book("1234567890", "Title", "Thibault", "Gallimard", "Poche", true);
        assertDoesNotThrow(book::validate);
    }

    @Test
    public void testNomEditeurLongueurMaximaleAutoriseeDepasse() {
        Book book = new Book("1234567890", "Title", "Thibault", "Les flamants roses Romans", "Poche", true);
        assertThrows(IllegalArgumentException.class, book::validate);
    }

    @Test
    public void testNomEditeurLongueurValide() {
        Book book = new Book("1234567890", "Title", "Thibault", "Hachette Romans", "Poche", true);
        assertDoesNotThrow(book::validate);
    }
}

package org.example.book;

import org.example.exceptions.InvalidIsbnCharactersException;
import org.example.exceptions.InvalidIsbnLengthException;
import org.example.books.IsbnValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsbnValidatorTest {

    @Test
    public void testValid10CharISBNCodeIsValid() {
        IsbnValidator isbnValidator = new IsbnValidator();
        assertTrue(isbnValidator.validateISBN("2210765528"));
    }

    @Test
    public void testValid10CharISBNCodeIsNotValid() {
        IsbnValidator isbnValidator = new IsbnValidator();
        assertFalse(isbnValidator.validateISBN("2210765548"));
    }

    @Test
    public void testValid10CharISBNCodeEndWithXIsValid() {
        IsbnValidator isbnValidator = new IsbnValidator();
        assertTrue(isbnValidator.validateISBN("955203051X"));
    }

    @Test
    public void testInvalidLengthISBNCodeThrowsError() {
        IsbnValidator isbnValidator = new IsbnValidator();
        assertThrows(InvalidIsbnLengthException.class, () -> isbnValidator.validateISBN("123"));
    }

    @Test
    public void testNonNumericCharISBNCodeThrowsError() {
        IsbnValidator isbnValidator = new IsbnValidator();
        assertThrows(InvalidIsbnCharactersException.class, () -> isbnValidator.validateISBN("123A456789"));
    }

    @Test
    public void testValid13CharISBNCodeIsValid() {
        IsbnValidator isbnValidator = new IsbnValidator();
        assertTrue(isbnValidator.validateISBN("9782382882542"));
    }

    @Test
    public void testValid13CharISBNCodeIsNotValid() {
        IsbnValidator isbnValidator = new IsbnValidator();
        assertFalse(isbnValidator.validateISBN("9782210765526"));
    }

    @Test
    public void testInvalidCharactersInISBNCodeThrowsError() {
        IsbnValidator isbnValidator = new IsbnValidator();
        assertThrows(InvalidIsbnCharactersException.class, () -> isbnValidator.validateISBN("148955a030514"));
    }
}

package org.example.books;

import org.example.models.Book;

public class BookValidator {
    private static final String[] VALID_FORMATS = {"Poche", "Broché", "Grand format"};

    public static boolean validateFormat(String format) {
        for (String validFormat : VALID_FORMATS) {
            if (validFormat.equalsIgnoreCase(format)) {
                return true;
            }
        }
        return false;
    }

    // Valide un livre en vérifiant ses champs
    public static void validateBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (book.getEditor() == null || book.getEditor().isEmpty()) {
            throw new IllegalArgumentException("Editor cannot be empty");
        }
        if (book.getFormat() == null || book.getFormat().isEmpty()) {
            throw new IllegalArgumentException("Format cannot be empty");
        }
        if (!validateFormat(book.getFormat())) {
            throw new IllegalArgumentException("Invalid format: " + book.getFormat());
        }
    }
}

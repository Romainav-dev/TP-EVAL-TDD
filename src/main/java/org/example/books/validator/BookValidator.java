package org.example.books.validator;

import org.example.books.models.Book;

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
        if (book.getTitle() == null || book.getTitle().isEmpty() || !TitreLivreValide(book.getTitle())) {
            throw new IllegalArgumentException("Title cannot be empty or longer than 15 characters");
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty() || !NomAuteurValide(book.getAuthor())) {
            throw new IllegalArgumentException("Author cannot be empty or longer than 15 characters");
        }
        if (book.getEditor() == null || book.getEditor().isEmpty() || !NomEditeurValide(book.getEditor())) {
            throw new IllegalArgumentException("Editor cannot be empty or longer than 15 characters");
        }
        if (book.getFormat() == null || book.getFormat().isEmpty() || !validateFormat(book.getFormat())) {
            throw new IllegalArgumentException("Format cannot be empty or invalid format");
        }
    }

    public static boolean TitreLivreValide(String title) {
        return title.length() <= 30;
    }
    public static boolean NomAuteurValide(String author) {
        return author.length() <= 15;
    }

    public static boolean NomEditeurValide(String editor) {
        return editor.length() <= 20;
    }
}

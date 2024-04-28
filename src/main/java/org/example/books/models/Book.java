package org.example.books.models;

import org.example.interfaces.Validatable;
import org.example.books.validator.BookValidator;

public class Book implements Validatable {

    private String isbn;
    private String title;
    private String author;
    private String editor;
    private String format;
    private boolean available;

    public Book(String isbn, String title, String author, String editor, String format, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.format = format;
        this.available = available;
    }

    public String getIsbn() {
        return isbn;
    }

    public String setIsbn(String isbn) {
        return this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void validate() {
        BookValidator.validateBook(this);
    }
}

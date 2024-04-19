package org.example.models;

import org.example.interfaces.Validatable;
import org.example.books.BookValidator;

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

    public String getIsbn(){
        return isbn;
    }
    public String setIsbn(String isbn){
        return this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }
    public String setTitle(String title){
        return this.title = this.title;
    }

    public String getAuthor() {
        return author;
    }
    public String setAuthor(String author){
        return this.author = this.author;
    }

    public String getEditor() {
        return editor;
    }
    public String setEditor(String editor){
        return this.editor = this.editor;
    }

    public String getFormat() {
        return format;
    }
    public String setFormat(String format){
        return this.format = this.format;
    }

    public boolean getAvailable() {
        return available;
    }
    public boolean setAvailable(boolean available){
        return this.available = this.available;
    }

    @Override
    public void validate() {
        BookValidator.validateBook(this);
    }
}

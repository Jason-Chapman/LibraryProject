package com.company;

public class Book {
    protected String Title;
    protected String ISBN;
    protected String Author;
    protected String Genre;

    public Book(String title, String ISBN, String author, String genre) {
        this.Title = title;
        this.ISBN = ISBN;
        this.Author = author;
        this.Genre = genre;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}

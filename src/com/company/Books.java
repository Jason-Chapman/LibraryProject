package com.company;

public class Books {
    private String Title;
    private String ISBN;
    private String Author;
    private String Genre;

    public Books(String Title, String ISBN, String Author, String Genre) {
        this.Title = Title;
        this.ISBN = ISBN;
        this.Author = Author;
        this.Genre = Genre;

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

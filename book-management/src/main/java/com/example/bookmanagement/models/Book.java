package com.example.bookmanagement.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Book {

    @Positive
    private int id;

    @NotNull
    private String title;

    private String author;

    private String publisher;

    private String thumbnail;
    private Category category;

    public Book() {

    }

//    public Book(@Positive int id, @NotNull String title, @NotNull String author, @NotNull String publisher, @NotNull String thumbnail, Category category) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.publisher = publisher;
//        this.thumbnail = thumbnail;
//        this.category = category;
//    }



    public Book(@Positive int id, @NotNull String title, @NotNull String author, @NotNull String publisher, @NotNull String thumbnail) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }


}

package com.example.bookmanagement.models;
import java.util.List;
public class Category {
    private Integer id;
    private String name;
    List <Book> books;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Category(Integer id, String name, List<Book> books) {

        this.id = id;
        this.name = name;
        this.books = books;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}



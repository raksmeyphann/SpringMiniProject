package com.example.bookmanagement.services;

import com.example.bookmanagement.models.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book findOne(Integer id);

    boolean update(Book book);

    boolean remove(Integer id);

    Integer count();

    boolean create(Book book);
}

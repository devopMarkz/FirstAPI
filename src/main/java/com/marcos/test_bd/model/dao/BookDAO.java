package com.marcos.test_bd.model.dao;

import com.marcos.test_bd.model.entities.Book;

import java.util.List;

public interface BookDAO {

    void update(Long id, Book book);
    void deleteById(Long id);
    void insert(Book book);
    Book findById(int id);
    List<Book> findAll();

}

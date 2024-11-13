package com.marcos.test_bd.model.dao;

import com.marcos.test_bd.model.entities.Autor;

import java.util.List;

public interface AutorDAO {

    void update(Long id, Autor autor);
    void deleteById(Long id);
    void insert(Autor autor);
    Autor findById(Long id);
    List<Autor> findAll();

}

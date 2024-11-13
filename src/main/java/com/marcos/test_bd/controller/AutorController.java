package com.marcos.test_bd.controller;

import com.marcos.test_bd.model.dao.AutorDAO;
import com.marcos.test_bd.model.dao.DAOFactory;
import com.marcos.test_bd.model.entities.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autors")
public class AutorController {

    private AutorDAO autorDAO = DAOFactory.createAutorDAO();

    @GetMapping("/all")
    public ResponseEntity<List<Autor>> getAutors(){
        List<Autor> autorList = autorDAO.findAll();
        return ResponseEntity.ok(autorList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
        Autor autor = null;
        try {
            autor = autorDAO.findById(id);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(autor);
    }

    @PostMapping("/")
    public ResponseEntity<List<Autor>> createAutor(@RequestBody Autor autor) {
        autorDAO.insert(autor);
        return ResponseEntity.ok(autorDAO.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Long id,@RequestBody Autor autor) {
        autorDAO.update(id, autor);
        Autor autorResposta = autorDAO.findById(id);
        return ResponseEntity.ok(autorResposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutorById(@PathVariable Long id) {
        autorDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

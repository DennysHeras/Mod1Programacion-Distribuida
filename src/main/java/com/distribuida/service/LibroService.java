package com.distribuida.service;

import com.distribuida.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> findAll();
    Optional<Libro> findById(Integer id);
    Libro save(Libro libro);
    Libro update(Libro libro);
    void deleteById(Integer id);
}




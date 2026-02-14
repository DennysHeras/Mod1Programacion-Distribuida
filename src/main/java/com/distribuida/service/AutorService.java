package com.distribuida.service;

import com.distribuida.model.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    List<Autor> findAll();
    Optional<Autor> findById(Integer id);
    Autor save(Autor autor);
    Autor update(Autor autor);
    void deleteById(Integer id);
}




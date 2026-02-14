package com.distribuida.service;

import com.distribuida.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> findAll();
    Optional<Categoria> findById(Integer id);
    Categoria save(Categoria categoria);
    Categoria update(Categoria categoria);
    void deleteById(Integer id);
}




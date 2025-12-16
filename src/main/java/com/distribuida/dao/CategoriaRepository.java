package com.distribuida.dao;

import com.distribuida.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Metodos del CRUD: findAll(), findById(), save(), deleteById(), etc.
    // Estos metodos son proporcionados automaticamente por JpaRepository

    // Metodo para buscar categorias por su nombre
    // Spring Data JPA genera automaticamente la consulta SQL basada en el nombre del metodo
    // Retorna una lista porque pueden existir multiples categorias con el mismo nombre
    List<Categoria> findByCategoria(String categoria);
}

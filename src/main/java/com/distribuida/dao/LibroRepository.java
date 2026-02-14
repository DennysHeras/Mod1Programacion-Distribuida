package com.distribuida.dao;

import com.distribuida.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    // Metodos del CRUD: findAll(), findById(), save(), deleteById(), etc.
    // Spring Data JPA genera automaticamente las implementaciones.
}




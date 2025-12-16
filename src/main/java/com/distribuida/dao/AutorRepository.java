package com.distribuida.dao;

import com.distribuida.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {

    // Metodos del CRUD: findAll(), findById(), save(), deleteById(), etc.
    // Spring Data JPA genera automaticamente las implementaciones.

    Autor findByNombreAndApellido(String nombre, String apellido);

    Autor findByNacionalidad(String nacionalidad);
}

package com.distribuida.dao;

import com.distribuida.model.Autor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(true)
public class AutorTestIntegration {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void saveAndFindById() {
        Autor autor = new Autor(0, "Juan", "Perez",
                "Ecuador", "Autor de prueba");
        Autor saved = autorRepository.save(autor);

        Autor found = autorRepository.findById(saved.getIdAutor()).orElse(null);

        assertNotNull(found);
        assertEquals("Juan", found.getNombre());
        assertEquals("Perez", found.getApellido());
    }

    @Test
    public void findAll() {
        autorRepository.save(new Autor(0, "Ana", "Lopez",
                "Peru", "Descripcion 1"));
        autorRepository.save(new Autor(0, "Luis", "Gomez",
                "Colombia", "Descripcion 2"));

        List<Autor> autores = autorRepository.findAll();

        assertTrue(autores.size() >= 2);
        assertTrue(autores.stream().anyMatch(a -> "Ana".equals(a.getNombre())));
        assertTrue(autores.stream().anyMatch(a -> "Luis".equals(a.getNombre())));
    }

    @Test
    public void findByNombreAndApellido() {
        autorRepository.save(new Autor(0, "Maria", "Suarez",
                "Chile", "Descripcion"));

        Autor found = autorRepository.findByNombreAndApellido("Maria",
                "Suarez");

        assertNotNull(found);
        assertEquals("Chile", found.getNacionalidad());
    }

    @Test
    public void findByNacionalidad() {
        autorRepository.save(new Autor(0, "Pedro", "Diaz",
                "Argentina", "Descripcion"));

        Autor found = autorRepository.findByNacionalidad("Argentina");

        assertNotNull(found);
        assertEquals("Pedro", found.getNombre());
    }

    @Test
    public void deleteAutor() {
        Autor autor = autorRepository.save(new Autor(0, "Carlos", "Mora", "Mexico", "Temp"));
        Integer id = autor.getIdAutor();

        autorRepository.deleteById(id);

        assertFalse(autorRepository.findById(id).isPresent());
    }
}

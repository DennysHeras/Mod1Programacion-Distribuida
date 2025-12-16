package com.distribuida.dao;

import com.distribuida.model.Categoria;
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
public class CategoriaTestIntegration {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void saveAndFindById() {
        Categoria categoria = new Categoria(0, "Fantasia", "Libros de fantasia");
        Categoria saved = categoriaRepository.save(categoria);

        Categoria found = categoriaRepository.findById(saved.getIdCategoria()).orElse(null);

        if (found != null) {
            System.out.println(found.toString());
        }

        assertNotNull(found);
        assertEquals("Fantasia", found.getCategoria());
    }

    @Test
    public void findAll() {
        categoriaRepository.save(new Categoria(0,
                "Romance", "Libros romanticos"));
        categoriaRepository.save(new Categoria(0,
                "Aventura", "Libros de aventura"));

        List<Categoria> categorias = categoriaRepository.findAll();

        for (Categoria categoria : categorias) {
            System.out.println(categoria.toString());
        }

        assertTrue(categorias.size() >= 2);
        assertTrue(categorias.stream().anyMatch(c -> "Romance".equals(c.getCategoria())));
        assertTrue(categorias.stream().anyMatch(c -> "Aventura".equals(c.getCategoria())));
    }

    @Test
    public void findByCategoria() {
        categoriaRepository.save(new Categoria(0, "Historia", "Libros historicos"));

        List<Categoria> found = categoriaRepository.findByCategoria("Historia");

        for (Categoria categoria : found) {
            System.out.println(categoria.toString());
        }

        assertFalse(found.isEmpty());
        assertTrue(found.stream().anyMatch(c -> "Historia".equals(c.getCategoria())));
    }

    @Test
    public void deleteCategoria() {
        Categoria categoria = categoriaRepository.save(new Categoria(0,
                "Comedia", "Libros de comedia"));
        Integer id = categoria.getIdCategoria();

        System.out.println(categoria.toString());

        categoriaRepository.deleteById(id);

        System.out.println("Categoria eliminada: " + id);

        assertFalse(categoriaRepository.findById(id).isPresent());
    }
}

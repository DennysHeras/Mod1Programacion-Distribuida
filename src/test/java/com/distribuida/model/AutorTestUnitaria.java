package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutorTestUnitaria {

    private Autor autor;

    @BeforeEach
    public void setUp() {
        autor = new Autor(
                1,
                "Gabriel",
                "García Márquez",
                "Colombiana",
                "Autor de realismo mágico"
        );
    }

    @Test
    public void testAutorConstructor() {
        assertAll("Validar datos del Autor - constructor",
                () -> assertEquals(1, autor.getIdAutor()),
                () -> assertEquals("Gabriel", autor.getNombre()),
                () -> assertEquals("García Márquez", autor.getApellido()),
                () -> assertEquals("Colombiana", autor.getNacionalidad()),
                () -> assertEquals("Autor de realismo mágico", autor.getDescripcion())
        );
    }

    @Test
    public void testAutorSetters() {
        autor.setIdAutor(2);
        autor.setNombre("Mario");
        autor.setApellido("Vargas Llosa");
        autor.setNacionalidad("Peruana");
        autor.setDescripcion("Novelista peruano");

        assertAll("Validar datos del Autor - setters",
                () -> assertEquals(2, autor.getIdAutor()),
                () -> assertEquals("Mario", autor.getNombre()),
                () -> assertEquals("Vargas Llosa", autor.getApellido()),
                () -> assertEquals("Peruana", autor.getNacionalidad()),
                () -> assertEquals("Novelista peruano", autor.getDescripcion())
        );
    }

    @Test
    public void testAutorToString() {
        String str = autor.toString();

        assertAll("Validar datos del Autor - toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Gabriel")),
                () -> assertTrue(str.contains("García Márquez")),
                () -> assertTrue(str.contains("Colombiana")),
                () -> assertTrue(str.contains("Autor de realismo mágico"))
        );
    }
}
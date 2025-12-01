package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTestUnitaria {

    private Cliente cliente;
    @BeforeEach
    public void setUp(){
        cliente = new Cliente (1, "1235566732","Dennysh", "Oso", "Av. Siempre abierta", "0957236163823", "raulrevive@gmail.com");
    }

    @Test
    public void testClienteConstructor(){
        assertAll("Validar datos cliuente - contructor,",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("1235566732", cliente.getCedula()),
                () -> assertEquals("Dennysh", cliente.getNombre()),
                () -> assertEquals("Oso", cliente.getApellido()),
                () -> assertEquals("Av. Siempre abierta", cliente.getDireccion()),
                () -> assertEquals("0957236163823", cliente.getTelefono()),
                () -> assertEquals("raulrevive@gmail.com", cliente.getEmail())
                );
    }

    @Test
    public void testClienteSetters(){
        cliente.setIdCliente(2);
        cliente.setCedula("1235563243");
        cliente.setNombre("Denwin");
        cliente.setApellido("Aprecio");
        cliente.setDireccion("Av. Siempre ");
        cliente.setTelefono("1234567890");
        cliente.setEmail("raulrevive222@gmail.com");

        assertAll("VaLIDAR DATOS DEL lciente",
                () -> assertEquals(2,cliente.getIdCliente()),
                () -> assertEquals("1235563243", cliente.getCedula()),
                () -> assertEquals("Denwin", cliente.getNombre()),
                () -> assertEquals("Aprecio", cliente.getApellido()),
                () -> assertEquals("Av. Siempre ", cliente.getDireccion()),
                () -> assertEquals("1234567890", cliente.getTelefono()),
                () -> assertEquals("raulrevive222@gmail.com", cliente.getEmail())
        );
    }

    @Test
    public void testClienteToString() {
        String str = cliente.toString();
        assertAll("Validar datos del cliente - toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1235566732")),
                () -> assertTrue(str.contains("Dennysh")),
                () -> assertTrue(str.contains("Oso")),
                () -> assertTrue(str.contains("Av. Siempre abierta")),
                () -> assertTrue(str.contains("0957236163823")),
                () -> assertTrue(str.contains("raulrevive@gmail.com"))
        );
    }


}




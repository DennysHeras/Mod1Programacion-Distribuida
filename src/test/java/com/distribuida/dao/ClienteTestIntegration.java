package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ClienteTestIntegration {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente item : clientes) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void findByOne() {
        Cliente cliente = clienteRepository.findById(1).orElse(null);
        if (cliente != null) {
            System.out.println(cliente.toString());
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    @Test
    public void save() {
        Cliente cliente = new Cliente(
                39,
                "1701234567",
                "Juan66",
                "Taipe66",
                "Direccion66",
                "0987652666",
                "jtaipe66@correo.com"
        );
        clienteRepository.save(cliente);
        System.out.println(cliente.toString());
    }
    @Test
    public void update() {
        Optional<Cliente> clienteOpt = clienteRepository.findById(39);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setCedula("1701234777");
            cliente.setNombre("ElOso");
            cliente.setApellido("Osito");
            cliente.setDireccion("Dmotana");
            cliente.setTelefono("0923232323");
            cliente.setEmail("ositomonta@correo.com");

            clienteRepository.save(cliente);
            System.out.println(cliente.toString());
        } else {
            System.out.println("Cliente no encontrado");
        }
    }


}

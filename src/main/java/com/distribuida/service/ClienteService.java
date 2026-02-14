package com.distribuida.service;

import com.distribuida.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Integer id);
    Cliente save(Cliente cliente);
    Cliente update(Cliente cliente);
    void deleteById(Integer id);
}

package com.distribuida.service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        if (cliente.getIdCliente() == 0) return null;
        Optional<Cliente> opt = clienteRepository.findById(cliente.getIdCliente());
        if (opt.isEmpty()) return null;
        Cliente actual = opt.get();
        actual.setCedula(cliente.getCedula());
        actual.setNombre(cliente.getNombre());
        actual.setApellido(cliente.getApellido());
        actual.setDireccion(cliente.getDireccion());
        actual.setTelefono(cliente.getTelefono());
        actual.setEmail(cliente.getEmail());
        return clienteRepository.save(actual);
    }

    @Override
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }
}

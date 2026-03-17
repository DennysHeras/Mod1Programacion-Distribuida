package com.distribuida.dao;

import com.distribuida.model.Carrito;
import com.distribuida.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Optional<Carrito> findByCliente(Cliente cliente);

    Optional<Carrito> findByToken(String token);
}

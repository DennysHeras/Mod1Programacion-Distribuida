package com.distribuida.service;

import com.distribuida.dao.CategoriaRepository;
import com.distribuida.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Categoria categoria) {
        if (categoria.getIdCategoria() == 0) return null;
        Optional<Categoria> opt = categoriaRepository.findById(categoria.getIdCategoria());
        if (opt.isEmpty()) return null;
        Categoria actual = opt.get();
        actual.setCategoria(categoria.getCategoria());
        actual.setDescripcion(categoria.getDescripcion());
        return categoriaRepository.save(actual);
    }

    @Override
    public void deleteById(Integer id) {
        categoriaRepository.deleteById(id);
    }
}

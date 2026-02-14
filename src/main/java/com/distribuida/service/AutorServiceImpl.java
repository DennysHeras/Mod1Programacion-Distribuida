package com.distribuida.service;

import com.distribuida.dao.AutorRepository;
import com.distribuida.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public Optional<Autor> findById(Integer id) {
        return autorRepository.findById(id);
    }

    @Override
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor update(Autor autor) {
        if (autor.getIdAutor() == 0) return null;
        Optional<Autor> opt = autorRepository.findById(autor.getIdAutor());
        if (opt.isEmpty()) return null;
        Autor actual = opt.get();
        actual.setNombre(autor.getNombre());
        actual.setApellido(autor.getApellido());
        actual.setNacionalidad(autor.getNacionalidad());
        actual.setDescripcion(autor.getDescripcion());
        return autorRepository.save(actual);
    }

    @Override
    public void deleteById(Integer id) {
        autorRepository.deleteById(id);
    }
}

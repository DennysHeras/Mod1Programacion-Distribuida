package com.distribuida.service;

import com.distribuida.dao.AutorRepository;
import com.distribuida.dao.CategoriaRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> findById(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro save(Libro libro) {
        asignarReferencias(libro);
        return libroRepository.save(libro);
    }

    private void asignarReferencias(Libro libro) {
        if (libro.getAutor() != null && libro.getAutor().getIdAutor() != 0) {
            autorRepository.findById(libro.getAutor().getIdAutor()).ifPresent(libro::setAutor);
        }
        if (libro.getCategoria() != null && libro.getCategoria().getIdCategoria() != 0) {
            categoriaRepository.findById(libro.getCategoria().getIdCategoria()).ifPresent(libro::setCategoria);
        }
    }

    @Override
    public Libro update(Libro libro) {
        if (libro.getIdLibro() == 0) return null;
        Optional<Libro> opt = libroRepository.findById(libro.getIdLibro());
        if (opt.isEmpty()) return null;
        asignarReferencias(libro);
        Libro actual = opt.get();
        actual.setTitulo(libro.getTitulo());
        actual.setEditorial(libro.getEditorial());
        actual.setNumPaginas(libro.getNumPaginas());
        actual.setEdicion(libro.getEdicion());
        actual.setIdioma(libro.getIdioma());
        actual.setFechaPublicacion(libro.getFechaPublicacion());
        actual.setDescripcion(libro.getDescripcion());
        actual.setTipoPasta(libro.getTipoPasta());
        actual.setISBN(libro.getISBN());
        actual.setNumEjemplares(libro.getNumEjemplares());
        actual.setPortada(libro.getPortada());
        actual.setPresentacion(libro.getPresentacion());
        actual.setPrecio(libro.getPrecio());
        actual.setCategoria(libro.getCategoria());
        actual.setAutor(libro.getAutor());
        return libroRepository.save(actual);
    }

    @Override
    public void deleteById(Integer id) {
        libroRepository.deleteById(id);
    }
}

package com.distribuida.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private int idLibro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "num_paginas")
    private int numPaginas;

    @Column(name = "edicion")
    private String edicion;

    @Column(name = "idioma")
    private String idioma;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo_pasta")
    private String tipoPasta;

    @Column(name = "ISBN")
    @JsonProperty("isbn")
    private String ISBN;

    @Column(name = "num_ejemplares")
    private int numEjemplares;

    @Column(name = "portada")
    private String portada;

    @Column(name = "presentacion")
    private String presentacion;

    @Column(name = "precio")
    private float precio;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    public Libro() {
    }

    //constructor
    public Libro(int idLibro, String titulo, String editorial, int numPaginas, String edicion, String idioma, LocalDateTime fechaPublicacion, String descripcion, String tipoPasta, String ISBN, int numEjemplares, String portada, String presentacion, float precio, int idCategoria, int idAutor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.editorial = editorial;
        this.numPaginas = numPaginas;
        this.edicion = edicion;
        this.idioma = idioma;
        this.fechaPublicacion = fechaPublicacion;
        this.descripcion = descripcion;
        this.tipoPasta = tipoPasta;
        this.ISBN = ISBN;
        this.numEjemplares = numEjemplares;
        this.portada = portada;
        this.presentacion = presentacion;
        this.precio = precio;
        setIdCategoria(idCategoria);
        setIdAutor(idAutor);
    }

    //getters and setters
    public int getIdLibro() {
        return idLibro;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getEditorial() {
        return editorial;
    }
    public int getNumPaginas() {
        return numPaginas;
    }
    public String getEdicion() {
        return edicion;
    }
    public String getIdioma() {
        return idioma;
    }
    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getTipoPasta() {
        return tipoPasta;
    }
    public String getISBN() {
        return ISBN;
    }
    public int getNumEjemplares() {
        return numEjemplares;
    }
    public String getPortada() {
        return portada;
    }
    public String getPresentacion() {
        return presentacion;
    }
    public float getPrecio() {
        return precio;
    }
    public Integer getIdCategoria() {
        return categoria != null ? categoria.getIdCategoria() : null;
    }
    public Integer getIdAutor() {
        return autor != null ? autor.getIdAutor() : null;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setTipoPasta(String tipoPasta) {
        this.tipoPasta = tipoPasta;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public void setNumEjemplares(int numEjemplares) {
        this.numEjemplares = numEjemplares;
    }
    public void setPortada(String portada) {
        this.portada = portada;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void setIdCategoria(int idCategoria) {
        if (this.categoria == null) {
            this.categoria = new Categoria();
        }
        this.categoria.setIdCategoria(idCategoria);
    }
    public void setIdAutor(int idAutor) {
        if (this.autor == null) {
            this.autor = new Autor();
        }
        this.autor.setIdAutor(idAutor);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    @Override
    public String toString() {
        return "Libro{" +
                " idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", editorial='" + editorial + '\'' +
                ", numPaginas=" + numPaginas +
                ", edicion='" + edicion + '\'' +
                ", idioma='" + idioma + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", descripcion='" + descripcion + '\'' +
                ", tipoPasta='" + tipoPasta + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", numEjemplares=" + numEjemplares +
                ", portada='" + portada + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", precio=" + precio +
                ", idCategoria=" + getIdCategoria() +
                ", idAutor=" + getIdAutor() +
                '}';
    }
}


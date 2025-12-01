package com.distribuida.model;

import java.time.LocalDateTime;

public class Libro {

    private int idLibro;
    private String titulo;
    private String editorial;
    private int numPaginas;
    private String edicion;
    private String idioma;
    private LocalDateTime fechaPublicacion;
    private String descripcion;
    private String tipoPasta;
    private String ISBN;
    private int numEjemplares;
    private String portada;
    private String presentacion;
    private float precio;
    private int idCategoria;
    private int idAutor;

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
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
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
    public int getIdCategoria() {
        return idCategoria;
    }
    public int getIdAutor() {
        return idAutor;
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
        this.idCategoria = idCategoria;
    }
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
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
                ", idCategoria=" + idCategoria +
                ", idAutor=" + idAutor +
                '}';
    }
}


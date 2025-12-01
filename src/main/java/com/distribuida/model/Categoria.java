package com.distribuida.model;

public class Categoria {

    private int idCategoria;
    private String categoria;
    private String descripcion;

    //constructor
    public Categoria(int idCategoria, String categoria, String descripcion) {
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    //getters and setters
    public int getIdCategoria() {
        return idCategoria;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                " idCategoria=" + idCategoria +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}


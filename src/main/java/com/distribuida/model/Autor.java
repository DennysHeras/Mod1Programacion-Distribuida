package com.distribuida.model;

public class Autor {

    private int idAutor;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String descripcion;



    //constructor
    public Autor(int idAutor, String nombre, String apellido, String nacionalidad, String descripcion) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.descripcion = descripcion;
    }



    //getters
    public int getIdAutor() {
        return idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }




    //setters
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "Autor{" +
                " idAutor=" + idAutor +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}


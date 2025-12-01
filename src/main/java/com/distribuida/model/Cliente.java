package com.distribuida.model;

public class Cliente {

    private int idCliente;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;


    //constructor
    public Cliente(int idCliente, String cedula, String nombre, String apellido, String direccion, String telefono, String email) {
        this.idCliente = idCliente;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    //getters and setters
    public int getIdCliente() {
        return idCliente;
    }
    public String getCedula() {
        return cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                " idCliente=" + idCliente +
                ", cedula=" + cedula + '\'' +
                ", nombre=" + nombre + '\'' +
                ", apellido=" + apellido + '\'' +
                ", direccion=" + direccion + '\'' +
                ", telefono=" + telefono + '\'' +
                ", email=" + email + '\'' +
                '}';
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}

package com.distribuida.model;

public class FacturaDetalle {

    private int idFacturaDetalle;
    private int cantidad;
    private float subtotal;
    private int idFactura;
    private int idLibro;

    //constructor
    public FacturaDetalle(int idFacturaDetalle, int cantidad, float subtotal, int idFactura, int idLibro) {
        this.idFacturaDetalle = idFacturaDetalle;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.idFactura = idFactura;
        this.idLibro = idLibro;
    }

    //getters and setters
    public int getIdFacturaDetalle() {
        return idFacturaDetalle;
    }
    public int getCantidad() {
        return cantidad;
    }
    public float getSubtotal() {
        return subtotal;
    }
    public int getIdFactura() {
        return idFactura;
    }
    public int getIdLibro() {
        return idLibro;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                " idFacturaDetalle=" + idFacturaDetalle +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", idFactura=" + idFactura +
                ", idLibro=" + idLibro +
                '}';
    }
}


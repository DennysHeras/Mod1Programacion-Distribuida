package com.distribuida.model;

import java.time.LocalDateTime;

public class Factura {
    private int idFactura;
    private String numFactura;
    private LocalDateTime fecha;
    private float totalNeto;
    private float iva;
    private float total;
    private int idCliente;
    //constructor
    public Factura(int idFactura, String numFactura, LocalDateTime fecha, float totalNeto, float iva, float total, int idCliente) {
        this.idFactura = idFactura;
        this.numFactura = numFactura;
        this.fecha = fecha;
        this.totalNeto = totalNeto;
        this.iva = iva;
        this.total = total;
        this.idCliente = idCliente;
    }
    //getters and setters
    public int getIdFactura() {
        return idFactura;
    }
    public String getNumFactura() {
        return numFactura;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public float getTotalNeto() {
        return totalNeto;
    }
    public float getIva() {
        return iva;
    }
    public float getTotal() {
        return total;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public void setTotalNeto(float totalNeto) {
        this.totalNeto = totalNeto;
    }
    public void setIva(float iva) {
        this.iva = iva;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    @Override
    public String toString() {
        return "Factura{" +
                " idFactura=" + idFactura +
                ", numFactura='" + numFactura + '\'' +
                ", fecha=" + fecha +
                ", totalNeto=" + totalNeto +
                ", iva=" + iva +
                ", total=" + total +
                ", idCliente=" + idCliente +
                '}';
    }
}


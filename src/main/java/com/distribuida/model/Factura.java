package com.distribuida.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private int idFactura;

    @Column(name = "num_factura")
    private String numFactura;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "total_neto")
    private Double totalNeto;

    @Column(name = "iva")
    private Double iva;

    @Column(name = "total")
    private Double total;

    // inyección de dependencias
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Factura() {
    }

    //constructor
    public Factura(int idFactura, String numFactura, LocalDateTime fecha, float totalNeto, float iva, float total, int idCliente) {
        this.idFactura = idFactura;
        this.numFactura = numFactura;
        this.fecha = fecha;
        this.totalNeto = (double) totalNeto;
        this.iva = (double) iva;
        this.total = (double) total;
        setIdCliente(idCliente);
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
    public Double getTotalNeto() {
        return totalNeto;
    }
    public double getIva() {
        return iva;
    }
    public double getTotal() {
        return total;
    }
    public Integer getIdCliente() {
        return cliente != null ? cliente.getIdCliente() : null;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public void setTotalNeto(float totalNeto) {
        this.totalNeto = (double) totalNeto;
    }
    public void setIva(float iva) {
        this.iva = (double) iva;
    }
    public void setTotal(float total) {
        this.total = (double) total;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setIdCliente(int idCliente) {
        if (this.cliente == null) {
            this.cliente = new Cliente();
        }
        this.cliente.setIdCliente(idCliente);
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
                ", idCliente=" + getIdCliente() +
                '}';
    }
}


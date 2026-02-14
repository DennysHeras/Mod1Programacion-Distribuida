package com.distribuida.model;

import jakarta.persistence.*;

@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura_detalle")
    private int idFacturaDetalle;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal")
    private float subtotal;

    @ManyToOne
    @JoinColumn(name = "id_factura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    public FacturaDetalle() {
    }

    //constructor
    public FacturaDetalle(int idFacturaDetalle, int cantidad, float subtotal, int idFactura, int idLibro) {
        this.idFacturaDetalle = idFacturaDetalle;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
       
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
    public Integer getIdFactura() {
        return factura != null ? factura.getIdFactura() : null;
    }
    public Integer getIdLibro() {
        return libro != null ? libro.getIdLibro() : null;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    public void setIdFactura(int idFactura) {
        if (this.factura == null) {
            this.factura = new Factura();
        }
        this.factura.setIdFactura(idFactura);
    }
    public void setIdLibro(int idLibro) {
        // Este método se usa para compatibilidad pero Libro no tiene setIdLibro
        // Se debe usar setLibro() directamente con una instancia de Libro
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setIdFacturaDetalle(int idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                " idFacturaDetalle=" + idFacturaDetalle +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", idFactura=" + getIdFactura() +
                ", idLibro=" + getIdLibro() +
                '}';
    }
}


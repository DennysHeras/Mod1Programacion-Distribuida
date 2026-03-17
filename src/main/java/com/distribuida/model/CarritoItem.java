package com.distribuida.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "carrito_item")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito_item")
    private Long idCarritoItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrito")
    @JsonBackReference
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "total", precision = 12, scale = 2)
    private BigDecimal total;

    public CarritoItem() {
    }

    /**
     * Calcula el total del ítem: precioUnitario * cantidad.
     */
    public void calcularTotal() {
        this.total = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public Long getIdCarritoItem() { return idCarritoItem; }
    public void setIdCarritoItem(Long idCarritoItem) { this.idCarritoItem = idCarritoItem; }

    public Carrito getCarrito() { return carrito; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}

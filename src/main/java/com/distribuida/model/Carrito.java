package com.distribuida.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrito")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "subtotal", precision = 12, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(name = "descuento", precision = 12, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(name = "impuestos", precision = 12, scale = 2)
    private BigDecimal impuestos = BigDecimal.ZERO;

    @Column(name = "total", precision = 12, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CarritoItem> items = new ArrayList<>();

    public Carrito() {
    }

    @PrePersist
    @PreUpdate
    public void actualizarFecha() {
        this.actualizadoEn = LocalDateTime.now();
    }

    /**
     * Recalcula subtotal, impuestos y total a partir de los items actuales.
     */
    public void recomputarTotales(BigDecimal tasaIVA) {
        this.subtotal = items.stream()
                .map(CarritoItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        double iva = Math.max(0, subtotal.doubleValue()) * tasaIVA.doubleValue();
        this.impuestos = BigDecimal.valueOf(iva);
        this.total = subtotal.add(this.impuestos);
        this.actualizadoEn = LocalDateTime.now();
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public Long getIdCarrito() { return idCarrito; }
    public void setIdCarrito(Long idCarrito) { this.idCarrito = idCarrito; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getDescuento() { return descuento; }
    public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }

    public BigDecimal getImpuestos() { return impuestos; }
    public void setImpuestos(BigDecimal impuestos) { this.impuestos = impuestos; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public LocalDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(LocalDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }

    public List<CarritoItem> getItems() { return items; }
    public void setItems(List<CarritoItem> items) { this.items = items; }
}

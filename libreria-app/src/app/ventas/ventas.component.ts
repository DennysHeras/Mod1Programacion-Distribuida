import { Component, OnInit, OnDestroy, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { LibroService, Libro } from '../libro.service';
import { CarritoService, Carrito, Factura } from '../carrito.service';
import { CheckoutService } from '../checkout.service';

@Component({
  selector: 'app-ventas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ventas.component.html',
  changeDetection: ChangeDetectionStrategy.Default
})
export class VentasComponent implements OnInit, OnDestroy {

  // ─── Catálogo ────────────────────────────────────────────────────────────
  libros: Libro[] = [];
  busqueda: string = '';
  feedbackLibro: { [id: number]: string } = {};

  // ─── Carrito ─────────────────────────────────────────────────────────────
  carrito: Carrito | null = null;
  private sub: Subscription = new Subscription();

  // ─── Factura ─────────────────────────────────────────────────────────────
  facturaGenerada: Factura | null = null;
  procesando: boolean = false;
  errorMsg: string = '';

  constructor(
    private libroService: LibroService,
    private carritoService: CarritoService,
    private checkoutService: CheckoutService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    // Cargar catálogo de libros
    this.libroService.findAll().subscribe(libros => {
      this.libros = libros;
      this.cdr.detectChanges();
    });
    // Suscribirse al estado reactivo del carrito
    this.sub = this.carritoService.carrito$.subscribe(c => {
      this.carrito = c;
      this.cdr.detectChanges();
    });
    // Inicializar/cargar el carrito del token actual
    this.carritoService.inicializarCarrito();
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  // ─── Catálogo ─────────────────────────────────────────────────────────────
  get librosFiltrados(): Libro[] {
    const q = this.busqueda.toLowerCase().trim();
    if (!q) return this.libros;
    return this.libros.filter(l =>
      l.titulo?.toLowerCase().includes(q) ||
      (l as any).autor?.nombre?.toLowerCase().includes(q)
    );
  }

  agregarAlCarrito(libro: Libro): void {
    this.carritoService.addItem(libro.idLibro!, 1).subscribe({
      next: () => {
        this.feedbackLibro[libro.idLibro!] = '✓ Agregado';
        this.cdr.detectChanges();
        setTimeout(() => { delete this.feedbackLibro[libro.idLibro!]; this.cdr.detectChanges(); }, 1500);
      },
      error: () => {
        this.feedbackLibro[libro.idLibro!] = 'Error';
        this.cdr.detectChanges();
        setTimeout(() => { delete this.feedbackLibro[libro.idLibro!]; this.cdr.detectChanges(); }, 1500);
      }
    });
  }

  // ─── Carrito ──────────────────────────────────────────────────────────────
  incrementar(idLibro: number, cantidadActual: number): void {
    this.carritoService.updateItemCantidad(idLibro, cantidadActual + 1).subscribe();
  }

  decrementar(idLibro: number, cantidadActual: number): void {
    if (cantidadActual <= 1) {
      this.eliminarItem(idLibro);
    } else {
      this.carritoService.updateItemCantidad(idLibro, cantidadActual - 1).subscribe();
    }
  }

  eliminarItem(idLibro: number): void {
    this.carritoService.removeItem(idLibro).subscribe();
  }

  vaciarCarrito(): void {
    this.carritoService.clearCarrito().subscribe();
    this.facturaGenerada = null;
  }

  // ─── Checkout ─────────────────────────────────────────────────────────────
  checkout(): void {
    this.procesando = true;
    this.errorMsg = '';
    this.facturaGenerada = null;
    this.cdr.detectChanges();
    this.checkoutService.checkoutByToken(this.carritoService.getToken()).subscribe({
      next: (factura) => {
        this.facturaGenerada = factura;
        this.procesando = false;
        this.cdr.detectChanges();
        // Refrescar carrito (quedó vacío)
        this.carritoService.getCarrito().subscribe();
      },
      error: () => {
        this.errorMsg = 'Error al procesar el pago. Verifica el stock disponible.';
        this.procesando = false;
        this.cdr.detectChanges();
      }
    });
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Libro } from './libro.service';

export interface CarritoItem {
  idCarritoItem?: number;
  libro: Libro;
  cantidad: number;
  precioUnitario: number;
  total: number;
}

export interface Carrito {
  idCarrito?: number;
  token: string;
  subtotal: number;
  descuento: number;
  impuestos: number;
  total: number;
  actualizadoEn?: string;
  items: CarritoItem[];
}

export interface Factura {
  idFactura?: number;
  numFactura: string;
  fecha: string;
  totalNeto: number;
  iva: number;
  total: number;
}

@Injectable({ providedIn: 'root' })
export class CarritoService {
  private api = '/api/carrito';
  private carritoSubject = new BehaviorSubject<Carrito | null>(null);
  public carrito$ = this.carritoSubject.asObservable();

  constructor(private http: HttpClient) {}

  getToken(): string {
    let token = localStorage.getItem('carrito_token');
    if (!token) {
      token = 'TKN-' + Date.now() + '-' + Math.random().toString(36).substring(2, 9);
      localStorage.setItem('carrito_token', token);
    }
    return token;
  }

  inicializarCarrito(): void {
    this.getCarrito().subscribe();
  }

  getCarrito(): Observable<Carrito> {
    return this.http.get<Carrito>(`${this.api}/token/${this.getToken()}`).pipe(
      tap(c => this.carritoSubject.next(c))
    );
  }

  addItem(idLibro: number, cantidad: number): Observable<Carrito> {
    return this.http.post<Carrito>(`${this.api}/token/${this.getToken()}/items`,
      { idLibro, cantidad }).pipe(
      tap(c => this.carritoSubject.next(c))
    );
  }

  updateItemCantidad(idLibro: number, cantidad: number): Observable<Carrito> {
    return this.http.put<Carrito>(`${this.api}/token/${this.getToken()}/items/${idLibro}`,
      { cantidad }).pipe(
      tap(c => this.carritoSubject.next(c))
    );
  }

  removeItem(idLibro: number): Observable<Carrito> {
    return this.http.delete<Carrito>(`${this.api}/token/${this.getToken()}/items/${idLibro}`).pipe(
      tap(c => this.carritoSubject.next(c))
    );
  }

  clearCarrito(): Observable<Carrito> {
    return this.http.delete<Carrito>(`${this.api}/token/${this.getToken()}`).pipe(
      tap(c => this.carritoSubject.next(c))
    );
  }

  getCantidadTotal(): number {
    const carrito = this.carritoSubject.getValue();
    if (!carrito) return 0;
    return carrito.items.reduce((sum, item) => sum + item.cantidad, 0);
  }
}

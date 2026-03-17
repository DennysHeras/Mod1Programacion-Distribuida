import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Factura } from './carrito.service';

@Injectable({ providedIn: 'root' })
export class CheckoutService {
  private api = '/api/checkout';

  constructor(private http: HttpClient) {}

  checkoutByToken(token: string): Observable<Factura> {
    return this.http.post<Factura>(`${this.api}/token/${token}`, {});
  }
}

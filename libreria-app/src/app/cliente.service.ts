import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Cliente {
  idCliente?: number;
  cedula: string;
  nombre: string;
  apellido: string;
  direccion: string;
  telefono: string;
  correo: string;
}

@Injectable({ providedIn: 'root' })
export class ClienteService {
  private api = '/api/clientes';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.api}/findAll`);
  }

  save(cliente: Cliente): Observable<any> {
    return this.http.post(this.api, this.toPayload(cliente));
  }

  update(id: number, cliente: Cliente): Observable<any> {
    return this.http.put(`${this.api}/${id}`, this.toPayload(cliente));
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.api}/${id}`);
  }

  private toPayload(c: any): object {
    return {
      cedula: c.cedula ?? '',
      nombre: c.nombre ?? '',
      apellido: c.apellido ?? '',
      direccion: c.direccion ?? '',
      telefono: c.telefono ?? '',
      email: c.correo ?? c.email ?? ''
    };
  }
}

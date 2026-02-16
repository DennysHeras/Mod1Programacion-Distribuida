import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Autor {
  idAutor?: number;
  nombre: string;
  apellido: string;
  nacionalidad?: string;
  descripcion?: string;
}

@Injectable({ providedIn: 'root' })
export class AutorService {
  private api = '/api/autores';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Autor[]> {
    return this.http.get<Autor[]>(`${this.api}/findAll`);
  }

  save(autor: Autor): Observable<any> {
    return this.http.post(this.api, this.toPayload(autor));
  }

  update(id: number, autor: Autor): Observable<any> {
    return this.http.put(`${this.api}/${id}`, this.toPayload(autor));
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.api}/${id}`);
  }

  private toPayload(a: any): object {
    return {
      nombre: a.nombre ?? '',
      apellido: a.apellido ?? '',
      nacionalidad: a.nacionalidad ?? '',
      descripcion: a.descripcion ?? ''
    };
  }
}

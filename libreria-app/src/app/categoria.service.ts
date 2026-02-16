import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Categoria {
  idCategoria?: number;
  categoria: string;
  descripcion: string;
}

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  private api = '/api/categorias';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.api}/findAll`);
  }

  save(categoria: Categoria): Observable<any> {
    return this.http.post(this.api, this.toPayload(categoria));
  }

  update(id: number, categoria: Categoria): Observable<any> {
    return this.http.put(`${this.api}/${id}`, this.toPayload(categoria));
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.api}/${id}`);
  }

  private toPayload(c: any): object {
    return {
      categoria: c.categoria ?? '',
      descripcion: c.descripcion ?? ''
    };
  }
}

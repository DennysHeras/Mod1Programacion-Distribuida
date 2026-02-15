import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Libro {
  idLibro?: number;
  titulo: string;
  editorial: string;
  edicion: string;
  idioma: string;
  numPaginas: number;
  descripcion: string;
  tipoPasta: string;
  isbn: string;
  numEjemplares: number;
  precio: number;
  idAutor?: number;
  idCategoria?: number;
  fechaPublicacion?: string;
  portada?: string;
  presentacion?: string;
}

export interface Autor {
  idAutor: number;
  nombre?: string;
  apellido?: string;
}

export interface Categoria {
  idCategoria: number;
  categoria?: string;
  descripcion?: string;
}

@Injectable({ providedIn: 'root' })
export class LibroService {
  private api = '/api/libros';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Libro[]> {
    return this.http.get<Libro[]>(`${this.api}/findAll`);
  }

  getAutores(): Observable<Autor[]> {
    return this.http.get<Autor[]>('/api/autores/findAll');
  }

  getCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>('/api/categorias/findAll');
  }

  save(libro: Libro): Observable<any> {
    return this.http.post(`${this.api}`, this.toPayload(libro));
  }

  update(id: number, libro: Libro): Observable<any> {
    return this.http.put(`${this.api}/${id}`, this.toPayload(libro));
  }

  private toPayload(libro: any): object {
    return {
      titulo: libro.titulo ?? '',
      editorial: libro.editorial ?? '',
      numPaginas: Number(libro.numPaginas || 0),
      edicion: libro.edicion ?? '',
      idioma: libro.idioma ?? '',
      fechaPublicacion: (libro.fechaPublicacion && String(libro.fechaPublicacion).length >= 19)
        ? libro.fechaPublicacion
        : new Date().toISOString().slice(0, 19),
      descripcion: libro.descripcion ?? '',
      tipoPasta: libro.tipoPasta ?? '',
      isbn: libro.isbn || libro.ISBN || 'SIN-ISBN',
      numEjemplares: Number(libro.numEjemplares || 1),
      portada: libro.portada || 'sin_portada',
      presentacion: libro.presentacion || 'fisica',
      precio: Number(libro.precio || 0),
      idAutor: Number(libro.idAutor),
      idCategoria: Number(libro.idCategoria)
    };
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.api}/${id}`);
  }
}

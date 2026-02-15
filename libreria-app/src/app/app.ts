import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Autor, Categoria, Libro, LibroService } from './libro.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html'
})
export class AppComponent implements OnInit {
  libros: Libro[] = [];
  autores: Autor[] = [];
  categorias: Categoria[] = [];
  editando = false;
  idEditando: number | null = null;

  libro: Libro = {
    titulo: '',
    editorial: '',
    edicion: '',
    idioma: '',
    numPaginas: 0,
    descripcion: '',
    tipoPasta: '',
    isbn: '',
    numEjemplares: 0,
    precio: 0
  };

  constructor(private libroService: LibroService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargarLibros();
    this.libroService.getAutores().subscribe({
      next: (data) => (this.autores = Array.isArray(data) ? data : []),
      error: (err) => console.error(err)
    });
    this.libroService.getCategorias().subscribe({
      next: (data) => (this.categorias = Array.isArray(data) ? data : []),
      error: (err) => console.error(err)
    });
  }

  cargarLibros(): void {
    this.libroService.findAll().subscribe({
      next: (data) => {
        this.libros = Array.isArray(data) ? data : [];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  guardar(): void {
    if (!this.libro.idAutor || !this.libro.idCategoria) {
      alert('Seleccione Autor y Categoría');
      return;
    }
    if (this.editando && this.idEditando != null) {
      this.libroService.update(this.idEditando, this.libro).subscribe({
        next: () => { this.cancelar(); this.cargarLibros(); this.cdr.detectChanges(); alert('Libro actualizado'); },
        error: (err) => {
          const msg = err?.error?.message || err?.message || err?.statusText || JSON.stringify(err);
          alert('Error al actualizar: ' + msg);
        }
      });
    } else {
      this.libroService.save(this.libro).subscribe({
        next: () => { this.cancelar(); this.cargarLibros(); this.cdr.detectChanges(); alert('Libro guardado'); },
        error: (err) => {
          const msg = err?.error?.message || err?.message || err?.statusText || JSON.stringify(err);
          alert('Error al guardar: ' + msg);
        }
      });
    }
  }

  editar(l: Libro): void {
    this.editando = true;
    this.idEditando = l.idLibro!;
    const a = (l as any).autor;
    const c = (l as any).categoria;
    const lo = l as any;
    this.libro = {
      ...l,
      isbn: lo.isbn ?? lo.ISBN ?? '',
      idAutor: l.idAutor ?? a?.idAutor,
      idCategoria: l.idCategoria ?? c?.idCategoria,
      fechaPublicacion: lo.fechaPublicacion,
      portada: lo.portada,
      presentacion: lo.presentacion
    };
  }

  eliminar(id?: number): void {
    if (!id) return;
    if (!confirm('¿Eliminar libro?')) return;
    this.libroService.delete(id).subscribe(() => this.cargarLibros());
  }

  cancelar(): void {
    this.editando = false;
    this.idEditando = null;
    this.libro = {
      titulo: '',
      editorial: '',
      edicion: '',
      idioma: '',
      numPaginas: 0,
      descripcion: '',
      tipoPasta: '',
      isbn: '',
      numEjemplares: 0,
      precio: 0
    };
  }
}

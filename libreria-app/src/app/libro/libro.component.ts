import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Autor, Categoria, Libro, LibroService } from '../libro.service';

@Component({
  selector: 'app-libro',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './libro.component.html'
})
export class LibroComponent implements OnInit {
  libros: Libro[] = [];
  autores: Autor[] = [];
  categorias: Categoria[] = [];
  editando = false;
  idEditando: number | null = null;
  libro: Libro = {
    titulo: '', editorial: '', edicion: '', idioma: '', numPaginas: 0,
    descripcion: '', tipoPasta: '', isbn: '', numEjemplares: 0, precio: 0
  };

  constructor(private libroService: LibroService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargarLibros();
    this.libroService.getAutores().subscribe({ next: (d) => (this.autores = Array.isArray(d) ? d : []), error: (e) => console.error(e) });
    this.libroService.getCategorias().subscribe({ next: (d) => (this.categorias = Array.isArray(d) ? d : []), error: (e) => console.error(e) });
  }

  cargarLibros(): void {
    this.libroService.findAll().subscribe({
      next: (d) => { this.libros = Array.isArray(d) ? d : []; this.cdr.detectChanges(); },
      error: (e) => console.error(e)
    });
  }

  guardar(): void {
    if (!this.libro.idAutor || !this.libro.idCategoria) { alert('Seleccione Autor y Categoría'); return; }
    if (this.editando && this.idEditando != null) {
      this.libroService.update(this.idEditando, this.libro).subscribe({
        next: () => { this.cancelar(); this.cargarLibros(); this.cdr.detectChanges(); },
        error: (e) => alert('Error: ' + (e?.error?.message || e?.message))
      });
    } else {
      this.libroService.save(this.libro).subscribe({
        next: () => { this.cancelar(); this.cargarLibros(); this.cdr.detectChanges(); },
        error: (e) => alert('Error: ' + (e?.error?.message || e?.message))
      });
    }
  }

  editar(l: Libro): void {
    this.editando = true;
    this.idEditando = l.idLibro!;
    const lo = l as any;
    this.libro = { ...l, isbn: lo.isbn ?? lo.ISBN ?? '', idAutor: l.idAutor ?? lo.autor?.idAutor, idCategoria: l.idCategoria ?? lo.categoria?.idCategoria };
  }

  eliminar(id?: number): void {
    if (!id || !confirm('Eliminar libro?')) return;
    this.libroService.delete(id).subscribe(() => this.cargarLibros());
  }

  cancelar(): void {
    this.editando = false;
    this.idEditando = null;
    this.libro = { titulo: '', editorial: '', edicion: '', idioma: '', numPaginas: 0, descripcion: '', tipoPasta: '', isbn: '', numEjemplares: 0, precio: 0 };
  }
}

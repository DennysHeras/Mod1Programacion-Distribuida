import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Categoria, CategoriaService } from '../categoria.service';

@Component({
  selector: 'app-categoria',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './categoria.component.html'
})
export class CategoriaComponent implements OnInit {
  categorias: Categoria[] = [];
  editando = false;
  idEditando: number | null = null;
  categoria: Categoria = { categoria: '', descripcion: '' };

  constructor(private service: CategoriaService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.service.findAll().subscribe({
      next: (data) => {
        this.categorias = Array.isArray(data) ? data : [];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  guardar(): void {
    if (this.editando && this.idEditando != null) {
      this.service.update(this.idEditando, this.categoria).subscribe({
        next: () => { this.cancelar(); this.cargar(); this.cdr.detectChanges(); },
        error: (err) => alert('Error: ' + (err?.error?.message || err?.message))
      });
    } else {
      this.service.save(this.categoria).subscribe({
        next: () => { this.cancelar(); this.cargar(); this.cdr.detectChanges(); },
        error: (err) => alert('Error: ' + (err?.error?.message || err?.message))
      });
    }
  }

  editar(c: Categoria): void {
    this.editando = true;
    this.idEditando = c.idCategoria!;
    this.categoria = { ...c };
  }

  eliminar(id?: number): void {
    if (!id || !confirm('Eliminar categoría?')) return;
    this.service.delete(id).subscribe(() => this.cargar());
  }

  cancelar(): void {
    this.editando = false;
    this.idEditando = null;
    this.categoria = { categoria: '', descripcion: '' };
  }
}

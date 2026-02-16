import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Autor, AutorService } from '../autor.service';

@Component({
  selector: 'app-autor',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './autor.component.html'
})
export class AutorComponent implements OnInit {
  autores: Autor[] = [];
  editando = false;
  idEditando: number | null = null;
  autor: Autor = { nombre: '', apellido: '' };

  constructor(private service: AutorService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.service.findAll().subscribe({
      next: (data) => {
        this.autores = Array.isArray(data) ? data : [];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  guardar(): void {
    if (this.editando && this.idEditando != null) {
      this.service.update(this.idEditando, this.autor).subscribe({
        next: () => { this.cancelar(); this.cargar(); this.cdr.detectChanges(); },
        error: (err) => alert('Error: ' + (err?.error?.message || err?.message))
      });
    } else {
      this.service.save(this.autor).subscribe({
        next: () => { this.cancelar(); this.cargar(); this.cdr.detectChanges(); },
        error: (err) => alert('Error: ' + (err?.error?.message || err?.message))
      });
    }
  }

  editar(a: Autor): void {
    this.editando = true;
    this.idEditando = a.idAutor!;
    this.autor = { ...a };
  }

  eliminar(id?: number): void {
    if (!id || !confirm('Eliminar autor?')) return;
    this.service.delete(id).subscribe(() => this.cargar());
  }

  cancelar(): void {
    this.editando = false;
    this.idEditando = null;
    this.autor = { nombre: '', apellido: '' };
  }
}

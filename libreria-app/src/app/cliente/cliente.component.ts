import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cliente, ClienteService } from '../cliente.service';

@Component({
  selector: 'app-cliente',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cliente.component.html'
})
export class ClienteComponent implements OnInit {
  clientes: Cliente[] = [];
  editando = false;
  idEditando: number | null = null;
  cliente: Cliente = {
    cedula: '',
    nombre: '',
    apellido: '',
    direccion: '',
    telefono: '',
    correo: ''
  };

  constructor(private service: ClienteService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.service.findAll().subscribe({
      next: (data) => {
        this.clientes = Array.isArray(data) ? data : [];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  guardar(): void {
    if (this.editando && this.idEditando != null) {
      this.service.update(this.idEditando, this.cliente).subscribe({
        next: () => { this.cancelar(); this.cargar(); this.cdr.detectChanges(); },
        error: (err) => alert('Error: ' + (err?.error?.message || err?.message))
      });
    } else {
      this.service.save(this.cliente).subscribe({
        next: () => { this.cancelar(); this.cargar(); this.cdr.detectChanges(); },
        error: (err) => alert('Error: ' + (err?.error?.message || err?.message))
      });
    }
  }

  editar(c: Cliente): void {
    this.editando = true;
    this.idEditando = c.idCliente!;
    this.cliente = { ...c };
    if ((c as any).email) (this.cliente as any).correo = (c as any).email;
  }

  eliminar(id?: number): void {
    if (!id || !confirm('Eliminar cliente?')) return;
    this.service.delete(id).subscribe(() => this.cargar());
  }

  cancelar(): void {
    this.editando = false;
    this.idEditando = null;
    this.cliente = { cedula: '', nombre: '', apellido: '', direccion: '', telefono: '', correo: '' };
  }

  getCorreo(c: Cliente): string {
    return (c as any).correo || (c as any).email || '';
  }
}

import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <nav style="padding:12px 20px; background:#333; color:#fff; font-family:Arial;">
      <a routerLink="/libros" routerLinkActive="active" style="color:#fff; margin-right:16px; text-decoration:none;">Libros</a>
      <a routerLink="/clientes" routerLinkActive="active" style="color:#fff; margin-right:16px; text-decoration:none;">Clientes</a>
      <a routerLink="/autores" routerLinkActive="active" style="color:#fff; margin-right:16px; text-decoration:none;">Autores</a>
      <a routerLink="/categorias" routerLinkActive="active" style="color:#fff; text-decoration:none;">Categorías</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styles: [`.active { font-weight: bold; text-decoration: underline !important; }`]
})
export class AppComponent {}

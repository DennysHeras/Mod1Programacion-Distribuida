import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: '/libros', pathMatch: 'full' },
  { path: 'libros', loadComponent: () => import('./libro/libro.component').then(m => m.LibroComponent) },
  { path: 'clientes', loadComponent: () => import('./cliente/cliente.component').then(m => m.ClienteComponent) },
  { path: 'autores', loadComponent: () => import('./autor/autor.component').then(m => m.AutorComponent) },
  { path: 'categorias', loadComponent: () => import('./categoria/categoria.component').then(m => m.CategoriaComponent) }
];

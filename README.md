# Libreria - Backend + Frontend

## Estructura

- **Backend (Spring Boot)**: raíz del proyecto, puerto 8081
- **Frontend (Angular)**: carpeta `libreria-app/`, puerto 4200

## Cómo ejecutar

**1. Backend**
```bash
./gradlew bootRun
```

**2. Frontend**
```bash
cd libreria-app
npm install
npm start
```

El frontend usa proxy para redirigir `/api` al backend en localhost:8081.

**3. Abrir la aplicacion**
- http://localhost:4200

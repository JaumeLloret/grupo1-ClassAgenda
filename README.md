# Grupo 1 - ClassAgenda

Proyecto Intermodular de **1º DAM**  
Aplicación web de agenda colaborativa desarrollada sin frameworks.

---

## 👥 Equipo

- Alumno/a 1: Roberto 
- Alumno/a 2: Antonio
- Alumno/a 3: Iván
- Alumno/a 4: Santi
- Alumno/a 5: Neiva
- Alumno/a 6: Paula

---

## 🎯 Descripción del proyecto

ClassAgenda es una aplicación web que permite a los usuarios gestionar **tareas** y **eventos**, asociarlos a un usuario propietario y **compartirlos con otros usuarios** con distintos permisos (READ / EDIT).

El proyecto integra contenidos de Programación, Bases de Datos, Sistemas Informáticos, Entornos de Desarrollo y Lenguajes de Marcas.

---

## ⚙️ Tecnologías utilizadas

### Backend
- Java puro
- HttpServer
- JDBC
- Arquitectura limpia
- Principios SOLID

### Base de datos
- SQL Server

### Cliente
- HTML5
- CSS3
- JavaScript (fetch + JSON)

### Infraestructura
- Máquina virtual Windows
- Git y GitHub

---

## 📈 Metodología y Normas de Trabajo

El proyecto se desarrolla siguiendo **Extreme Programming (XP)**:

- Trabajo en iteraciones
- Pair programming
- Commits pequeños y frecuentes
- Uso de Issues, Projects y Pull Requests en GitHub
- Refactorización continua

### Estilo de Commits
Utilizaremos el estándar de **Conventional Commits** para mantener un historial claro:
- **Formato:** `<tipo>: <descripción corta en minúsculas>`
- **Tipos comunes:**
    - `feat`: Nueva funcionalidad.
    - `fix`: Corrección de un error.
    - `refactor`: Cambio de código que no añade funciones ni corrige errores.
    - `docs`: Cambios en la documentación.
    - `style`: Formateo de código (espacios, puntos y coma, etc.).

### Flujo de Pull Requests (PR)
1. **Ramas:** No se permite subir código directamente a `main`. Se debe crear una rama descriptiva (ej. `feature/nombre-tarea`).
2. **Vinculación:** Cada PR debe indicar qué tarea resuelve usando `Closes #ID_ISSUE`.
3. **Revisión:** Antes del merge, el código debe ser revisado por un miembro del equipo que no haya participado en ese desarrollo.

---

## 🚫 Restricciones

- No se utilizan frameworks
- No hay sistema de autenticación
- El usuario activo se indica mediante la cabecera HTTP:

```
X-User-Id: <id_del_usuario>
```

---

## 🧱 Arquitectura del proyecto

> Describir aquí la estructura de carpetas y la arquitectura utilizada.

Ejemplo:
```
/api
  /presentation
  /application
  /domain
  /infrastructure
/client
/database
/docs
```

---

## 🗄️ Base de datos

- Motor: SQL Server
- Tablas principales:
  - USERS
  - TASKS
  - EVENTS
  - TASK_SHARES
  - EVENT_SHARES

📌 **Pendiente**:  
- Esquema relacional  
- Diagrama E-R  
- Scripts SQL  

---

## 🌐 API REST

📌 **Pendiente**:  
- Listado de endpoints
- Ejemplos de peticiones y respuestas
- Contratos JSON

---

## 🖥️ Cliente web

📌 **Pendiente**:  
- Descripción de las vistas
- Flujo de navegación
- Capturas de pantalla

---

## 🖥️ Máquina virtual (Servidor)

📌 **Pendiente**:  
- Configuración de la VM
- Instalación de SQL Server
- Puesta en marcha de la API
- Evidencias (capturas)

---

## 🧪 Pruebas

📌 **Pendiente**:  
- Casos de prueba manuales
- Evidencias de funcionamiento

---

## 📈 Metodología de trabajo

El proyecto se desarrolla siguiendo **Extreme Programming (XP)**:

- Trabajo en iteraciones
- Pair programming
- Commits pequeños y frecuentes
- Uso de Issues, Projects y Pull Requests en GitHub
- Refactorización continua

---

## 🚀 Estado del proyecto

- [ ] Diseño inicial
- [ ] Base de datos
- [ ] API REST
- [ ] Cliente web
- [ ] Integración
- [ ] Despliegue en VM
- [ ] Documentación final

---

## 📌 Notas finales

Este README debe actualizarse durante todo el desarrollo del proyecto.  
La calidad de la documentación forma parte de la evaluación.

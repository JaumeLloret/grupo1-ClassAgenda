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
  
- Esquema relacional
    La base de datos se ha diseñado siguiendo un modelo relacional en Tercera Forma Normal (3FN).
    
    Relaciones principales:
    
    USERS (1) —— (N) TASKS
    USERS (1) —— (N) EVENTS
    TASKS (N) —— (M) USERS mediante TASK_SHARES
    EVENTS (N) —— (M) USERS mediante EVENT_SHARES
    
    Las relaciones N:M se han resuelto mediante tablas intermedias con clave primaria compuesta:
    
    TASK_SHARES (task_id, shared_with_user_id)
    EVENT_SHARES (event_id, shared_with_user_id)
    
    Se han definido claves foráneas con ON DELETE CASCADE para mantener la integridad referencial. 
  
- Scripts SQL
    Se incluyen los siguientes scripts en la carpeta /database:
    
    01_schema.sql
    Contiene la creación de la base de datos, tablas, claves primarias, claves foráneas y restricciones.
    
    02_seed.sql
    Inserta datos de prueba para verificar el funcionamiento del modelo y las relaciones.

  
📌 **Pendiente**:  
- Diagrama E-R  
 
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

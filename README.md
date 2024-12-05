# Literalura 📚

## Descripción del Proyecto

Literalura es una aplicación de gestión de biblioteca desarrollada en Java con Spring Boot que permite buscar, registrar y consultar libros utilizando la API de Gutendex. La aplicación ofrece funcionalidades para interactuar con una base de datos de libros y autores.

## Características Principales

- 🔍 Búsqueda de libros por título
- 📖 Registro de libros en la base de datos
- 📚 Listado de libros por idioma
- 👥 Consulta de autores
- 📅 Búsqueda de autores vivos en un año específico

## Tecnologías Utilizadas

- Java 23
- Spring Boot
- Spring Data JPA
- Hibernate
- RestTemplate
- Jackson (para procesamiento JSON)
- Base de datos (configurada en application.properties)

## Estructura del Proyecto

- `modelo/`: Clases de entidad (Libro, Autor)
- `repository/`: Interfaces de repositorio JPA
- `service/`: Servicios de lógica de negocio
  - `BibliotecaService`: Servicio principal
  - `GutendexService`: Servicio de integración con API Gutendex

## Funcionalidades Detalladas

### Buscar y Registrar Libro
- Busca un libro por título en la API Gutendex
- Verifica si el libro ya existe en la base de datos
- Registra el libro con su autor si no existe

### Listar Libros
- Obtener todos los libros
- Filtrar libros por idioma

### Listar Autores
- Obtener todos los autores
- Buscar autores vivos en un año específico

## Configuración

1. Clonar el repositorio
2. Configurar las dependencias en el `pom.xml`
3. Configurar la conexión de base de datos en `application.properties`
4. Ejecutar la aplicación

## Ejemplo de Uso

```java
// Buscar y registrar un libro
Libro libro = bibliotecaService.buscarYRegistrarLibro("Don Quijote");

// Listar libros en español
List<Libro> librosEspañol = bibliotecaService.listarLibrosPorIdioma("es");

// Listar autores vivos en 1850
List<Autor> autoresVivos = bibliotecaService.listarAutoresVivosPorAño(1850);

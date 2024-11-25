// src/main/java/com/alura/literalura/LiteraluraApplication.java
package com.alura.literalura;

import com.alura.literalura.modelo.Autor;
import com.alura.literalura.modelo.Libro;
import com.alura.literalura.service.BibliotecaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	private final BibliotecaService bibliotecaService;
	private final Scanner scanner = new Scanner(System.in);

	public LiteraluraApplication(BibliotecaService bibliotecaService) {
		this.bibliotecaService = bibliotecaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		while (true) {
			System.out.println("---------------------------------------");
			System.out.println("Elige la opcion a través de su numero:");
			System.out.println("1 - buscar libro por titulo");
			System.out.println("2 - listar libros registrados");
			System.out.println("3 - listar autores registrados");
			System.out.println("4 - listar autores vivos en un determinado año");
			System.out.println("5 - listar libros por idioma");
			System.out.println("6 - salir");

			int opcion = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			try {
				switch (opcion) {
					case 1:
						buscarLibro();
						break;
					case 2:
						listarLibros();
						break;
					case 3:
						listarAutores();
						break;
					case 4:
						listarAutoresVivos();
						break;
					case 5:
						listarLibrosPorIdioma();
						break;
					case 6:
						System.out.println("¡Hasta luego!");
						return;
					default:
						System.out.println("Opción no válida");
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void buscarLibro() {
		System.out.println("Ingrese el nombre del libro a buscar");
		String titulo = scanner.nextLine();
		try {
			Libro libro = bibliotecaService.buscarYRegistrarLibro(titulo);
			mostrarLibro(libro);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarLibros() {
		List<Libro> libros = bibliotecaService.listarLibros();
		libros.forEach(this::mostrarLibro);
	}

	private void listarAutores() {
		List<Autor> autores = bibliotecaService.listarAutores();
		autores.forEach(this::mostrarAutor);
	}

	private void listarAutoresVivos() {
		System.out.println("Ingrese el año:");
		int año = scanner.nextInt();
		List<Autor> autores = bibliotecaService.listarAutoresVivosPorAño(año);
		autores.forEach(this::mostrarAutor);
	}

	private void listarLibrosPorIdioma() {
		System.out.println("Ingrese el idioma para buscar los libros:");
		System.out.println("es- español");
		System.out.println("en- inglés");
		System.out.println("fr- francés");
		System.out.println("pt- portugués");
		String idioma = scanner.nextLine();
		List<Libro> libros = bibliotecaService.listarLibrosPorIdioma(idioma);
		libros.forEach(this::mostrarLibro);
	}

	private void mostrarLibro(Libro libro) {
		System.out.println("---------- LIBRO ----------");
		System.out.println("Titulo: " + libro.getTitulo());
		System.out.println("Autor: " + libro.getAutor().getNombre());
		System.out.println("Idioma: " + libro.getIdioma());
		System.out.println("Numero de descargas: " + libro.getNumeroDeDescargas());
		System.out.println("---------------------------");
	}

	private void mostrarAutor(Autor autor) {
		System.out.println("Autor: " + autor.getNombre());
		System.out.println("Fecha de nacimiento: " + autor.getFechaDeNacimiento());
		System.out.println("Fecha de fallecimiento: " + autor.getFechaDeFallecimiento());
		System.out.println("Libros: " + autor.getLibros());
	}
}
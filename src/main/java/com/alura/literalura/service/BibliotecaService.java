// src/main/java/com/alura/literalura/service/BibliotecaService.java
package com.alura.literalura.service;

import com.alura.literalura.modelo.Autor;
import com.alura.literalura.modelo.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {
    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;
    private final GutendexService gutendexService;

    public BibliotecaService(AutorRepository autorRepository, LibroRepository libroRepository, GutendexService gutendexService) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
        this.gutendexService = gutendexService;
    }

    public Libro buscarYRegistrarLibro(String titulo) {
        Optional<Libro> libroExistente = libroRepository.findByTitulo(titulo);
        if (libroExistente.isPresent()) {
            throw new RuntimeException("No se puede registrar el libro mas de una vez");
        }

        JsonNode libroInfo = gutendexService.buscarLibro(titulo);
        if (libroInfo == null) {
            throw new RuntimeException("Libro no encontrado");
        }

        String nombreAutor = libroInfo.get("authors").get(0).get("name").asText();
        Integer birthYear = libroInfo.get("authors").get(0).get("birth_year").asInt();
        Integer deathYear = libroInfo.get("authors").get(0).get("death_year").asInt();

        Autor autor = autorRepository.findByNombre(nombreAutor)
                .orElseGet(() -> autorRepository.save(new Autor(
                        nombreAutor,
                        LocalDate.of(birthYear, 1, 1),
                        deathYear != null ? LocalDate.of(deathYear, 12, 31) : null
                )));

        Libro libro = new Libro(
                libroInfo.get("title").asText(),
                libroInfo.get("languages").get(0).asText(),
                libroInfo.get("download_count").asInt(),
                autor
        );

        return libroRepository.save(libro);
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosPorAño(int año) {
        return autorRepository.findAutoresVivosPorAño(LocalDate.of(año, 1, 1));
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}
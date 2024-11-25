// src/main/java/com/alura/literalura/modelo/Libro.java
package com.alura.literalura.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer numeroDeDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}

    public Libro(String titulo, String idioma, Integer numeroDeDescargas, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDeDescargas = numeroDeDescargas;
        this.autor = autor;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public Integer getNumeroDeDescargas() { return numeroDeDescargas; }
    public void setNumeroDeDescargas(Integer numeroDeDescargas) { this.numeroDeDescargas = numeroDeDescargas; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
}
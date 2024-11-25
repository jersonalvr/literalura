// src/main/java/com/alura/literalura/modelo/Autor.java
package com.alura.literalura.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor(String nombre, LocalDate fechaDeNacimiento, LocalDate fechaDeFallecimiento) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public LocalDate getFechaDeNacimiento() { return fechaDeNacimiento; }
    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }
    public LocalDate getFechaDeFallecimiento() { return fechaDeFallecimiento; }
    public void setFechaDeFallecimiento(LocalDate fechaDeFallecimiento) { this.fechaDeFallecimiento = fechaDeFallecimiento; }
    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}
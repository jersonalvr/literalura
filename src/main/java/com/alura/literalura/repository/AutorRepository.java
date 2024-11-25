// src/main/java/com/alura/literalura/repository/AutorRepository.java
package com.alura.literalura.repository;

import com.alura.literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :año AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :año)")
    List<Autor> findAutoresVivosPorAño(LocalDate año);
}
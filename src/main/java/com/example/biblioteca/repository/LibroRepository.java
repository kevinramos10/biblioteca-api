package com.example.biblioteca.repository;

import com.example.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByAutor_NombreContainingIgnoreCase(String nombre);

    List<Libro> findByTituloContainingIgnoreCaseOrAutor_NombreContainingIgnoreCase(String titulo, String autor);


}

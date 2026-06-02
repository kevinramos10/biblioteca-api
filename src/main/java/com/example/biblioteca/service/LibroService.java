package com.example.biblioteca.service;

import com.example.biblioteca.dto.LibroInputDTO;
import com.example.biblioteca.dto.LibroOutputDTO;
import com.example.biblioteca.model.Libro;

import java.util.List;

public interface LibroService {

    List<LibroOutputDTO> obtenerTodosLibros();

    LibroOutputDTO crearLibro(LibroInputDTO libroInputDTO);

    LibroOutputDTO actualizarLibro(int id, LibroInputDTO libroInputDTO);

    LibroOutputDTO eliminarLibro(int id);

    //------

    List<LibroOutputDTO> buscarPorTitulo(String titulo);

    List<LibroOutputDTO> buscarPorNombre(String nombre);

    List<LibroOutputDTO> buscarPorNombreoTitulo(String titulo, String autor);

}

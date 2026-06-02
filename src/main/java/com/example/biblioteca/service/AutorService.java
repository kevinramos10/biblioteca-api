package com.example.biblioteca.service;

import com.example.biblioteca.dto.AutorInputDTO;
import com.example.biblioteca.dto.AutorOutputDTO;
import com.example.biblioteca.model.Autor;

import java.util.List;

public interface AutorService {

    List<AutorOutputDTO> obtenerTodosAutores();

    AutorOutputDTO crearAutor(AutorInputDTO autorInputDTO);

    AutorOutputDTO actualizarActor(int id, AutorInputDTO autorInputDTO);

    AutorOutputDTO eliminarAutor(int id);

}

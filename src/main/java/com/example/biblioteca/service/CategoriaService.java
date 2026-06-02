package com.example.biblioteca.service;

import com.example.biblioteca.dto.*;

import java.util.List;

public interface CategoriaService {

    List<CategoriaOutputDTO> obtenerTodosCategorias();

    CategoriaOutputDTO crearCategoria(CategoriaInputDTO categoriaInputDTO);

    CategoriaOutputDTO actualizarCategoria(int id, CategoriaInputDTO categoriaInputDTO);

    CategoriaOutputDTO eliminarCategoria(int id);


}

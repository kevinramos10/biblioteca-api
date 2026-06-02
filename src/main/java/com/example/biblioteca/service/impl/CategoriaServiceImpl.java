package com.example.biblioteca.service.impl;

import com.example.biblioteca.dto.CategoriaInputDTO;
import com.example.biblioteca.dto.CategoriaOutputDTO;
import com.example.biblioteca.exception.ConflictoException;
import com.example.biblioteca.exception.RecursoNoEncontradoException;
import com.example.biblioteca.model.Categoria;
import com.example.biblioteca.repository.CategoriaRepository;
import com.example.biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaOutputDTO> obtenerTodosCategorias(){

        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream()
                .map(a -> new CategoriaOutputDTO(
                        a.getId(),
                        a.getNombre()
                )).collect(Collectors.toList());
    }

    @Override
    public CategoriaOutputDTO crearCategoria(CategoriaInputDTO categoriaInputDTO){

        //Verificacion si existe la categoria con un mismo nombre
        boolean existe = categoriaRepository.existsByNombre(categoriaInputDTO.getNombre());

        if(existe){
            throw new ConflictoException(
              "La categoria " + categoriaInputDTO.getNombre() + " ya existe"
            );
        }

        Categoria categoria = new Categoria();

        categoria.setNombre(categoriaInputDTO.getNombre());

        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        return new CategoriaOutputDTO(
                categoriaGuardada.getId(),
                categoriaGuardada.getNombre()
        );

    }

    @Override
    public CategoriaOutputDTO actualizarCategoria(int id, CategoriaInputDTO dto){

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrada con id: " + id));

        //Verificacion si existe la categoria con un mismo nombre
        boolean existe = categoriaRepository.existsByNombre(dto.getNombre());

        if(existe){
            throw new ConflictoException(
                    "La categoria " + dto.getNombre() + " ya existe"
            );
        }

        categoria.setNombre(dto.getNombre());

        Categoria categoriaActualizada = categoriaRepository.save(categoria);

        return new CategoriaOutputDTO(
                categoriaActualizada.getId(),
                categoriaActualizada.getNombre()
        );


    }

    @Override
    public CategoriaOutputDTO eliminarCategoria(int id){

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrada con id:" + id));

        categoriaRepository.delete(categoria);

        return new CategoriaOutputDTO(
                categoria.getId(),
                categoria.getNombre()
        );

    }



}

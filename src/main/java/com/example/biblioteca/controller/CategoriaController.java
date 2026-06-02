package com.example.biblioteca.controller;

import com.example.biblioteca.dto.CategoriaInputDTO;
import com.example.biblioteca.dto.CategoriaOutputDTO;
import com.example.biblioteca.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaOutputDTO> listarCategorias(){
        return categoriaService.obtenerTodosCategorias();
    }

    @PostMapping
    public CategoriaOutputDTO crearCategoria(@Valid @RequestBody CategoriaInputDTO categoriaInputDTO){
        return categoriaService.crearCategoria(categoriaInputDTO);
    }

    @PutMapping("/{id}")
    public  CategoriaOutputDTO actualizarCategoria(@Valid @PathVariable int id, @RequestBody CategoriaInputDTO categoriaInputDTO){
        return categoriaService.actualizarCategoria(id, categoriaInputDTO);
    }

    @DeleteMapping("/{id}")
    public CategoriaOutputDTO eliminarCategoria(@PathVariable int id){
        return categoriaService.eliminarCategoria(id);
    }



}

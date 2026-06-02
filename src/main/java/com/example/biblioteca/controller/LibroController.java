package com.example.biblioteca.controller;

import com.example.biblioteca.dto.CategoriaInputDTO;
import com.example.biblioteca.dto.CategoriaOutputDTO;
import com.example.biblioteca.dto.LibroInputDTO;
import com.example.biblioteca.dto.LibroOutputDTO;
import com.example.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<LibroOutputDTO> listarLibros(){
        return libroService.obtenerTodosLibros();
    }

    @PostMapping
    public LibroOutputDTO crearLibro(@RequestBody LibroInputDTO libroInputDTO){
        return libroService.crearLibro(libroInputDTO);
    }

    @PutMapping("/{id}")
    public LibroOutputDTO actualizarLibro(@PathVariable int id, @RequestBody LibroInputDTO libroInputDTO){
        return libroService.actualizarLibro(id, libroInputDTO);
    }

    @DeleteMapping("/{id}")
    public LibroOutputDTO eliminarLibro(@PathVariable int id){
        return libroService.eliminarLibro(id);
    }

    @GetMapping("/search")
    public List<LibroOutputDTO> buscarPorTitulo(@RequestParam String titulo){
        return libroService.buscarPorTitulo(titulo);
    }

    @GetMapping("/search/nom")
    public List<LibroOutputDTO> buscarPorNombre(@RequestParam String nombre){
        return libroService.buscarPorNombre(nombre);
    }

    @GetMapping("/search/nomaut")
    public List<LibroOutputDTO> buscarPorNombreoTitulo(@RequestParam String titulo, @RequestParam String autor ){
        return libroService.buscarPorNombreoTitulo(titulo, autor);
    }

}

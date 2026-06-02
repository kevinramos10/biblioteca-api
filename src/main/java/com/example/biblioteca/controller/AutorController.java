package com.example.biblioteca.controller;


import com.example.biblioteca.dto.AutorInputDTO;
import com.example.biblioteca.dto.AutorOutputDTO;
import com.example.biblioteca.exception.PeticionInvalidaException;
import com.example.biblioteca.service.AutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<AutorOutputDTO> listarAutores(){
        return autorService.obtenerTodosAutores();
    }

    @PostMapping
    public AutorOutputDTO crearAutor(@RequestBody AutorInputDTO autorInputDTO){
        return autorService.crearAutor(autorInputDTO);
    }

    @PutMapping("/{id}")
    public AutorOutputDTO actualizarAutor(@PathVariable int id, @RequestBody AutorInputDTO autorInputDTO){
        return autorService.actualizarActor(id, autorInputDTO);
    }

    @DeleteMapping("/{id}")
    public AutorOutputDTO eliminarAutor(@PathVariable String id){

        int idInt;

        try{
            idInt = Integer.parseInt(id);
        } catch (NumberFormatException e){
            throw new PeticionInvalidaException("El ID enviado no es valido");
        }

        return autorService.eliminarAutor(idInt);
    }



}

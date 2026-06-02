package com.example.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaInputDTO {

    @NotBlank(message = "El nombre de la categoria no puede ser vacio!")
    private String nombre;

    // Constructor vacío
    public CategoriaInputDTO() {}

    // Constructor con parámetros
    public CategoriaInputDTO(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
package com.example.biblioteca.dto;

import java.util.List;

public class LibroInputDTO {

    private String titulo;
    private int anioPublicacion;
    private int autorId;                 // id del autor relacionado
    private List<Integer> categoriaIds;  // ids de las categorías relacionadas

    // Constructor vacío
    public LibroInputDTO() {}

    // Constructor con parámetros
    public LibroInputDTO(String titulo, int anioPublicacion, int autorId, List<Integer> categoriaIds) {
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.autorId = autorId;
        this.categoriaIds = categoriaIds;
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public List<Integer> getCategoriaIds() {
        return categoriaIds;
    }

    public void setCategoriaIds(List<Integer> categoriaIds) {
        this.categoriaIds = categoriaIds;
    }
}
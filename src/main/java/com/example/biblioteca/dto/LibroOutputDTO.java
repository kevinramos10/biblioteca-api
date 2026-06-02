package com.example.biblioteca.dto;

import java.util.List;

public class LibroOutputDTO {

    private int id;
    private String titulo;
    private int anioPublicacion;
    private String nombreAutor;
    private int autorId; //Poder enviar el id del autor
    private List<String> categorias; // solo nombres de categorías
    private List<Integer> categoriaIds; //Para enviar Ids de las categorias

    // Constructor vacío
    public LibroOutputDTO() {}

    public LibroOutputDTO(int id, String titulo, int anioPublicacion, String nombreAutor, int autorId, List<String> categorias, List<Integer> categoriaIds) {
        this.id = id;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.nombreAutor = nombreAutor;
        this.autorId = autorId;
        this.categorias = categorias;
        this.categoriaIds = categoriaIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<Integer> getCategoriaIds() {
        return categoriaIds;
    }

    public void setCategoriaIds(List<Integer> categoriaIds) {
        this.categoriaIds = categoriaIds;
    }
}
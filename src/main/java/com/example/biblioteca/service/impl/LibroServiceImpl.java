package com.example.biblioteca.service.impl;

import com.example.biblioteca.dto.LibroInputDTO;
import com.example.biblioteca.dto.LibroOutputDTO;
import com.example.biblioteca.exception.RecursoNoEncontradoException;
import com.example.biblioteca.model.Autor;
import com.example.biblioteca.model.Categoria;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.repository.CategoriaRepository;
import com.example.biblioteca.repository.LibroRepository;
import com.example.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<LibroOutputDTO> obtenerTodosLibros(){

        List<Libro> libros = libroRepository.findAll();

        return libros.stream()
                .map(a -> new LibroOutputDTO(
                        a.getId(),
                        a.getTitulo(),
                        a.getAnioPublicacion(),      // coincide con el getter
                        a.getAutor().getNombre(),
                        a.getAutor().getId(),
                        a.getCategorias().stream()
                                .map(categoria -> categoria.getNombre())
                                .toList(),
                        a.getCategorias().stream()
                                .map(categoria -> categoria.getId())
                                .toList()
                ))
                .collect(Collectors.toList());

    }

    @Override
    public LibroOutputDTO crearLibro(LibroInputDTO dto){

        Libro libro = new Libro();

        libro.setTitulo(dto.getTitulo());
        libro.setAnioPublicacion(dto.getAnioPublicacion());

        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Autor no encontrado"));

        libro.setAutor(autor);

        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriaIds());

        if(categorias.size() != dto.getCategoriaIds().size()){
            throw new RecursoNoEncontradoException("Una o más categorías no existen");
        }

        libro.setCategorias(categorias);

        Libro libroGuardado = libroRepository.save(libro);

        List<String> categoriasDTO = libroGuardado.getCategorias()
                .stream()
                .map(Categoria::getNombre)
                .toList();

        List<Integer> categoriasIdsDTO = libroGuardado.getCategorias()
                .stream()
                .map(Categoria::getId)
                .toList();

        return new LibroOutputDTO(
                libroGuardado.getId(),
                libroGuardado.getTitulo(),
                libroGuardado.getAnioPublicacion(),
                libroGuardado.getAutor().getNombre(),
                libroGuardado.getAutor().getId(),
                categoriasDTO,
                categoriasIdsDTO
        );

    }

    @Override
    public LibroOutputDTO actualizarLibro(int id, LibroInputDTO dto){

        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el libro con id: " + id));

        libro.setTitulo(dto.getTitulo());
        libro.setAnioPublicacion(dto.getAnioPublicacion());

        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el autor con id: " + dto.getAutorId()));

        libro.setAutor(autor);

        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriaIds());
        if(categorias.size() != dto.getCategoriaIds().size()){
            throw new RecursoNoEncontradoException("Uno o mas categorias no existen");
        }

        libro.setCategorias(categorias);

        Libro libroActualizado = libroRepository.save(libro);

        List<String> categoriasDTO = libroActualizado.getCategorias()
                .stream()
                .map(Categoria::getNombre)
                .toList();

        List<Integer> categoriasIdsDTO = libroActualizado.getCategorias()
                .stream()
                .map(Categoria::getId)
                .toList();

        return new LibroOutputDTO(
                libroActualizado.getId(),
                libroActualizado.getTitulo(),
                libroActualizado.getAnioPublicacion(),
                libroActualizado.getAutor().getNombre(),
                libroActualizado.getAutor().getId(),
                categoriasDTO,
                categoriasIdsDTO
        );
    }

    @Override
    public LibroOutputDTO eliminarLibro(int id){

        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el libro con id: " + id));

        List<String> categoriaDTO = libro.getCategorias()
                .stream()
                .map(Categoria::getNombre)
                .toList();

        List<Integer> categoriasIdsDTO = libro.getCategorias()
                .stream()
                .map(Categoria::getId)
                .toList();

        libroRepository.delete(libro);

        return new LibroOutputDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getAnioPublicacion(),
                libro.getAutor().getNombre(),
                libro.getAutor().getId(),
                categoriaDTO,
                categoriasIdsDTO
        );
    }

    @Override
    public List<LibroOutputDTO> buscarPorTitulo(String titulo){

        List<Libro> libros = libroRepository.findByTituloContainingIgnoreCase(titulo);

        return libros.stream()
                .map(libro -> new LibroOutputDTO(
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAnioPublicacion(),
                        libro.getAutor().getNombre(),
                        libro.getAutor().getId(),
                        libro.getCategorias().stream()
                                .map(categoria -> categoria.getNombre()).toList(),
                        libro.getCategorias().stream()
                                .map(categoria -> categoria.getId()).toList()
                )).toList();

    }

    @Override
    public List<LibroOutputDTO> buscarPorNombre(String nombre){

        List<Libro> libros = libroRepository.findByAutor_NombreContainingIgnoreCase(nombre);

        return  libros.stream()
                .map(libro -> new LibroOutputDTO(
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAnioPublicacion(),
                        libro.getAutor().getNombre(),
                        libro.getAutor().getId(),
                        libro.getCategorias().stream()
                                .map(categoria -> categoria.getNombre()).toList(),
                        libro.getCategorias().stream()
                                .map(categoria -> categoria.getId()).toList()
                )).toList();
    }

    @Override
    public List<LibroOutputDTO> buscarPorNombreoTitulo(String titulo, String autor){

        List<Libro> libros = libroRepository.findByTituloContainingIgnoreCaseOrAutor_NombreContainingIgnoreCase(titulo, autor);

        return libros.stream()
                .map(libro -> new LibroOutputDTO(
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAnioPublicacion(),
                        libro.getAutor().getNombre(),
                        libro.getAutor().getId(),
                        libro.getCategorias().stream()
                                .map(categoria -> categoria.getNombre()).toList(),
                        libro.getCategorias().stream()
                                .map(categoria -> categoria.getId()).toList()
                )).toList();
    }
}

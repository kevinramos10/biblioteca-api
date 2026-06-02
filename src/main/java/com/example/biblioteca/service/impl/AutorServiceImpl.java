package com.example.biblioteca.service.impl;

import com.example.biblioteca.dto.AutorInputDTO;
import com.example.biblioteca.dto.AutorOutputDTO;
import com.example.biblioteca.exception.ConflictoException;
import com.example.biblioteca.exception.RecursoNoEncontradoException;
import com.example.biblioteca.model.Autor;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<AutorOutputDTO> obtenerTodosAutores(){

        List<Autor> autores = autorRepository.findAll();

        return autores.stream()
                .map(a -> new AutorOutputDTO(
                        a.getId(),
                        a.getNombre(),
                        a.getNacionalidad()
                )).collect(Collectors.toList());

    }

    @Override
    public AutorOutputDTO crearAutor(AutorInputDTO dto){

        boolean existe = autorRepository.existsByNombre(dto.getNombre());

        if(existe){
            throw new ConflictoException(
                    "El Autor " + dto.getNombre() + " ya existe"
            );
        }

        Autor autor = new Autor();

        autor.setNombre(dto.getNombre());
        autor.setNacionalidad(dto.getNacionalidad());

        Autor autorGuardado = autorRepository.save(autor);

        return new AutorOutputDTO(
                autorGuardado.getId(),
                autorGuardado.getNombre(),
                autorGuardado.getNacionalidad()
        );

    }

    @Override
    public AutorOutputDTO actualizarActor(int id, AutorInputDTO dto){

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Autor no encontrado con id: " + id));

        autor.setNombre(dto.getNombre());
        autor.setNacionalidad(dto.getNacionalidad());

        Autor autorActualizado = autorRepository.save(autor);

        return new AutorOutputDTO(
                autorActualizado.getId(),
                autorActualizado.getNombre(),
                autorActualizado.getNacionalidad()
        );


    }

    @Override
    public AutorOutputDTO eliminarAutor(int id){

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Autor no encontrado con id: " + id));

        autorRepository.delete(autor);

        return new AutorOutputDTO(
                autor.getId(),
                autor.getNombre(),
                autor.getNacionalidad()
        );
    }



}

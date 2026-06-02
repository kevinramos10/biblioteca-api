import com.example.biblioteca.dto.AutorInputDTO;
import com.example.biblioteca.dto.AutorOutputDTO;
import com.example.biblioteca.model.Autor;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.service.impl.AutorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutorServiceImplTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorServiceImpl autorService;

    public AutorServiceImplTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deberiaCrearAutorCorrectamente(){
        // 1. Datos de entrada
        AutorInputDTO dto = new AutorInputDTO();
        dto.setNombre("Gabriel Garcia Marquez");
        dto.setNacionalidad("Colombiano");

        // 2. Simular comportamiento del repository
        Autor autorGuardado = new Autor();
        autorGuardado.setId(1);
        autorGuardado.setNombre(dto.getNombre());
        autorGuardado.setNacionalidad(dto.getNacionalidad());

        when(autorRepository.save(any(Autor.class))).thenReturn(autorGuardado);

        // 3. Ejecutar método
        AutorOutputDTO resultado = autorService.crearAutor(dto);

        // 4. Verificaciones (lo más importante)
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Gabriel Garcia Marquez", resultado.getNombre());
        assertEquals("Colombiano", resultado.getNacionalidad());


    }

    @Test
    void deberiaActualizarAutorCorrectamente(){
        // 1. Datos de entrada
        AutorInputDTO dto = new AutorInputDTO();

        dto.setNombre("Franz Kafka");
        dto.setNacionalidad("Uruguayo");

        Autor autorActualizado = new Autor();
        autorActualizado.setId(1);
        autorActualizado.setNombre(dto.getNombre());
        autorActualizado.setNacionalidad(dto.getNacionalidad());

        when(autorRepository.findById(1)).thenReturn(Optional.of(autorActualizado));
        when(autorRepository.save(any(Autor.class))).thenReturn(autorActualizado);

        AutorOutputDTO resultado = autorService.actualizarActor(1, dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Franz Kafka", resultado.getNombre());
        assertEquals("Uruguayo", resultado.getNacionalidad());




    }






}

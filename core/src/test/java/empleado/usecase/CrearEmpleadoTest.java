package empleado.usecase;

import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearEmpleadoTest {

    @Mock
    private EmpleadoRepository mockEmpleadoRepository;

    @Test
    void crearEmpleado_success(){

        when(mockEmpleadoRepository.existePorLegajo("ABC1234")).thenReturn(false);

        when(mockEmpleadoRepository.guardar(any(Empleado.class))).thenReturn(true);

        CrearEmpleado usecase= new CrearEmpleado(mockEmpleadoRepository);

        Empleado resultado = usecase.registrar(Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));

        assertNotNull(resultado);
    }

    @Test
    void crearEmpleado_legajoDuplicado_lanzaExcepcion(){

        CrearEmpleado usecase= new CrearEmpleado(mockEmpleadoRepository);

        when(mockEmpleadoRepository.existePorLegajo("ABC1234")).thenReturn(true);

        assertThrows(EmpleadoDuplicadoException.class, () -> usecase.ejecutar(
                Empleado.instance(
                        UUID.randomUUID(),
                        "Guerrero Eduardo Javier",
                        "ABC1234",
                        LocalDate.of(2026,3,21),
                        Puesto.ANALISTA,
                        new BigDecimal("500000")
                )
        ));

    }

    @Test
    void crearEmpleado_falloGuardado_lanzaExcepcion(){

        CrearEmpleado usecase = new CrearEmpleado(mockEmpleadoRepository);

        when(mockEmpleadoRepository.existePorLegajo("ABC1234")).thenReturn(false);
        when(mockEmpleadoRepository.guardar(any(Empleado.class))).thenReturn(false);

        assertThrows(EmpleadoFallaGuardadoException.class, () -> usecase.ejecutar(
                Empleado.instance(
                        UUID.randomUUID(),
                        "Guerrero Eduardo Javier",
                        "ABC1234",
                        LocalDate.of(2026,3,21),
                        Puesto.ANALISTA,
                        new BigDecimal("500000")
                )

        ));

    }


}

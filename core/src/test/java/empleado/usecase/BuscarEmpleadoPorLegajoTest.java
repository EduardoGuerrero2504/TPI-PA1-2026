package empleado.usecase;

import empleado.exception.EmpleadoLegajoNullException;


import empleado.exception.EmpleadoNoEncontradoException;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarEmpleadoPorLegajoTest {

    @Mock
    private EmpleadoRepository mockEmpleadoRepository;

    @Test
    void consultarEmpleado_success() {


        Empleado empleado = Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        );

        when(mockEmpleadoRepository.buscarPorLegajo("ABC1234")).thenReturn(empleado);

        BuscarEmpleadoPorLegajo usecase= new BuscarEmpleadoPorLegajo(mockEmpleadoRepository);

        Empleado resultado = usecase.consultar("ABC1234");

        assertNotNull(resultado);
        assertEquals("Guerrero Eduardo Javier", resultado.getNombre());
        assertEquals("ABC1234", resultado.getLegajo());
    }

    @Test
    void consultarEmpleado_noExiste_lanzaExcepcion() {

        when(mockEmpleadoRepository.buscarPorLegajo("ABC1234")).thenReturn(null);

        BuscarEmpleadoPorLegajo usecase= new BuscarEmpleadoPorLegajo(mockEmpleadoRepository);

        assertThrows(EmpleadoNoEncontradoException.class, () -> usecase.consultar(
                "ABC1234"
        ));
    }

    @Test
    void consultarEmpleado_legajoNull_lanzaExcepcion() {

        BuscarEmpleadoPorLegajo usecase = new BuscarEmpleadoPorLegajo(mockEmpleadoRepository);

        assertThrows(EmpleadoLegajoNullException.class, () -> usecase.consultar(null));
    }

    @Test
    void consultarEmpleado_legajoVacio_lanzaExcepcion() {

        BuscarEmpleadoPorLegajo usecase = new BuscarEmpleadoPorLegajo(mockEmpleadoRepository);

        assertThrows(EmpleadoLegajoNullException.class, () -> usecase.consultar(""));
    }
}

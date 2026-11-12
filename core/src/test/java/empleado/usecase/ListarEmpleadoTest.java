package empleado.usecase;


import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarEmpleadoTest {

    @Mock
    private EmpleadoRepository mockempleadoRepository;

    @Test
    void listarEmpleados_success() {

        Empleado empleado1 = Empleado.instance(
                UUID.randomUUID(),
                "Juan Perez",
                "ABC1234",
                LocalDate.of(2025, 3, 10),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        );

        Empleado empleado2 = Empleado.instance(
                UUID.randomUUID(),
                "Maria Lopez",
                "DEF5678",
                LocalDate.of(2025, 5, 15),
                Puesto.SUPERVISOR,
                new BigDecimal("800000")
        );

        List<Empleado> empleados = List.of(empleado1, empleado2);

        when(mockempleadoRepository.listarTodos()).thenReturn(empleados);

        ListarEmpleados listarEmpleados = new ListarEmpleados(mockempleadoRepository);

        List<Empleado> resultado = listarEmpleados.listar();

        assertEquals(2,resultado.size());
        assertEquals(empleados,resultado);

    }

    @Test
    void listarEmpleados_sinEmpleados() {

        when(mockempleadoRepository.listarTodos()).thenReturn(List.of());

        ListarEmpleados listarEmpleados = new ListarEmpleados(mockempleadoRepository);

        List<Empleado> resultado = listarEmpleados.listar();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(mockempleadoRepository).listarTodos();
    }
}

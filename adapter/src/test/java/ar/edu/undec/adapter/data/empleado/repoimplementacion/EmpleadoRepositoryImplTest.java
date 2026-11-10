package ar.edu.undec.adapter.data.empleado.repoimplementacion;

import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class EmpleadoRepositoryImplTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    void guadarEmpleado_datosValidos(){

        Empleado empleado = Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        );

        boolean resultado= empleadoRepository.guardar(empleado);

        assertTrue(resultado);
    }

    @Test
    void existePorLegajo_empleadoRegistrado(){

        Empleado empleado = Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "DEF5678",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        );

        empleadoRepository.guardar(empleado);

        boolean resultado = empleadoRepository.existePorLegajo("DEF5678");

        assertTrue(resultado);
    }

    @Test
    void buscarPorLegajo_empleadoRegistrado(){

        Empleado empleado = Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "GHI9012",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        );

        empleadoRepository.guardar(empleado);

        Empleado resultado = empleadoRepository.buscarPorLegajo("GHI9012");

        assertNotNull(resultado);
        assertEquals("Guerrero Eduardo Javier", resultado.getNombre());
        assertEquals("GHI9012", resultado.getLegajo());
        assertEquals(Puesto.ANALISTA, resultado.getPuesto());
        assertEquals(new BigDecimal("500000"), resultado.getSueldo());

    }
}

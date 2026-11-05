package empleado.modelo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

    @Test
    void instance_datosValidos(){

        UUID id = UUID.randomUUID();

        Empleado empleado = Empleado.instance(
                id,
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        );

        assertAll(
                () -> assertEquals(id, empleado.getId()),
                () -> assertEquals("Guerrero Eduardo Javier", empleado.getNombre()),
                () -> assertEquals("ABC1234", empleado.getLegajo()),
                () -> assertEquals((LocalDate.of(2026,3,21)), empleado.getFecha()),
                () -> assertEquals(Puesto.ANALISTA, empleado.getPuesto()),
                () -> assertEquals(new BigDecimal("500000"), empleado.getSueldo())
        );
    }

    @Test
    void instance_idNUll_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                null,
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")

        ));

    }

    @Test
    void instance_nombreNull_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_nombreVacio_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_legajoNull_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                null,
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_legajoVacio_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_legajoInvalido_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "abc123",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_fechaInvalida_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.now().plusDays(1),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_fechaNull_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                null,
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_puestoNull_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                null,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_sueldoNull_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                null
        ));
    }

    @Test
    void instance_sueldoCero_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                BigDecimal.ZERO
        ));
    }

    @Test
    void instance_sueldoNegativo_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("-100")
        ));
    }

    @Test
    void instance_analistaSueldoMenor_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("499999")
        ));
    }

    @Test
    void instance_supervisorSueldoMenor_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.SUPERVISOR,
                new BigDecimal("799999")
        ));
    }

    @Test
    void instance_gerenteSueldoMenor_lanzaExcepcion(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.GERENTE,
                new BigDecimal("1199999")
        ));
    }


}

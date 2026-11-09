package ar.edu.undec.adapter.service.empleado;

import ar.edu.undec.adapter.handler.GlobalExceptionHandler;
import ar.edu.undec.adapter.service.empleado.controller.EmpleadoController;
import empleado.exception.EmpleadoDuplicadoException;
import empleado.exception.EmpleadoInvalidaException;
import empleado.exception.EmpleadoLegajoNullException;
import empleado.exception.EmpleadoNoEncontradoException;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.usecase.BuscarEmpleadoPorLegajo;
import empleado.usecase.CrearEmpleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmpleadoControllerTest {

    @Mock
    private CrearEmpleado mockCrearEmpleado;

    @Mock
    private BuscarEmpleadoPorLegajo mockBuscarEmpleadoPorLegajo;

    private Empleado empleado;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

        empleado = Empleado.instance(
                UUID.randomUUID(),
                "Guerrero Eduardo Javier",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        );

        EmpleadoController controller = new EmpleadoController(mockCrearEmpleado,mockBuscarEmpleadoPorLegajo);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void registrarEmpleado() throws Exception {

        when(mockCrearEmpleado.registrar(any(Empleado.class))).thenReturn(empleado);

        String json= """
                {
                    "nombre": "Guerrero Eduardo Javier",
                    "legajo": "ABC1234",
                    "fecha": "2026-03-21",
                    "puesto": "ANALISTA",
                    "sueldo": "500000"
                
                }
                """;

        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Guerrero Eduardo Javier"))
                .andExpect(jsonPath("$.legajo").value("ABC1234"));

        verify(mockCrearEmpleado).registrar(any(Empleado.class));
    }

    @Test
    void registrarEmpleado_legajoDuplicado_conflict() throws Exception {

        when(mockCrearEmpleado.registrar(any(Empleado.class))).thenThrow(new EmpleadoDuplicadoException("Legajo duplicado"));

        String json = """
                {
                    "nombre": "Guerrero Eduardo Javier",
                    "legajo": "ABC1234",
                    "fecha": "2026-03-21",
                    "puesto": "ANALISTA",
                    "sueldo": "500000"
                }
                """;

        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    void consultarEmpleado() throws Exception {

        when(mockBuscarEmpleadoPorLegajo.consultar("ABC1234")).thenReturn(empleado);

        mockMvc.perform(get("/api/empleados/ABC1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Guerrero Eduardo Javier"))
                .andExpect(jsonPath("$.legajo").value("ABC1234"));

        verify(mockBuscarEmpleadoPorLegajo).consultar("ABC1234");
    }

    @Test
    void consultarEmpleado_noExiste_notFound() throws Exception {

        when(mockBuscarEmpleadoPorLegajo.consultar("ABC1234")).thenThrow(new EmpleadoNoEncontradoException("No se encontro el empleado"));

        mockMvc.perform(get("/api/empleados/ABC1234"))
                .andExpect(status().isNotFound());
    }

    @Test
    void registrarEmpleado_datosInvalidos() throws Exception {

        when(mockCrearEmpleado.registrar(any(Empleado.class))).thenThrow(new EmpleadoInvalidaException("Sueldo invalido"));

        String json= """
                {
                    "nombre": "Guerrero Eduardo Javier",
                    "legajo": "ABC1234",
                    "fecha": "2026-03-21",
                    "puesto": "ANALISTA",
                    "sueldo": "500000"
                }
                """;

        mockMvc.perform(post("/api/empleados")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void consultarEmpleado_legajoVacio() throws Exception {

        when(mockBuscarEmpleadoPorLegajo.consultar("%20")).thenThrow(new EmpleadoLegajoNullException("El legajo es obligatorio"));

        mockMvc.perform(get("/api/empleados/%20"))
                .andExpect(status().isBadRequest());

        verify(mockBuscarEmpleadoPorLegajo).consultar("%20");
    }


}

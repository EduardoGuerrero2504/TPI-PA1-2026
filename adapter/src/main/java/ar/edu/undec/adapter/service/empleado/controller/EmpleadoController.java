package ar.edu.undec.adapter.service.empleado.controller;

import ar.edu.undec.adapter.service.empleado.mapper.EmpleadoMapper;
import ar.edu.undec.adapter.service.empleado.model.EmpleadoRequest;
import ar.edu.undec.adapter.service.empleado.model.EmpleadoResponse;
import empleado.modelo.Empleado;
import empleado.usecase.BuscarEmpleadoPorLegajo;
import empleado.usecase.CrearEmpleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final CrearEmpleado  crearEmpleado;
    private final BuscarEmpleadoPorLegajo buscarEmpleadoPorLegajo;

    public EmpleadoController(CrearEmpleado crearEmpleado,  BuscarEmpleadoPorLegajo buscarEmpleadoPorLegajo) {
        this.crearEmpleado = crearEmpleado;
        this.buscarEmpleadoPorLegajo = buscarEmpleadoPorLegajo;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> registrarEmpleado(
            @RequestBody EmpleadoRequest resquest) {

        Empleado empleado = EmpleadoMapper.toDomain(
                UUID.randomUUID(),
                resquest
        );

        Empleado empleadoRegistrado = crearEmpleado.registrar(empleado);

        EmpleadoResponse response = EmpleadoMapper.toResponse(empleadoRegistrado);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<EmpleadoResponse> consultarEmpleado(
            @PathVariable("legajo") String legajo){

        Empleado empleado = buscarEmpleadoPorLegajo.consultar(legajo);

        EmpleadoResponse response = EmpleadoMapper.toResponse(empleado);

        return ResponseEntity.ok(response);
    }

}

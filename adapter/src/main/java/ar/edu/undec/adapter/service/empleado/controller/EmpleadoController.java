package ar.edu.undec.adapter.service.empleado.controller;

import ar.edu.undec.adapter.service.empleado.mapper.EmpleadoMapper;
import ar.edu.undec.adapter.service.empleado.model.EmpleadoRequest;
import ar.edu.undec.adapter.service.empleado.model.EmpleadoResponse;
import empleado.input.BuscarEmpleadoPorLegajoInput;
import empleado.input.CrearEmpleadoInput;
import empleado.input.ListarEmpleadosInput;
import empleado.modelo.Empleado;
import empleado.usecase.BuscarEmpleadoPorLegajo;
import empleado.usecase.CrearEmpleado;
import empleado.usecase.ListarEmpleados;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final CrearEmpleadoInput  crearEmpleado;
    private final BuscarEmpleadoPorLegajoInput buscarEmpleadoPorLegajo;
    private final ListarEmpleadosInput listarEmpleados;

    public EmpleadoController(CrearEmpleadoInput crearEmpleado, BuscarEmpleadoPorLegajoInput buscarEmpleadoPorLegajo, ListarEmpleadosInput listarEmpleados) {
        this.crearEmpleado = crearEmpleado;
        this.buscarEmpleadoPorLegajo = buscarEmpleadoPorLegajo;
        this.listarEmpleados = listarEmpleados;
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

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> listarEmpleados(){

        List<Empleado> empleados = listarEmpleados.listar();

        List<EmpleadoResponse> response = new ArrayList<>();

        for (Empleado empleado : empleados) {
            response.add(EmpleadoMapper.toResponse(empleado));
        }
        return ResponseEntity.ok(response);
    }

}

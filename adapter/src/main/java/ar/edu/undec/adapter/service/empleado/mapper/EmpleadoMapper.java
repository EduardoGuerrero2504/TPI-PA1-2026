package ar.edu.undec.adapter.service.empleado.mapper;

import ar.edu.undec.adapter.service.empleado.model.EmpleadoRequest;
import ar.edu.undec.adapter.service.empleado.model.EmpleadoResponse;
import empleado.modelo.Empleado;

import java.util.UUID;

public class EmpleadoMapper {

    public static Empleado toDomain(UUID id, EmpleadoRequest request) {

        return Empleado.instance(
                id,
                request.getNombre(),
                request.getLegajo(),
                request.getFecha(),
                request.getPuesto(),
                request.getSueldo()
        );
    }

    public static EmpleadoResponse toResponse(Empleado empleado) {
        return new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getLegajo(),
                empleado.getFecha(),
                empleado.getPuesto(),
                empleado.getSueldo()
        );
    }
}

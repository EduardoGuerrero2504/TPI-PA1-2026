package ar.edu.undec.adapter.data.empleado.mapper;

import ar.edu.undec.adapter.data.empleado.model.EmpleadoEntity;
import empleado.modelo.Empleado;

public class EmpleadoEntityMapper {

    public static EmpleadoEntity toEntity(Empleado empleado) {
        return new EmpleadoEntity(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getLegajo(),
                empleado.getFecha(),
                empleado.getPuesto(),
                empleado.getSueldo()
        );
    }

    public static Empleado toDomain(EmpleadoEntity empleadoEntity) {
        return Empleado.instance(
                empleadoEntity.getId(),
                empleadoEntity.getNombre(),
                empleadoEntity.getLegajo(),
                empleadoEntity.getFecha(),
                empleadoEntity.getPuesto(),
                empleadoEntity.getSueldo()
        );
    }
}

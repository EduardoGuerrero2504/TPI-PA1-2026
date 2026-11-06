package empleado.usecase;

import empleado.exception.EmpleadoDuplicadoException;
import empleado.exception.EmpleadoFallaGuardadoException;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;

public class CrearEmpleado {

    private EmpleadoRepository mockEmpleadoRepository;

    public CrearEmpleado(EmpleadoRepository mockEmpleadoRepository) {
        this.mockEmpleadoRepository = mockEmpleadoRepository;
    }

    public Empleado registrar(Empleado empleado) {
        if(mockEmpleadoRepository.existePorLegajo(empleado.getLegajo())) {
            throw new EmpleadoDuplicadoException("Ya existe un empleado con este legajo");
        }

        boolean guardado = mockEmpleadoRepository.guardar(empleado);

        if(!guardado) {
            throw new EmpleadoFallaGuardadoException("No se pudo guardar el empleado");
        }

        return empleado;
    }
}

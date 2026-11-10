package empleado.usecase;

import empleado.exception.EmpleadoLegajoNullException;
import empleado.exception.EmpleadoNoEncontradoException;
import empleado.input.BuscarEmpleadoPorLegajoInput;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;

public class BuscarEmpleadoPorLegajo implements BuscarEmpleadoPorLegajoInput {

    private EmpleadoRepository mockEmpleadoRepository;

    public BuscarEmpleadoPorLegajo(EmpleadoRepository mockEmpleadoRepository) {
        this.mockEmpleadoRepository = mockEmpleadoRepository;
    }

    @Override
    public Empleado consultar(String legajo){

        if(legajo == null || legajo.isBlank()) {
            throw new EmpleadoLegajoNullException("El legajo es obligatorio");
        }

        Empleado empleado = mockEmpleadoRepository.buscarPorLegajo(legajo);

        if (empleado == null) {
            throw new EmpleadoNoEncontradoException("No se encontro el empleado");
        }

        return empleado;
    }
}

package empleado.usecase;

import empleado.input.ListarEmpleadosInput;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;

import java.util.List;

public class ListarEmpleados implements ListarEmpleadosInput {

    private EmpleadoRepository mockempleadoRepository;

    public ListarEmpleados(EmpleadoRepository mockempleadoRepository) {
        this.mockempleadoRepository = mockempleadoRepository;
    }

    @Override
    public List<Empleado> listar() {
        return mockempleadoRepository.listarTodos();
    }
}

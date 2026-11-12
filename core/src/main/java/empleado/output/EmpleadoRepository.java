package empleado.output;

import empleado.modelo.Empleado;

import java.util.List;

public interface EmpleadoRepository {
    boolean existePorLegajo(String legajo);
    boolean guardar(Empleado empleado);

    Empleado buscarPorLegajo(String legajo);

    List<Empleado> listarTodos();
}

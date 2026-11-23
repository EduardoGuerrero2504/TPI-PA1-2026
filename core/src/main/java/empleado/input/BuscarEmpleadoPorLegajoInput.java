package empleado.input;

import empleado.modelo.Empleado;

public interface BuscarEmpleadoPorLegajoInput {

    Empleado consultar(String legajo);
}

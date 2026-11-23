package ar.edu.undec.adapter.service.empleado.beanconfig;

import empleado.input.BuscarEmpleadoPorLegajoInput;
import empleado.input.CrearEmpleadoInput;
import empleado.input.ListarEmpleadosInput;
import empleado.output.EmpleadoRepository;
import empleado.usecase.BuscarEmpleadoPorLegajo;
import empleado.usecase.CrearEmpleado;
import empleado.usecase.ListarEmpleados;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfing {

    @Bean
    public CrearEmpleadoInput crearEmpleadoInput(EmpleadoRepository empleadoRepository) {
        return new CrearEmpleado(empleadoRepository);
    }

    @Bean
    public BuscarEmpleadoPorLegajoInput buscarEmpleadoPorLegajoInput(EmpleadoRepository empleadoRepository) {
        return new BuscarEmpleadoPorLegajo(empleadoRepository);
    }

    @Bean
    public ListarEmpleadosInput listarEmpleadosInput(EmpleadoRepository empleadoRepository) {
        return new ListarEmpleados(empleadoRepository);
    }
}

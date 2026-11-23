package ar.edu.undec.adapter.data.empleado.repoimplementacion;

import ar.edu.undec.adapter.data.empleado.crud.EmpleadoCrudRepository;
import ar.edu.undec.adapter.data.empleado.mapper.EmpleadoEntityMapper;
import ar.edu.undec.adapter.data.empleado.model.EmpleadoEntity;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    private final EmpleadoCrudRepository empleadoCrudRepository;

    public EmpleadoRepositoryImpl(EmpleadoCrudRepository empleadoCrudRepository) {
        this.empleadoCrudRepository = empleadoCrudRepository;
    }

    @Override
    public boolean existePorLegajo(String legajo) {
        return empleadoCrudRepository.existsByLegajo(legajo);
    }

    @Override
    public boolean guardar(Empleado  empleado) {
        EmpleadoEntity empleadoEntity = EmpleadoEntityMapper.toEntity(empleado);
        empleadoCrudRepository.save(empleadoEntity);
        return true;
    }

    @Override
    public Empleado buscarPorLegajo(String legajo) {
        EmpleadoEntity empleadoEntity = empleadoCrudRepository.findByLegajo(legajo);

        if (empleadoEntity == null) {
            return null;
        }

        return EmpleadoEntityMapper.toDomain(empleadoEntity);
    }

    @Override
    public List<Empleado> listarTodos(){

        List<Empleado> empleados = new ArrayList<>();

        for (EmpleadoEntity empleadoEntity : empleadoCrudRepository.findAll()) {
            empleados.add(EmpleadoEntityMapper.toDomain(empleadoEntity));
        }

        return empleados;
    }
}

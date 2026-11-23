package empleado.modelo;

import empleado.exception.EmpleadoInvalidaException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Empleado {

    private UUID id;
    private String nombre;
    private String legajo;
    private LocalDate fecha;
    private Puesto puesto;
    private BigDecimal sueldo;

    private Empleado (UUID id, String nombre, String legajo,
                      LocalDate fecha, Puesto puesto, BigDecimal sueldo){
        this.id = id;
        this.nombre = nombre;
        this.legajo = legajo;
        this.fecha = fecha;
        this.puesto = puesto;
        this.sueldo = sueldo;
    }

    public static Empleado instance(UUID id, String nombre, String legajo,LocalDate fecha, Puesto puesto, BigDecimal sueldo){

        if (id == null){
            throw new EmpleadoInvalidaException("El id no puede ser nulo");
        }
        if (nombre == null || nombre.isEmpty()){
            throw new EmpleadoInvalidaException("El nombre no puede ser nulo");
        }

        if (legajo == null || !legajo.matches("[A-Z]{3}[0-9]{4}")){
            throw new EmpleadoInvalidaException("El legajo no puede ser nulo y tiene que tener 3 letras mayusculas y 4 numeros");
        }

        if (fecha == null){
            throw new EmpleadoInvalidaException("El fecha no puede ser nulo");
        }

        if (fecha.isAfter(LocalDate.now())){
            throw new EmpleadoInvalidaException("La fecha no puede ser futura");
        }

        if  (puesto == null){
            throw new EmpleadoInvalidaException("El puesto no puede ser nulo");
        }

        if (sueldo == null){
            throw new EmpleadoInvalidaException("El sueldo no puede ser nulo");
        }

        if (sueldo.compareTo(BigDecimal.ZERO) <= 0){
            throw new EmpleadoInvalidaException("El sueldo no puede ser menor que 0");
        }

        BigDecimal sueldoMinimo;

        switch (puesto){
            case ANALISTA:
                sueldoMinimo = new BigDecimal("500000");
                break;
            case SUPERVISOR:
                sueldoMinimo = new BigDecimal("800000");
                break;
            case GERENTE:
                sueldoMinimo = new BigDecimal("1200000");
                break;
            default:
                throw new EmpleadoInvalidaException("El puesto es invalido");
        }

        if (sueldo.compareTo(sueldoMinimo) < 0){
            throw new EmpleadoInvalidaException("El sueldo no alcanza para el sueldo");
        }



        return new Empleado(id, nombre, legajo, fecha, puesto, sueldo);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Puesto getPuesto(){
        return puesto;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

}

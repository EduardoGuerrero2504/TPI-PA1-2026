package ar.edu.undec.adapter.service.empleado.model;

import empleado.modelo.Puesto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class EmpleadoResponse {

    private UUID id;
    private String nombre;
    private String legajo;
    private LocalDate fecha;
    private Puesto  puesto;
    private BigDecimal sueldo;

    public EmpleadoResponse(UUID id, String nombre, String legajo, LocalDate fecha, Puesto puesto, BigDecimal sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.legajo = legajo;
        this.fecha = fecha;
        this.puesto = puesto;
        this.sueldo = sueldo;
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

    public Puesto getPuesto() {
        return puesto;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

}

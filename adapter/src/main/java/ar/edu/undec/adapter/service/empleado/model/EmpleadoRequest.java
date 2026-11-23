package ar.edu.undec.adapter.service.empleado.model;

import empleado.modelo.Puesto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoRequest {

    private String nombre;
    private String legajo;
    private LocalDate fecha;
    private Puesto puesto;
    private BigDecimal sueldo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

}

package ar.edu.undec.adapter.data.empleado.model;

import empleado.modelo.Puesto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "empleados")
public class EmpleadoEntity {


    @Id
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String legajo;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Puesto  puesto;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal sueldo;

    public EmpleadoEntity() {

    }

    public EmpleadoEntity(UUID id, String nombre, String legajo, LocalDate fecha, Puesto puesto, BigDecimal sueldo) {
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

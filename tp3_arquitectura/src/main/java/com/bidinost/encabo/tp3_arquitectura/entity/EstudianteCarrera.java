package com.bidinost.encabo.tp3_arquitectura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "estudiante_carrera")
public class EstudianteCarrera implements Serializable {

    @EmbeddedId
    private EstudianteCarreraId id;

    @NotNull(message = "La fecha de inscripción es obligatoria")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private java.util.Date fechaInscripcion;

    @NotNull(message = "La antigüedad es obligatoria")
    @Min(value = 0, message = "La antigüedad no puede ser negativa")
    @Max(value = 50, message = "La antigüedad no puede ser mayor a 50 años")
    @Column(nullable = false)
    private Integer antiguedad; // Antigüedad en años

    @NotNull(message = "El estado de graduación es obligatorio")
    @Column(nullable = false)
    private Boolean graduado; // Si se graduó en esta carrera específica

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", insertable = false, updatable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_id", insertable = false, updatable = false)
    private Carrera carrera;

    public EstudianteCarrera() {
    }

    public EstudianteCarrera(EstudianteCarreraId id, java.util.Date fechaInscripcion, Integer antiguedad, Boolean graduado) {
        this.id = id;
        this.fechaInscripcion = fechaInscripcion;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }

    public EstudianteCarrera(Long estudianteId, Long carreraId, java.util.Date fechaInscripcion, Integer antiguedad, Boolean graduado) {
        this.id = new EstudianteCarreraId(estudianteId, carreraId);
        this.fechaInscripcion = fechaInscripcion;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }

    // Getters y Setters
    public EstudianteCarreraId getId() {
        return id;
    }

    public void setId(EstudianteCarreraId id) {
        this.id = id;
    }

    public java.util.Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(java.util.Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Boolean getGraduado() {
        return graduado;
    }

    public void setGraduado(Boolean graduado) {
        this.graduado = graduado;
    }

    public Long getEstudianteId() {
        return id != null ? id.getEstudianteId() : null;
    }

    public Long getCarreraId() {
        return id != null ? id.getCarreraId() : null;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "EstudianteCarrera{" +
                "id=" + id +
                ", fechaInscripcion=" + fechaInscripcion +
                ", antiguedad=" + antiguedad +
                ", graduado=" + graduado +
                '}';
    }
}


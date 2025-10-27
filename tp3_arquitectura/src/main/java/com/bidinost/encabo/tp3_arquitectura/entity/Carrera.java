package com.bidinost.encabo.tp3_arquitectura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carreras")
public class Carrera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la carrera es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre de la carrera debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @NotBlank(message = "La facultad es obligatoria")
    @Size(min = 3, max = 100, message = "La facultad debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String facultad;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EstudianteCarrera> estudiantesInscritos = new ArrayList<>();

    public Carrera() {
    }

    public Carrera(String nombre, String facultad) {
        this.nombre = nombre;
        this.facultad = facultad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public List<EstudianteCarrera> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public void setEstudiantesInscritos(List<EstudianteCarrera> estudiantesInscritos) {
        this.estudiantesInscritos = estudiantesInscritos;
    }

    public void agregarEstudiante(EstudianteCarrera inscripcion) {
        estudiantesInscritos.add(inscripcion);
        inscripcion.setCarrera(this);
    }

    public void removerEstudiante(EstudianteCarrera inscripcion) {
        estudiantesInscritos.remove(inscripcion);
        inscripcion.setCarrera(null);
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", facultad='" + facultad + '\'' +
                '}';
    }
}


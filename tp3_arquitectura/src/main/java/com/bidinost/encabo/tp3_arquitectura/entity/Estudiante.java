package com.bidinost.encabo.tp3_arquitectura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiantes")
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String apellido;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 100, message = "La edad máxima es 100 años")
    @Column(nullable = false)
    private Integer edad;

    @NotBlank(message = "El género es obligatorio")
    @Size(max = 20, message = "El género no puede tener más de 20 caracteres")
    @Column(nullable = false, length = 20)
    private String genero;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(min = 6, max = 20, message = "El número de documento debe tener entre 6 y 20 caracteres")
    @Column(nullable = false, unique = true, length = 20)
    private String numeroDocumento;

    @NotBlank(message = "La ciudad de residencia es obligatoria")
    @Size(min = 3, max = 100, message = "La ciudad de residencia debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String ciudadDeResidencia;

    @NotNull(message = "El número de libreta universitaria es obligatorio")
    @Min(value = 1, message = "El número de libreta universitaria debe ser mayor que 0")
    @Column(nullable = false, unique = true)
    private Integer numeroLibretaUniversitaria;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EstudianteCarrera> inscripciones = new ArrayList<>();

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, Integer edad, String genero, 
                      String numeroDocumento, String ciudadDeResidencia, Integer numeroLibretaUniversitaria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudadDeResidencia = ciudadDeResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCiudadDeResidencia() {
        return ciudadDeResidencia;
    }

    public void setCiudadDeResidencia(String ciudadDeResidencia) {
        this.ciudadDeResidencia = ciudadDeResidencia;
    }

    public Integer getNumeroLibretaUniversitaria() {
        return numeroLibretaUniversitaria;
    }

    public void setNumeroLibretaUniversitaria(Integer numeroLibretaUniversitaria) {
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
    }

    public List<EstudianteCarrera> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<EstudianteCarrera> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public void agregarInscripcion(EstudianteCarrera inscripcion) {
        inscripciones.add(inscripcion);
        inscripcion.setEstudiante(this);
    }

    public void removerInscripcion(EstudianteCarrera inscripcion) {
        inscripciones.remove(inscripcion);
        inscripcion.setEstudiante(null);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", numeroLibretaUniversitaria=" + numeroLibretaUniversitaria +
                '}';
    }
}


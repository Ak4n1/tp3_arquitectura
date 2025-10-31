package com.bidinost.encabo.tp3_arquitectura.dto;

import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;

public class ResponseEstudianteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String genero;
    private String numeroDocumento;
    private String ciudadDeResidencia;
    private Integer numeroLibretaUniversitaria;

    public ResponseEstudianteDTO() {
    }

    public ResponseEstudianteDTO(Long id, String nombre, String apellido, Integer edad, 
                                String genero, String numeroDocumento, String ciudadDeResidencia, 
                                Integer numeroLibretaUniversitaria) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudadDeResidencia = ciudadDeResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
    }

    // Constructor que acepta un Estudiante para facilitar la conversión
    public ResponseEstudianteDTO(Estudiante estudiante) {
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.edad = estudiante.getEdad();
        this.genero = estudiante.getGenero();
        this.numeroDocumento = estudiante.getNumeroDocumento();
        this.ciudadDeResidencia = estudiante.getCiudadDeResidencia();
        this.numeroLibretaUniversitaria = estudiante.getNumeroLibretaUniversitaria();
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

    @Override
    public String toString() {
        return "ResponseEstudianteDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", ciudadDeResidencia='" + ciudadDeResidencia + '\'' +
                ", numeroLibretaUniversitaria=" + numeroLibretaUniversitaria +
                '}';
    }
}


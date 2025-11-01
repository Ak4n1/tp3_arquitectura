package com.bidinost.encabo.tp3_arquitectura.dto;

import com.bidinost.encabo.tp3_arquitectura.entity.Carrera;

public class ResponseCarreraDTO {

    private Long id;
    private String nombre;
    private String facultad;
    private Integer cantidadInscriptos;

    public ResponseCarreraDTO() {
    }

    public ResponseCarreraDTO(Long id, String nombre, String facultad, Integer cantidadInscriptos) {
        this.id = id;
        this.nombre = nombre;
        this.facultad = facultad;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    // Constructor que acepta un Carrera para facilitar la conversión
    public ResponseCarreraDTO(Carrera carrera) {
        this.id = carrera.getId();
        this.nombre = carrera.getNombre();
        this.facultad = carrera.getFacultad();
        this.cantidadInscriptos = carrera.getEstudiantesInscritos() != null 
            ? carrera.getEstudiantesInscritos().size() 
            : 0;
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

    public Integer getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public void setCantidadInscriptos(Integer cantidadInscriptos) {
        this.cantidadInscriptos = cantidadInscriptos;
    }

    @Override
    public String toString() {
        return "ResponseCarreraDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", facultad='" + facultad + '\'' +
                ", cantidadInscriptos=" + cantidadInscriptos +
                '}';
    }
}


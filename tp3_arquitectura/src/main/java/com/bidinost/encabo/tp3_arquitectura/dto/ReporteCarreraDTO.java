package com.bidinost.encabo.tp3_arquitectura.dto;

import java.util.Map;

public class ReporteCarreraDTO {

    private Long id;
    private String nombre;
    private String facultad;
    // Map con año como clave (Integer) y cantidad como valor
    // inscriptosPorAnio: {2020: 15, 2021: 20, 2022: 18}
    private Map<Integer, Integer> inscriptosPorAnio;
    // Map con año como clave (Integer) y cantidad como valor
    // egresadosPorAnio: {2023: 5, 2024: 8}
    private Map<Integer, Integer> egresadosPorAnio;

    public ReporteCarreraDTO() {
    }

    public ReporteCarreraDTO(Long id, String nombre, String facultad, 
                             Map<Integer, Integer> inscriptosPorAnio, 
                             Map<Integer, Integer> egresadosPorAnio) {
        this.id = id;
        this.nombre = nombre;
        this.facultad = facultad;
        this.inscriptosPorAnio = inscriptosPorAnio;
        this.egresadosPorAnio = egresadosPorAnio;
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

    public Map<Integer, Integer> getInscriptosPorAnio() {
        return inscriptosPorAnio;
    }

    public void setInscriptosPorAnio(Map<Integer, Integer> inscriptosPorAnio) {
        this.inscriptosPorAnio = inscriptosPorAnio;
    }

    public Map<Integer, Integer> getEgresadosPorAnio() {
        return egresadosPorAnio;
    }

    public void setEgresadosPorAnio(Map<Integer, Integer> egresadosPorAnio) {
        this.egresadosPorAnio = egresadosPorAnio;
    }
}


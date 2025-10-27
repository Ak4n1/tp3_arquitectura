package com.bidinost.encabo.tp3_arquitectura.dto;

import jakarta.validation.constraints.*;

public class RequestCrearCarreraDTO {
    
    @NotBlank(message = "El nombre de la carrera es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre de la carrera debe tener entre 3 y 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "La facultad es obligatoria")
    @Size(min = 3, max = 100, message = "La facultad debe tener entre 3 y 100 caracteres")
    private String facultad;

    public RequestCrearCarreraDTO() {
    }

    public RequestCrearCarreraDTO(String nombre, String facultad) {
        this.nombre = nombre;
        this.facultad = facultad;
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
    
}


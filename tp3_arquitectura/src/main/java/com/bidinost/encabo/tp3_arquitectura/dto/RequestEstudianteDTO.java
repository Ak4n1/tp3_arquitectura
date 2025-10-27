package com.bidinost.encabo.tp3_arquitectura.dto;

import jakarta.validation.constraints.*;

public class RequestEstudianteDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;
    
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 100, message = "La edad máxima es 100 años")
    private Integer edad;
    
    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "^(Masculino|Femenino|Otro)$", message = "El género debe ser Masculino, Femenino u Otro")
    private String genero;
    
    @NotBlank(message = "El número de documento es obligatorio")
    @Size(min = 6, max = 20, message = "El número de documento debe tener entre 6 y 20 caracteres")
    private String numeroDocumento;
    
    @NotBlank(message = "La ciudad de residencia es obligatoria")
    @Size(min = 3, max = 100, message = "La ciudad de residencia debe tener entre 3 y 100 caracteres")
    private String ciudadDeResidencia;
    
    @NotNull(message = "El número de libreta universitaria es obligatorio")
    @Min(value = 1, message = "El número de libreta universitaria debe ser mayor que 0")
    private Integer numeroLibretaUniversitaria;


    public RequestEstudianteDTO(String nombre, String apellido, Integer edad, String genero, String numeroDocumento, String ciudadDeResidencia, Integer numeroLibretaUniversitaria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudadDeResidencia = ciudadDeResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }   

    public String getCiudadDeResidencia() {
        return ciudadDeResidencia;
    }

    public Integer getNumeroLibretaUniversitaria() {
        return numeroLibretaUniversitaria;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public void setCiudadDeResidencia(String ciudadDeResidencia) {
        this.ciudadDeResidencia = ciudadDeResidencia;
    }       

    public void setNumeroLibretaUniversitaria(Integer numeroLibretaUniversitaria) {
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
    }

    @Override
    public String toString() {
        return "RequestEstudianteDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", ciudadDeResidencia='" + ciudadDeResidencia + '\'' +
                ", numeroLibretaUniversitaria=" + numeroLibretaUniversitaria +
                '}';
    }           
}

package com.bidinost.encabo.tp3_arquitectura.dto;

public class RequestMatricularEstudiante {

    private Long estudianteId;
    private Long carreraId;

    public RequestMatricularEstudiante(Long estudianteId, Long carreraId) {
        this.estudianteId = estudianteId;
        this.carreraId = carreraId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }
    
    public Long getCarreraId() {
        return carreraId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }
    
    public void setCarreraId(Long carreraId) {
        this.carreraId = carreraId;
    }
}

package com.bidinost.encabo.tp3_arquitectura.service;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;

public interface ServiceEstudiante {
    
    Estudiante crearEstudiante(RequestEstudianteDTO request);
    
}


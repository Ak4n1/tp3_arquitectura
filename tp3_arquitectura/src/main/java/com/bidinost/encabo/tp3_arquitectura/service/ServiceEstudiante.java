package com.bidinost.encabo.tp3_arquitectura.service;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.dto.ResponseEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;
import java.util.List;

public interface ServiceEstudiante {
    
    Estudiante crearEstudiante(RequestEstudianteDTO request);
    //retorna un listado de ordenados segun criterio
    List<ResponseEstudianteDTO> obtenerTodosLosEstudiantes(String criterio);
    
}


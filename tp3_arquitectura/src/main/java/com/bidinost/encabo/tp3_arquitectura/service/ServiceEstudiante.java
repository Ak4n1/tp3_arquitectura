package com.bidinost.encabo.tp3_arquitectura.service;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.dto.ResponseEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;
import java.util.List;

public interface ServiceEstudiante {
    
    Estudiante crearEstudiante(RequestEstudianteDTO request);
    //retorna un listado de ordenados segun criterio
    List<ResponseEstudianteDTO> obtenerTodosLosEstudiantes(String criterio);
    //retorna un estudiante en base a su número de libreta universitaria
    ResponseEstudianteDTO obtenerEstudiantePorLibreta(Integer numeroLibretaUniversitaria);
    //retorna un listado de estudiantes en base a su género
    List<ResponseEstudianteDTO> obtenerEstudiantesPorGenero(String genero);
    //retorna un listado de estudiantes de una carrera filtrados por ciudad de residencia
    List<ResponseEstudianteDTO> obtenerEstudiantesPorCarreraYCiudad(Long carreraId, String ciudadDeResidencia);
    
}


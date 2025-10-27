package com.bidinost.encabo.tp3_arquitectura.service;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestCrearCarreraDTO;
import com.bidinost.encabo.tp3_arquitectura.dto.RequestMatricularEstudiante;
import com.bidinost.encabo.tp3_arquitectura.entity.Carrera;

import java.util.List;
import java.util.Map;

public interface ServiceCarrera {
    
    Map<String, String> matricularEstudiante(RequestMatricularEstudiante request);
    
    Map<String, String> crearCarrera(RequestCrearCarreraDTO request);
    
    List<Map<String, Object>> obtenerTodasLasCarreras();
    
    Map<String, Object> obtenerCarreraPorId(Long id);
    
    Map<String, String> actualizarCarrera(Long id, RequestCrearCarreraDTO request);
    
    Map<String, String> eliminarCarrera(Long id);
}


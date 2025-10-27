package com.bidinost.encabo.tp3_arquitectura.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;
import com.bidinost.encabo.tp3_arquitectura.service.ServiceEstudiante;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    
    @Autowired
    private ServiceEstudiante serviceEstudiante;

 


    @PostMapping("/crear")
    public Estudiante crearEstudiante(@Valid @RequestBody RequestEstudianteDTO request) {
        return serviceEstudiante.crearEstudiante(request);
    }
    
    


}


package com.bidinost.encabo.tp3_arquitectura.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.dto.ResponseEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;
import com.bidinost.encabo.tp3_arquitectura.service.ServiceEstudiante;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    
    @Autowired
    private ServiceEstudiante serviceEstudiante;

 


    @PostMapping("/crear")
    public Estudiante crearEstudiante(@Valid @RequestBody RequestEstudianteDTO request) {
        return serviceEstudiante.crearEstudiante(request);
    }
    //devuelve el listado de estudiantes, NO debe haber logica adicional en el controller
    //en vez de devolver un estudiante, devuelve un dto y evitar el error de serializacion
    @GetMapping("/ordenar-Todos/{criterio}")
    //la url es /estudiantes/ordenar-Todos/ASC para que sea intuitivo que estas ordenando estudiantes y de que manera
    public List<ResponseEstudianteDTO> obtenerTodosLosEstudiantes(@PathVariable String criterio) {
        return serviceEstudiante.obtenerTodosLosEstudiantes(criterio);
    }
    
    @GetMapping("/libreta/{numeroLibreta}")
    //solo un estudiante puede tener una sola libreta universitaria, por lo tanto va a devolver solo 1
    //la url es /estudiantes/libreta/12345 para recuperar un estudiante por su número de libreta universitaria
    public ResponseEstudianteDTO obtenerEstudiantePorLibreta(@PathVariable Integer numeroLibreta) {
        return serviceEstudiante.obtenerEstudiantePorLibreta(numeroLibreta);
    }
    @GetMapping("/genero/{genero}")
    //la url va a devolver todos los estudiantes en base a su genero
    //la url es /estudiantes/genero/Masculino para obtener todos los estudiantes de ese género
    public List<ResponseEstudianteDTO> obtenerEstudiantesPorGenero(@PathVariable String genero) {
        return serviceEstudiante.obtenerEstudiantesPorGenero(genero);
    }
    
    @GetMapping("/carrera/{carreraId}/ciudad/{ciudadDeResidencia}")
    //la url es /estudiantes/carrera/1/ciudad/Buenos Aires para obtener estudiantes de esa carrera filtrados por ciudad
    //si la ciudad tiene espacios en la URL se veria como %20 (ej: Buenos%20Aires)
    public List<ResponseEstudianteDTO> obtenerEstudiantesPorCarreraYCiudad(
            @PathVariable Long carreraId, 
            @PathVariable String ciudadDeResidencia) {
        return serviceEstudiante.obtenerEstudiantesPorCarreraYCiudad(carreraId, ciudadDeResidencia);
    }

}


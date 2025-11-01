package com.bidinost.encabo.tp3_arquitectura.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestCrearCarreraDTO;
import com.bidinost.encabo.tp3_arquitectura.dto.RequestMatricularEstudiante;
import com.bidinost.encabo.tp3_arquitectura.dto.ResponseCarreraDTO;
import com.bidinost.encabo.tp3_arquitectura.dto.ReporteCarreraDTO;
import com.bidinost.encabo.tp3_arquitectura.entity.Carrera;
import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;
import com.bidinost.encabo.tp3_arquitectura.entity.EstudianteCarrera;
import com.bidinost.encabo.tp3_arquitectura.entity.EstudianteCarreraId;
import com.bidinost.encabo.tp3_arquitectura.exception.DuplicateResourceException;
import com.bidinost.encabo.tp3_arquitectura.exception.ResourceNotFoundException;
import com.bidinost.encabo.tp3_arquitectura.repository.CarreraRepository;
import com.bidinost.encabo.tp3_arquitectura.repository.EstudianteCarreraRepository;
import com.bidinost.encabo.tp3_arquitectura.repository.EstudianteRepository;

@Service
public class ServiceCarreraImpl implements ServiceCarrera {


    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository carreraRepository;
    
    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;
    
    @Override
    public Map<String, String> matricularEstudiante(RequestMatricularEstudiante request) {
        // Optional<Estudiante>: 
        // - Optional es un contenedor que puede contener un valor o estar vacío
        // - findById() retorna Optional porque puede no encontrar el registro
        // - estudiante puede ser: Optional con Estudiante adentro o Optional vacío
        Optional<Estudiante> estudiante = estudianteRepository.findById(request.getEstudianteId());
        Optional<Carrera> carrera = carreraRepository.findById(request.getCarreraId());
        
        
        // .isEmpty() devuelve true si el Optional está vacío (no encontró el registro)
        if(estudiante.isEmpty()) {
            throw new ResourceNotFoundException("Estudiante con ID: " + request.getEstudianteId() + " no encontrado en el sistema.");
        }
        if(carrera.isEmpty()) {
            throw new ResourceNotFoundException("Carrera con ID: " + request.getCarreraId() + " no encontrada en el sistema.");
        }

        // Verificar que no esté ya inscrito en esta carrera
        EstudianteCarreraId estudianteCarreraId = new EstudianteCarreraId(request.getEstudianteId(), request.getCarreraId());
        // Busca si ya existe la inscripción
        Optional<EstudianteCarrera> inscripcionExistente = estudianteCarreraRepository.findById(estudianteCarreraId);
        
        // .isPresent() devuelve true si el Optional TIENE un valor (opuesto de isEmpty)
        if (inscripcionExistente.isPresent()) {
            throw new DuplicateResourceException("El estudiante ya está inscrito en esta carrera.");
        }
        // Crear la inscripción
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudianteCarreraId, new Date(), 0, false);
        // .get() extrae el valor del Optional (ya sabemos que existe porque usamos isEmpty/isPresent antes)
        estudianteCarrera.setEstudiante(estudiante.get());
        estudianteCarrera.setCarrera(carrera.get());
        
        // Guardar la inscripción
        estudianteCarreraRepository.save(estudianteCarrera);
        
        // Preparar respuesta
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Estudiante matriculado exitosamente");
        respuesta.put("estudiante", estudiante.get().getNombre() + " " + estudiante.get().getApellido());
        respuesta.put("carrera", carrera.get().getNombre());
        respuesta.put("fechaInscripcion", new Date().toString());
        
        return respuesta;
    }
    
    @Override
    public Map<String, String> crearCarrera(RequestCrearCarreraDTO request) {
        // Verificar si ya existe una carrera con ese nombre
        if (carreraRepository.existsByNombre(request.getNombre())) {
            throw new DuplicateResourceException("Ya existe una carrera con el nombre: " + request.getNombre());
        }
        
        // Crear la carrera
        Carrera carrera = new Carrera();
        carrera.setNombre(request.getNombre());
        carrera.setFacultad(request.getFacultad());
        
        // Guardar en la base de datos
        carreraRepository.save(carrera);
        
        // Preparar respuesta
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Carrera creada exitosamente");
        respuesta.put("carrera", carrera.getNombre());
        respuesta.put("facultad", carrera.getFacultad());
        respuesta.put("id", carrera.getId().toString());
        
        return respuesta;
    }
    
    @Override
    public List<Map<String, Object>> obtenerTodasLasCarreras() {
        // Obtener todas las carreras de la base de datos
        List<Carrera> carreras = carreraRepository.findAll();
        
        // Convertir cada carrera a un Map para la respuesta
        List<Map<String, Object>> respuesta = new ArrayList<>();
        for (Carrera carrera : carreras) {
            Map<String, Object> carreraMap = new HashMap<>();
            carreraMap.put("id", carrera.getId());
            carreraMap.put("nombre", carrera.getNombre());
            carreraMap.put("facultad", carrera.getFacultad());
            respuesta.add(carreraMap);
        }
        
        return respuesta;
    }
    
    @Override
    public Map<String, Object> obtenerCarreraPorId(Long id) {
        // Buscar la carrera por ID
        Optional<Carrera> carrera = carreraRepository.findById(id);
        
        if (carrera.isEmpty()) {
            throw new ResourceNotFoundException("Carrera con ID: " + id + " no encontrada en el sistema.");
        }
        
        // Preparar respuesta
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("id", carrera.get().getId());
        respuesta.put("nombre", carrera.get().getNombre());
        respuesta.put("facultad", carrera.get().getFacultad());
        
        return respuesta;
    }
    
    @Override
    public Map<String, String> actualizarCarrera(Long id, RequestCrearCarreraDTO request) {
        // Buscar la carrera por ID
        Optional<Carrera> carreraOptional = carreraRepository.findById(id);
        
        if (carreraOptional.isEmpty()) {
            throw new ResourceNotFoundException("Carrera con ID: " + id + " no encontrada para actualizar.");
        }
        
        // Verificar si el nuevo nombre ya existe (excepto la carrera actual)
        Optional<Carrera> carreraConMismoNombre = carreraRepository.findByNombre(request.getNombre());
        if (carreraConMismoNombre.isPresent() && !carreraConMismoNombre.get().getId().equals(id)) {
            throw new DuplicateResourceException("Ya existe otra carrera con el nombre: " + request.getNombre());
        }
        
        // Actualizar los campos
        Carrera carrera = carreraOptional.get();
        carrera.setNombre(request.getNombre());
        carrera.setFacultad(request.getFacultad());
        
        // Guardar cambios
        carreraRepository.save(carrera);
        
        // Preparar respuesta
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Carrera actualizada exitosamente");
        respuesta.put("carrera", carrera.getNombre());
        respuesta.put("facultad", carrera.getFacultad());
        respuesta.put("id", carrera.getId().toString());
        
        return respuesta;
    }
    
    @Override
    public Map<String, String> eliminarCarrera(Long id) {
        // Buscar la carrera por ID
        Optional<Carrera> carreraOptional = carreraRepository.findById(id);
        
        if (carreraOptional.isEmpty()) {
            throw new ResourceNotFoundException("Carrera con ID: " + id + " no encontrada para eliminar.");
        }
        
        // Obtener la carrera para el mensaje
        Carrera carrera = carreraOptional.get();
        String nombreCarrera = carrera.getNombre();
        
        // Eliminar la carrera
        carreraRepository.deleteById(id);
        
        // Preparar respuesta
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Carrera eliminada exitosamente");
        respuesta.put("carrera", nombreCarrera);
        respuesta.put("id", id.toString());
        
        return respuesta;
    }
    
    @Override
    public List<ResponseCarreraDTO> obtenerCarrerasConEstudiantesInscriptos() {
        // Obtener carreras con estudiantes inscriptos
        List<Carrera> carreras = carreraRepository.findCarrerasConEstudiantesInscriptos();
        
        // Convertir cada carrera a un DTO
        //por cada elemento de la lista carreras, creo un responseCarreraDTO y lo agrego a la lista respuesta
        List<ResponseCarreraDTO> respuesta = new ArrayList<>();
        for (Carrera carrera : carreras) {
            //creo un responseCarreraDTO para cada elemento de la lista carreras
            respuesta.add(new ResponseCarreraDTO(carrera));
        }
        // Ordenar por cantidad de inscriptos descendente (de mayor a menor)
        //uso el metodo sort para ordenar la lista respuesta por la cantidad de inscriptos de cada carrera
        respuesta.sort((c1, c2) -> c2.getCantidadInscriptos().compareTo(c1.getCantidadInscriptos()));
        //retorno la lista respuesta ya ordenada por la cantidad de inscriptos de cada carrera
        return respuesta;
    }
    
    @Override
    public List<ReporteCarreraDTO> obtenerReporteCarreras() {
        // Obtener todas las carreras con sus inscripciones, ordenadas alfabéticamente
        List<Carrera> carreras = carreraRepository.findAllConInscripcionesOrdenadasAlfabeticamente();
        
        List<ReporteCarreraDTO> reporte = new ArrayList<>();
        
        // Por cada carrera, procesar sus inscripciones
        for (Carrera carrera : carreras) {
            // Map para contar inscriptos por año
            Map<Integer, Integer> inscriptosPorAnio = new HashMap<>();
            // Map para contar egresados por año
            Map<Integer, Integer> egresadosPorAnio = new HashMap<>();
            
            // Procesar cada inscripción de la carrera
            for (EstudianteCarrera inscripcion : carrera.getEstudiantesInscritos()) {
                // Obtener el año de inscripción
                Calendar cal = Calendar.getInstance();
                cal.setTime(inscripcion.getFechaInscripcion());
                int anioInscripcion = cal.get(Calendar.YEAR);
                
                // Contar inscripto para ese año
                inscriptosPorAnio.put(anioInscripcion, 
                    inscriptosPorAnio.getOrDefault(anioInscripcion, 0) + 1);
                
                // Si está graduado, calcular año estimado de egreso
                if (inscripcion.getGraduado() != null && inscripcion.getGraduado()) {
                    // Año de egreso estimado = año de inscripción + antigüedad
                    int anioEgreso = anioInscripcion + inscripcion.getAntiguedad();
                    
                    // Contar egresado para ese año
                    egresadosPorAnio.put(anioEgreso, 
                        egresadosPorAnio.getOrDefault(anioEgreso, 0) + 1);
                }
            }
            
            // Ordenar los años cronológicamente (de menor a mayor)
            // Crear listas de años ordenadas
            List<Integer> aniosInscriptos = new ArrayList<>(inscriptosPorAnio.keySet());
            Collections.sort(aniosInscriptos);
            
            List<Integer> aniosEgresados = new ArrayList<>(egresadosPorAnio.keySet());
            Collections.sort(aniosEgresados);
            
            // Crear Maps ordenados con LinkedHashMap (mantiene el orden de inserción)
            Map<Integer, Integer> inscriptosOrdenados = new LinkedHashMap<>();
            for (Integer anio : aniosInscriptos) {
                inscriptosOrdenados.put(anio, inscriptosPorAnio.get(anio));
            }
            
            Map<Integer, Integer> egresadosOrdenados = new LinkedHashMap<>();
            for (Integer anio : aniosEgresados) {
                egresadosOrdenados.put(anio, egresadosPorAnio.get(anio));
            }
            
            // Crear el DTO del reporte para esta carrera
            ReporteCarreraDTO reporteCarrera = new ReporteCarreraDTO(
                carrera.getId(),
                carrera.getNombre(),
                carrera.getFacultad(),
                inscriptosOrdenados,
                egresadosOrdenados
            );
            
            reporte.add(reporteCarrera);
        }
        
        return reporte;
    }
    
}


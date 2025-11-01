package com.bidinost.encabo.tp3_arquitectura.service;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.dto.ResponseEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;
import com.bidinost.encabo.tp3_arquitectura.exception.DuplicateResourceException;
import com.bidinost.encabo.tp3_arquitectura.exception.ResourceNotFoundException;
import com.bidinost.encabo.tp3_arquitectura.exception.ValidationException;
import com.bidinost.encabo.tp3_arquitectura.repository.CarreraRepository;
import com.bidinost.encabo.tp3_arquitectura.repository.EstudianteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceEstudianteImpl implements ServiceEstudiante {

    @Autowired
    private EstudianteRepository estudianteRepository;
    
    @Autowired
    private CarreraRepository carreraRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Estudiante crearEstudiante(RequestEstudianteDTO request) {
        // Validar que no exista otro estudiante con el mismo documento
        if (estudianteRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {
            throw new DuplicateResourceException(
                    "Ya existe un estudiante registrado con el número de documento: " + request.getNumeroDocumento() + ". Por favor, verifique sus datos.");
        }

        // Validar que no exista otro estudiante con la misma libreta universitaria
        if (estudianteRepository.existsByNumeroLibretaUniversitaria(request.getNumeroLibretaUniversitaria())) {
            throw new DuplicateResourceException(
                    "Ya existe un estudiante con el número de libreta universitaria: " + request.getNumeroLibretaUniversitaria() + ". Por favor, verifique su número de libreta.");
        }

        // Validaciones adicionales de negocio
        validarDatosEstudiante(request);

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(request.getNombre());
        estudiante.setApellido(request.getApellido());
        estudiante.setEdad(request.getEdad());
        estudiante.setGenero(request.getGenero());
        estudiante.setNumeroDocumento(request.getNumeroDocumento());
        estudiante.setCiudadDeResidencia(request.getCiudadDeResidencia());
        estudiante.setNumeroLibretaUniversitaria(request.getNumeroLibretaUniversitaria());

        return estudianteRepository.save(estudiante);
    }
    //retorna un listado de estudiantes convertido en dto segun un criterio
    //criterio:DESC o ASC
    @Override
    public List<ResponseEstudianteDTO> obtenerTodosLosEstudiantes(String criterio) {
        List<Estudiante> estudiantes;
        if (criterio.equalsIgnoreCase("DESC")) {
            estudiantes = estudianteRepository.obtenerTodosLosEstudiantesDesc();
        } else if (criterio.equalsIgnoreCase("ASC")) {
            estudiantes = estudianteRepository.obtenerTodosLosEstudiantesAsc();
        } else {
            throw new IllegalArgumentException("El criterio debe ser DESC o ASC");
        }
        
        return estudiantes.stream()
                .map(ResponseEstudianteDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEstudianteDTO obtenerEstudiantePorLibreta(Integer numeroLibretaUniversitaria) {
        Estudiante estudiante = estudianteRepository.findByNumeroLibretaUniversitaria(numeroLibretaUniversitaria);
        if (estudiante == null) {
            throw new ResourceNotFoundException(
                    "No se encontró un estudiante con el número de libreta universitaria: " + numeroLibretaUniversitaria);
        }
        return new ResponseEstudianteDTO(estudiante);
    }

    @Override
    public List<ResponseEstudianteDTO> obtenerEstudiantesPorGenero(String genero) {
        // Validar que el género sea válido
        if (!genero.equals("Masculino") && 
            !genero.equals("Femenino") && 
            !genero.equals("Otro")) {
            throw new ValidationException("El género debe ser: Masculino, Femenino u Otro.");
        }
        
        List<Estudiante> estudiantes = estudianteRepository.findByGenero(genero);
        
        return estudiantes.stream()
                .map(ResponseEstudianteDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseEstudianteDTO> obtenerEstudiantesPorCarreraYCiudad(Long carreraId, String ciudadDeResidencia) {
        // Validar que la carrera exista
        if (carreraId == null) {
            throw new ValidationException("El ID de carrera no puede ser nulo.");
        }
        
        if (!carreraRepository.existsById(carreraId)) {
            throw new ResourceNotFoundException("No se encontró una carrera con el ID: " + carreraId);
        }
        
        // Validar que la ciudad de residencia no sea nula o vacía
        if (ciudadDeResidencia == null || ciudadDeResidencia.trim().isEmpty()) {
            throw new ValidationException("La ciudad de residencia no puede estar vacía.");
        }
        
        // Obtener estudiantes de la carrera filtrados por ciudad
        List<Estudiante> estudiantes = estudianteRepository.findEstudiantesPorCarreraYCiudad(carreraId, ciudadDeResidencia);
        
        // Convertir cada estudiante a un DTO
        List<ResponseEstudianteDTO> respuesta = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            respuesta.add(new ResponseEstudianteDTO(estudiante));
        }
        
        return respuesta;
    }

    private void validarDatosEstudiante(RequestEstudianteDTO request) {
        // Validar que la edad sea razonable para un estudiante universitario
        if (request.getEdad() < 18) {
            throw new ValidationException("No se puede registrar un estudiante menor de 18 años en el sistema universitario.");
        }
        
        if (request.getEdad() > 100) {
            throw new ValidationException("La edad ingresada es inválida. Por favor, verifique sus datos.");
        }
        
        // Validar formato del documento
        if (request.getNumeroDocumento() != null && request.getNumeroDocumento().length() < 6) {
            throw new ValidationException("El número de documento debe tener al menos 6 caracteres.");
        }
        
        // Validar que el nombre y apellido no contengan números
        if (request.getNombre() != null && request.getNombre().matches(".*\\d.*")) {
            throw new ValidationException("El nombre no puede contener números.");
        }
        
        if (request.getApellido() != null && request.getApellido().matches(".*\\d.*")) {
            throw new ValidationException("El apellido no puede contener números.");
        }
        
        // Validar género
        if (!request.getGenero().equals("Masculino") && 
            !request.getGenero().equals("Femenino") && 
            !request.getGenero().equals("Otro")) {
            throw new ValidationException("El género debe ser: Masculino, Femenino u Otro.");
        }
    }

}

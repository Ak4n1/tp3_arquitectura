package com.bidinost.encabo.tp3_arquitectura.service;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestEstudianteDTO;
import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;
import com.bidinost.encabo.tp3_arquitectura.exception.DuplicateResourceException;
import com.bidinost.encabo.tp3_arquitectura.exception.ValidationException;
import com.bidinost.encabo.tp3_arquitectura.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceEstudianteImpl implements ServiceEstudiante {

    @Autowired
    private EstudianteRepository estudianteRepository;

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

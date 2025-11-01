package com.bidinost.encabo.tp3_arquitectura.repository;

import com.bidinost.encabo.tp3_arquitectura.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    
    boolean existsByNombre(String nombre);
    
    Optional<Carrera> findByNombre(String nombre);
    
    //query para obtener carreras con estudiantes inscriptos
    @Query("SELECT DISTINCT c FROM Carrera c " +
           "LEFT JOIN FETCH c.estudiantesInscritos " +
           "WHERE SIZE(c.estudiantesInscritos) > 0")
    List<Carrera> findCarrerasConEstudiantesInscriptos();
    
    //query para obtener todas las carreras con sus inscripciones, ordenadas alfabéticamente
    @Query("SELECT DISTINCT c FROM Carrera c " +
           "LEFT JOIN FETCH c.estudiantesInscritos " +
           "ORDER BY c.nombre ASC")
    List<Carrera> findAllConInscripcionesOrdenadasAlfabeticamente();
    
}


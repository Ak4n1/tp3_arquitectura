package com.bidinost.encabo.tp3_arquitectura.repository;

import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
    boolean existsByNumeroDocumento(String numeroDocumento);
    boolean existsByNumeroLibretaUniversitaria(Integer numeroLibretaUniversitaria);
    //dos query para obtener los estudiantes ordenados de manera asc o desc
    @Query("SELECT e FROM Estudiante e ORDER BY e.nombre DESC")
    List<Estudiante> obtenerTodosLosEstudiantesDesc();
    @Query("SELECT e FROM Estudiante e ORDER BY e.nombre ASC")
    List<Estudiante> obtenerTodosLosEstudiantesAsc();
    
}


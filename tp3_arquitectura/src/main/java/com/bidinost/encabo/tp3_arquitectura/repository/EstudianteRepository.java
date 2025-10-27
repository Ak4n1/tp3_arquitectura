package com.bidinost.encabo.tp3_arquitectura.repository;

import com.bidinost.encabo.tp3_arquitectura.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
    boolean existsByNumeroDocumento(String numeroDocumento);
    boolean existsByNumeroLibretaUniversitaria(Integer numeroLibretaUniversitaria);
    
}


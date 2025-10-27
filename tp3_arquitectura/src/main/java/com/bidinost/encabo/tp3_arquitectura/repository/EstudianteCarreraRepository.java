package com.bidinost.encabo.tp3_arquitectura.repository;

import com.bidinost.encabo.tp3_arquitectura.entity.EstudianteCarrera;
import com.bidinost.encabo.tp3_arquitectura.entity.EstudianteCarreraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, EstudianteCarreraId> {
    
}


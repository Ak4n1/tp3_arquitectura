package com.bidinost.encabo.tp3_arquitectura.controller;

import com.bidinost.encabo.tp3_arquitectura.dto.RequestCrearCarreraDTO;
import com.bidinost.encabo.tp3_arquitectura.dto.RequestMatricularEstudiante;
import com.bidinost.encabo.tp3_arquitectura.service.ServiceCarrera;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private ServiceCarrera serviceCarrera;

    @GetMapping
    public List<Map<String, Object>> obtenerTodasLasCarreras() {
        return serviceCarrera.obtenerTodasLasCarreras();
    }

    @PostMapping("/crear")
    public Map<String, String> crearCarrera(@Valid @RequestBody RequestCrearCarreraDTO request) {
        return serviceCarrera.crearCarrera(request);
    }

    @PostMapping("/matricular")
    public Map<String, String> matricularEstudiante(@RequestBody RequestMatricularEstudiante request) {
        return serviceCarrera.matricularEstudiante(request);
    }

    @GetMapping("/{id}")
    public Map<String, Object> obtenerCarreraPorId(@PathVariable Long id) {
        return serviceCarrera.obtenerCarreraPorId(id);
    }

    @PutMapping("/{id}")
    public Map<String, String> actualizarCarrera(@PathVariable Long id, @Valid @RequestBody RequestCrearCarreraDTO request) {
        return serviceCarrera.actualizarCarrera(id, request);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> eliminarCarrera(@PathVariable Long id) {
        return serviceCarrera.eliminarCarrera(id);
    }
}


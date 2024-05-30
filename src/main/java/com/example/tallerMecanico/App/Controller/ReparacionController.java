package com.example.tallerMecanico.App.Controller;

import com.example.tallerMecanico.App.Entity.Reparacion;
import com.example.tallerMecanico.App.Repository.ReparacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {

    @Autowired
    private ReparacionRepository reparacionRepository;

    // Obtener todas las reparaciones
    @GetMapping
    public List<Reparacion> getAllReparaciones() {
        return reparacionRepository.findAll();
    }

    // Obtener una reparación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reparacion> getReparacionById(@PathVariable String id) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(id);
        if (reparacion.isPresent()) {
            return ResponseEntity.ok(reparacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva reparación
    @PostMapping
    public Reparacion createReparacion(@RequestBody Reparacion reparacion) {
        return reparacionRepository.save(reparacion);
    }

    // Actualizar una reparación existente
    @PutMapping("/{id}")
    public ResponseEntity<Reparacion> updateReparacion(@PathVariable String id, @RequestBody Reparacion reparacionDetails) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(id);
        if (reparacion.isPresent()) {
            Reparacion reparacionToUpdate = reparacion.get();
            reparacionToUpdate.setFecha(reparacionDetails.getFecha());
            reparacionToUpdate.setNombreCliente(reparacionDetails.getNombreCliente());
            reparacionToUpdate.setVehiculo(reparacionDetails.getVehiculo());
            reparacionToUpdate.setDescripcion(reparacionDetails.getDescripcion());
            reparacionToUpdate.setMecanico(reparacionDetails.getMecanico());
            reparacionToUpdate.setEstado(reparacionDetails.getEstado());
            reparacionToUpdate.setRepuestos(reparacionDetails.getRepuestos());
            Reparacion updatedReparacion = reparacionRepository.save(reparacionToUpdate);
            return ResponseEntity.ok(updatedReparacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una reparación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReparacion(@PathVariable String id) {
        Optional<Reparacion> reparacion = reparacionRepository.findById(id);
        if (reparacion.isPresent()) {
            reparacionRepository.delete(reparacion.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

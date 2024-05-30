package com.example.tallerMecanico.App.Controller;

import com.example.tallerMecanico.App.Entity.Repuesto;
import com.example.tallerMecanico.App.Repository.RepuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/repuestos")
public class RepuestoController {

    @Autowired
    private RepuestoRepository repuestoRepository;

    // Obtener todos los repuestos
    @GetMapping
    public List<Repuesto> getAllRepuestos() {
        return repuestoRepository.findAll();
    }

    // Obtener un repuesto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> getRepuestoById(@PathVariable String id) {
        Optional<Repuesto> repuesto = repuestoRepository.findById(id);
        if (repuesto.isPresent()) {
            return ResponseEntity.ok(repuesto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo repuesto
    @PostMapping
    public String createRepuesto(@ModelAttribute Repuesto repuesto) {
        repuestoRepository.save(repuesto);
        return "redirect:/admin/repuestos";
    }

    // Actualizar un repuesto existente
    @PostMapping("/{id}")
    public String updateRepuesto(@PathVariable String id, @ModelAttribute Repuesto repuestoDetails) {
        Optional<Repuesto> repuesto = repuestoRepository.findById(id);
        if (repuesto.isPresent()) {
            Repuesto repuestoToUpdate = repuesto.get();
            repuestoToUpdate.setNombre(repuestoDetails.getNombre());
            repuestoToUpdate.setCantidad(repuestoDetails.getCantidad());
            repuestoToUpdate.setPrecio(repuestoDetails.getPrecio());
            Repuesto updatedRepuesto = repuestoRepository.save(repuestoToUpdate);
            return "redirect:/admin/repuestos";
        } else {
            return "redirect:/edit/"+id;
        }
    }

    // Eliminar un repuesto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepuesto(@PathVariable String id) {
        Optional<Repuesto> repuesto = repuestoRepository.findById(id);
        if (repuesto.isPresent()) {
            repuestoRepository.delete(repuesto.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

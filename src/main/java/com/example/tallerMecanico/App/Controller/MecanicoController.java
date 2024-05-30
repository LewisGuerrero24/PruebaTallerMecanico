package com.example.tallerMecanico.App.Controller;

import com.example.tallerMecanico.App.Entity.Mecanico;
import com.example.tallerMecanico.App.Repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/mecanicos")
public class MecanicoController {

    @Autowired
    private MecanicoRepository mecanicoRepository;

    // Obtener todos los mecánicos
    @GetMapping
    public List<Mecanico> getAllMecanicos() {
        return mecanicoRepository.findAll();
    }

    // Obtener un mecánico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mecanico> getMecanicoById(@PathVariable String id) {
        Optional<Mecanico> mecanico = mecanicoRepository.findById(id);
        if (mecanico.isPresent()) {
            return ResponseEntity.ok(mecanico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo mecánico
    @PostMapping
    public String createMecanico(@ModelAttribute Mecanico mecanico) {
        mecanicoRepository.save(mecanico);
        return "redirect:/mecanicos";
    }

    // Actualizar un mecánico existente
    @PostMapping("/{id}")
    public String updateMecanico(@PathVariable String id, @ModelAttribute Mecanico mecanicoDetails) {
        Optional<Mecanico> mecanico = mecanicoRepository.findById(id);
        if (mecanico.isPresent()) {
            Mecanico mecanicoToUpdate = mecanico.get();
            mecanicoToUpdate.setNombre(mecanicoDetails.getNombre());
            mecanicoToUpdate.setEspecialidad(mecanicoDetails.getEspecialidad());
            Mecanico updatedMecanico = mecanicoRepository.save(mecanicoToUpdate);
            return "redirect:/mecanicos";
        } else {
            return "redirect:/mecanicos/edit/"+id;
        }
    }

    // Eliminar un mecánico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMecanico(@PathVariable String id) {
        Optional<Mecanico> mecanico = mecanicoRepository.findById(id);
        if (mecanico.isPresent()) {
            mecanicoRepository.delete(mecanico.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
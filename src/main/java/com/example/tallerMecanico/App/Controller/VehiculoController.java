package com.example.tallerMecanico.App.Controller;

import com.example.tallerMecanico.App.Entity.Vehiculo;
import com.example.tallerMecanico.App.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping
    public String getAllVehiculos(Model model) {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        model.addAttribute("vehiculos", vehiculos);
        return "ListVehiculos";
    }

    @GetMapping("/{id}")
    public String getAllVehiculosMecanico(@PathVariable("id") String id,Model model) {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        model.addAttribute("IdMecanico", id);
        model.addAttribute("vehiculos", vehiculos);
        return "ListVehiculosMecanico";
    }
    @GetMapping("/new")
    public String newVehiculoForm(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculo_form";
    }

    @GetMapping("/new/mecanico/{id}")
    public String newVehiculoMecanicoForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("idmecanico", id);
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculo_form_mecanico";
    }

    @PostMapping
    public String createVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos";
    }

    @PostMapping("/mecanico/{idmecanico}")
    public String createVehiculo(@PathVariable("idmecanico")String idmecanico, @ModelAttribute("vehiculo") Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos/"+idmecanico;
    }

    @GetMapping("/edit/{id}/mecanico/{idmecanico}")
    public String editVehiculoForm(@PathVariable String id,@PathVariable("idmecanico") String idmecanico, Model model) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            model.addAttribute("vehiculo", vehiculo.get());
            model.addAttribute("idmecanico",idmecanico);
            return "vehiculo_form_mecanico";
        } else {
            return "redirect:/vehiculos/"+idmecanico;
        }
    }

    @PostMapping("/update/{id}")
    public String updateVehiculo(@PathVariable String id, @ModelAttribute("vehiculo") Vehiculo vehiculoDetails) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            Vehiculo vehiculoToUpdate = vehiculo.get();
            vehiculoToUpdate.setMarca(vehiculoDetails.getMarca());
            vehiculoToUpdate.setModelo(vehiculoDetails.getModelo());
            vehiculoToUpdate.setAño(vehiculoDetails.getAño());
            vehiculoToUpdate.setMatricula(vehiculoDetails.getMatricula());
            vehiculoRepository.save(vehiculoToUpdate);
        }
        return "redirect:/vehiculos";
    }

    @PostMapping("/update/{id}/mecanico/{idmecanico}")
    public String updateVehiculo(@PathVariable String id,@PathVariable("idmecanico") String idmecanico, @ModelAttribute("vehiculo") Vehiculo vehiculoDetails) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            Vehiculo vehiculoToUpdate = vehiculo.get();
            vehiculoToUpdate.setMarca(vehiculoDetails.getMarca());
            vehiculoToUpdate.setModelo(vehiculoDetails.getModelo());
            vehiculoToUpdate.setAño(vehiculoDetails.getAño());
            vehiculoToUpdate.setMatricula(vehiculoDetails.getMatricula());
            vehiculoRepository.save(vehiculoToUpdate);
        }
        return "redirect:/vehiculos/"+idmecanico;
    }

    @GetMapping("/delete/{id}")
    public String deleteVehiculo(@PathVariable String id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            vehiculoRepository.delete(vehiculo.get());
        }
        return "redirect:/vehiculos";
    }

    @GetMapping("/delete/{id}/mecanico/{idmecanico}")
    public String deleteVehiculo(@PathVariable String id, @PathVariable("idmecanico") String idmecanico) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        if (vehiculo.isPresent()) {
            vehiculoRepository.delete(vehiculo.get());
        }
        return "redirect:/vehiculos/"+idmecanico;
    }
}


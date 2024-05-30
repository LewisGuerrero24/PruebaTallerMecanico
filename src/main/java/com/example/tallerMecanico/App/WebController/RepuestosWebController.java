package com.example.tallerMecanico.App.WebController;


import com.example.tallerMecanico.App.Entity.Repuesto;
import com.example.tallerMecanico.App.Repository.RepuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class RepuestosWebController {

    @Autowired
    private RepuestoRepository repuestoRepository;
    @GetMapping("/admin/repuestos")
    public String getAllRepuestos(Model model) {
        List<Repuesto> repuestos = repuestoRepository.findAll();
        model.addAttribute("repuestos", repuestos);
        return "ListRepuestos";
    }

    @GetMapping("/repuestos/{idmecanico}")
    public String getAllMecanicoRepuestos(@PathVariable("idmecanico") String idmecanico, Model model) {

        List<Repuesto> repuestos = repuestoRepository.findAll();
        model.addAttribute("IdUser",idmecanico);
        model.addAttribute("repuestos", repuestos);
        return "ListRepuestosMecanico";
    }

    @GetMapping("/repuestos/new")
    public String newRepuestoForm(Model model) {
        model.addAttribute("repuesto", new Repuesto());
        return "repuesto_form";
    }
    @GetMapping("/repuestos/edit/{id}")
    public String editRepuestoForm(@PathVariable String id, Model model) {
        Optional<Repuesto> repuesto = repuestoRepository.findById(id);
        if (repuesto.isPresent()) {
            model.addAttribute("repuesto", repuesto.get());
            return "repuesto_form";
        } else {
            return "redirect:/repuestos";
        }
    }

    @GetMapping("/repuestos/delete/{id}")
    public String deleteRepuesto(@PathVariable String id) {
        Optional<Repuesto> repuesto = repuestoRepository.findById(id);
        if (repuesto.isPresent()) {
            repuestoRepository.delete(repuesto.get());
        }
        return "redirect:/admin/repuestos";
    }
}

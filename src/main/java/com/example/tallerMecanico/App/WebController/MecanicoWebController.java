package com.example.tallerMecanico.App.WebController;

import com.example.tallerMecanico.App.Entity.Mecanico;
import com.example.tallerMecanico.App.Repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mecanicos")
public class MecanicoWebController {

    @Autowired
    private MecanicoRepository mecanicoRepository;

    @GetMapping
    public String getAllMecanicos(Model model) {
        List<Mecanico> mecanicos = mecanicoRepository.findAll();
        model.addAttribute("mecanicos", mecanicos);
        return "ListMecanicos";
    }

    @GetMapping("/new")
    public String newMecanicoForm(Model model) {
        model.addAttribute("mecanico", new Mecanico());
        return "mecanico_form";
    }

    @GetMapping("/edit/{id}")
    public String editMecanicoForm(@PathVariable String id, Model model) {
        Optional<Mecanico> mecanico = mecanicoRepository.findById(id);
        if (mecanico.isPresent()) {
            model.addAttribute("mecanico", mecanico.get());
            return "mecanico_form";
        } else {
            return "redirect:/mecanicos";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteMecanico(@PathVariable String id) {
        Optional<Mecanico> mecanico = mecanicoRepository.findById(id);
        if (mecanico.isPresent()) {
            mecanicoRepository.delete(mecanico.get());
        }
        return "redirect:/mecanicos";
    }

}

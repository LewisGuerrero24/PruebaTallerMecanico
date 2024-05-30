package com.example.tallerMecanico.App.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/nosotros")
    public String Nosotros(Model model){
        return "nosotros";
    }


}

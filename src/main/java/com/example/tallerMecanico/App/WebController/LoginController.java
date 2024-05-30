package com.example.tallerMecanico.App.WebController;



import com.example.tallerMecanico.App.Entity.Rol;
import com.example.tallerMecanico.App.Entity.User;
import com.example.tallerMecanico.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private UserRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        return "Login";
    }



    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {

        User usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getPassword().equals(password)) {

            if (usuario.getRol() == Rol.ADMINISTRADOR) {

                return "redirect:/admin/users";
            } else if (usuario.getRol() == Rol.MECANICO) {

                return "redirect:/reparaciones/mecanico/"+usuario.getId();
            } else {

                return "redirect:/";
            }
        }else{
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
}


package com.example.tallerMecanico.App.WebController;

import com.example.tallerMecanico.App.Entity.User;
import com.example.tallerMecanico.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UsersWebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public String index(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "listaAdminUsers";
    }

    @GetMapping("/admin/user/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @GetMapping("/user/edit/{id}")
    public String editUserForm(@PathVariable String id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user_form";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/admin/users";
    }

}

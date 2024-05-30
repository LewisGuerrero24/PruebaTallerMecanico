package com.example.tallerMecanico.App.Controller;

import com.example.tallerMecanico.App.Entity.User;
import com.example.tallerMecanico.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo usuario
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    // Actualizar un usuario existente
    @PostMapping("/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userToUpdate = user.get();
            userToUpdate.setNombres(userDetails.getNombres());
            userToUpdate.setApellidos(userDetails.getApellidos());
            userToUpdate.setTelefono(userDetails.getTelefono());
            userToUpdate.setEmail(userDetails.getEmail());
            userToUpdate.setPassword(userDetails.getPassword());
            userToUpdate.setRol(userDetails.getRol());
            User updatedUser = userRepository.save(userToUpdate);
            return "redirect:/admin/users";
        } else {
            return "redirect:/user/edit/"+id;
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

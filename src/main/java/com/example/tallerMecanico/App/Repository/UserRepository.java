package com.example.tallerMecanico.App.Repository;

import com.example.tallerMecanico.App.Entity.Rol;
import com.example.tallerMecanico.App.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    List<User> findByRol(Rol rol);
}


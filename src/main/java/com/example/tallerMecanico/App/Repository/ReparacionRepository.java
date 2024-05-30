package com.example.tallerMecanico.App.Repository;

import com.example.tallerMecanico.App.Entity.Reparacion;
import com.example.tallerMecanico.App.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReparacionRepository extends MongoRepository<Reparacion, String> {

    List<Reparacion> findByMecanico(User mecanico);
}

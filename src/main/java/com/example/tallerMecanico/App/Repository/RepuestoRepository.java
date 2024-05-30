package com.example.tallerMecanico.App.Repository;

import com.example.tallerMecanico.App.Entity.Repuesto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepuestoRepository extends MongoRepository<Repuesto, String> {
}

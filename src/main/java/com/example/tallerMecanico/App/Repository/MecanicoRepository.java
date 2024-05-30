package com.example.tallerMecanico.App.Repository;

import com.example.tallerMecanico.App.Entity.Mecanico;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MecanicoRepository extends MongoRepository<Mecanico, String> {
}
package com.example.tallerMecanico.App.Repository;

import com.example.tallerMecanico.App.Entity.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
}

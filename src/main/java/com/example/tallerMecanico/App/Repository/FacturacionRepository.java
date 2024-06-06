package com.example.tallerMecanico.App.Repository;

import com.example.tallerMecanico.App.Entity.Facturacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacturacionRepository extends MongoRepository<Facturacion, String> {
}

package com.example.tallerMecanico.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Facturas")
public class Facturacion {

    @Id
    private String id;
    private String idReparacion;
    private double total;
    private boolean pagada;
    private List<Repuesto> repuestos; // Agregar la lista de repuestos

    public Facturacion() {
    }

    public Facturacion(String id, String idReparacion, double total, boolean pagada, List<Repuesto> repuestos) {
        this.id = id;
        this.idReparacion = idReparacion;
        this.total = total;
        this.pagada = pagada;
        this.repuestos = repuestos;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(String idReparacion) {
        this.idReparacion = idReparacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<Repuesto> repuestos) {
        this.repuestos = repuestos;
    }

    // Método para calcular el total basado en los repuestos
    public double calcularTotal(List<Repuesto> repuestos) {
        double total = 0.0;
        for (Repuesto repuesto : repuestos) {
            total += repuesto.getPrecio() * 1;
        }
        return total;
    }
}

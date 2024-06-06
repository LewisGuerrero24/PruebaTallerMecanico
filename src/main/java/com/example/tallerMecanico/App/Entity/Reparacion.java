package com.example.tallerMecanico.App.Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "Reparaciones")
public class Reparacion {
	
	  	@Id
	    private String id;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate fecha;
	    private String NombreCliente;
	    private Vehiculo vehiculo;
	    private String descripcion;
	    private User mecanico;
	    private String estado;

		@DBRef
	    private List<Repuesto> Repuestos;

	public Reparacion() {
	}

	public Reparacion(String id, LocalDate fecha, String nombreCliente, Vehiculo vehiculo, String descripcion, User mecanico, String estado, List<Repuesto> repuestos) {
		this.id = id;
		this.fecha = fecha;
		NombreCliente = nombreCliente;
		this.vehiculo = vehiculo;
		this.descripcion = descripcion;
		this.mecanico = mecanico;
		this.estado = estado;
		Repuestos = repuestos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getNombreCliente() {
		return NombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		NombreCliente = nombreCliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public User getMecanico() {
		return mecanico;
	}

	public void setMecanico(User mecanico) {
		this.mecanico = mecanico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Repuesto> getRepuestos() {
		return Repuestos;
	}

	public void setRepuestos(List<Repuesto> repuestos) {
		Repuestos = repuestos;
	}
}

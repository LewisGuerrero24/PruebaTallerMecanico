package com.example.tallerMecanico.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Mecanicos")
public class Mecanico {
	
	@Id
	private String id;
	
	private String nombre;
	
	private String especialidad;

	public Mecanico() {
	}

	public Mecanico(String id, String nombre, String especialidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.especialidad = especialidad;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	
}

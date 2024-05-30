package com.example.tallerMecanico.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Vehiculos")
public class Vehiculo {
	
	@Id
	private String id;
    private String marca;
    private String modelo;
    private int año;
    private String matricula;

	public Vehiculo() {
	}

	public Vehiculo(String id, String marca, String modelo, int año, String matricula) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
		this.matricula = matricula;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
    
    

}

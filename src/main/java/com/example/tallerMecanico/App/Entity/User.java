package com.example.tallerMecanico.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Users")
public class User {
	 	@Id
	    private String id;

	    private String nombres;

	    private String apellidos;

	    private String telefono;

	    private String email;

	    private String password;

	    private Rol rol;

	public User() {
	}

	public User(String id, String nombres, String apellidos, String telefono, String email, String password,
				Rol rol) {
			super();
			this.id = id;
			this.nombres = nombres;
			this.apellidos = apellidos;
			this.telefono = telefono;
			this.email = email;
			this.password = password;
			this.rol = rol;
		}


	public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNombres() {
			return nombres;
		}

		public void setNombres(String nombres) {
			this.nombres = nombres;
		}

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Rol getRol() {
			return rol;
		}

		public void setRol(Rol rol) {
			this.rol = rol;
		}
	    
	    
	    
}

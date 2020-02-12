package com.labcivil.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "estudiantes")
public class Estudiante implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Pattern(regexp = "^[a-zA-ZÑñáéíóúÁÉÍÓÚ ]*$")
	private String nombre;
	
	@NotEmpty
	@Pattern(regexp = "^[A-ZÑÁÉÍÓÚ]{1}[a-zñáéíóú]*\\s[A-ZÑÁÉÍÓÚ]{1}[a-zñáéíóú ]*$")
	private String apellido;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Size(min=10, max = 10)
	@Pattern(regexp = "^[0-9]{10}$")
	private String cedula;
	
	@NotEmpty
	@Size(min=10, max = 10)
	@Pattern(regexp = "^09[0-9]{8}$")
	private String telefono;
	
	private String foto;
	
	private int numprestamo;
	
	@OneToMany( mappedBy="estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@JsonBackReference
	private List<Registro> registros;
	
	public Estudiante() {
		registros = new ArrayList<Registro>();
		this.numprestamo = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}		

	public int getNumprestamo() {
		return numprestamo;
	}

	public void setNumprestamo(int numprestamo) {
		this.numprestamo = numprestamo;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
	
	public void addRegistro(Registro registro) {
		registros.add(registro);
	}
	
}

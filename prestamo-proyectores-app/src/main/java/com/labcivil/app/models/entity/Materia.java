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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "materias")
public class Materia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String codigo;
	
	@NotNull
	private int creditos;
		
	@NotNull
	private int semestre;
	
	@NotNull
	private int paralelo;
	
	private int numprestamo;
	
	@OneToMany(mappedBy="materia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private List<Registro> registros;
	
	public Materia() {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getParalelo() {
		return paralelo;
	}

	public void setParalelo(int paralelo) {
		this.paralelo = paralelo;
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

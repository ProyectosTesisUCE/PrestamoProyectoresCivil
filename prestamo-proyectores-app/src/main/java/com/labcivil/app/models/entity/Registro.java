package com.labcivil.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "registros")
public class Registro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	
	@Column(name = "email_es")
	private String emailEs;
	
	@NotEmpty
	private String horario;

	private String observacion;
	
	@Column(name = "create_re")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date createRe;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Estudiante estudiante;
		
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="profesor_id")
	@JsonBackReference
	private Profesor profesor;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="materia_id")
	@JsonBackReference
	private Materia materia;
	
	private String estado;
	
	@OneToMany(mappedBy="registro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@JsonBackReference
	private List<Prestamo> prestamos;
	
	public Registro() {
		prestamos = new ArrayList<Prestamo>();
	}
	
	@PrePersist
	public void prePersist() {
		createRe = new Date();
		estado = "Activo";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmailEs() {
		return emailEs;
	}

	public void setEmailEs(String emailEs) {
		this.emailEs = emailEs;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateRe() {
		return createRe;
	}

	public void setCreateRe(Date createRe) {
		this.createRe = createRe;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}	
	
	public void addPrestamo(Prestamo prestamo) {
		prestamos.add(prestamo);
	}
	
}

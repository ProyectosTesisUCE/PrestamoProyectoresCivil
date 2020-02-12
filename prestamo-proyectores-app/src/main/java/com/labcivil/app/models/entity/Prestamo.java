package com.labcivil.app.models.entity;

import java.io.Serializable;
//import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String aula;
	
	@Column(name = "create_pr")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date createPr;
	
	@Column(name = "hora_in")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="HH:mm")
	private Date horaIn;
	
	@Column(name = "hora_fn")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="HH:mm")
	private Date horaFn;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="registro_id")
	private Registro registro;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="proyector_id") //Comentar si falla la app NO es necesario antes de hacer grafica
	private Proyector proyector;
	
	private String estado;
	
	private String observacion;

	@PrePersist
	public void prePersist() {
		createPr = new Date();
		horaIn = new Date();
		estado = "Activo";		
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public Date getCreatePr() {
		return createPr;
	}

	public void setCreatePr(Date createPr) {
		this.createPr = createPr;
	}

	public Date getHoraIn() {
		return horaIn;
	}

	public void setHoraIn(Date horaIn) {
		this.horaIn = horaIn;
	}

	public Date getHoraFn() {
		return horaFn;
	}

	public void setHoraFn(Date horaFn) {
		this.horaFn = horaFn;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public Proyector getProyector() {
		return proyector;
	}

	public void setProyector(Proyector proyector) {
		this.proyector = proyector;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", aula=" + aula + ", createPr=" + createPr + ", horaIn=" + horaIn + ", horaFn="
				+ horaFn + ", registro=" + registro + ", proyector=" + proyector + ", estado=" + estado
				+ ", observacion=" + observacion + "]";
	}
	
}

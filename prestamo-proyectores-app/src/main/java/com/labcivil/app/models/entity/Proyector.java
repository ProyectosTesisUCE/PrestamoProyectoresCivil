package com.labcivil.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "proyectores")
public class Proyector implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty //Validar de tipo String (No este vacio el campo)
	private String alta;
	
	@NotEmpty //@NotNull / @Email --> para fechas 
	private String marca;
	
	@NotEmpty
	private String modelo;
	
	@NotEmpty
	private String serie;
	
	@NotEmpty
	private String color;
	
	private String foto;
	
	@NotEmpty
	private String estatus;
	
	private int numprestamo;
	
	private int numhours;
	
	@Column(name = "hours_used")
	private String hoursUsed;
	
	@OneToMany(mappedBy="proyector", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private List<Prestamo> prestamos;
	
	public Proyector() {
		prestamos = new ArrayList<Prestamo>();
		this.numprestamo = 0;
		this.numhours = 0;
		this.hoursUsed = "00:00:00";
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlta() {
		return alta;
	}

	public void setAlta(String alta) {
		this.alta = alta;
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

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	public int getNumprestamo() {
		return numprestamo;
	}

	public void setNumprestamo(int numprestamo) {
		this.numprestamo = numprestamo;
	}		

	public int getNumhours() {
		return numhours;
	}

	public void setNumhours(int numhours) {
		this.numhours = numhours;
	}

	public String getHoursUsed() {
		return hoursUsed;
	}

	public void setHoursUsed(String hoursUsed) {
		this.hoursUsed = hoursUsed;
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


	@Override
	public String toString() {
		return "Proyector [id=" + id + ", alta=" + alta + ", marca=" + marca + ", modelo=" + modelo + ", serie=" + serie
				+ ", color=" + color + ", foto=" + foto + ", estatus=" + estatus + ", numprestamo=" + numprestamo
				+ ", numhours=" + numhours + ", hoursUsed=" + hoursUsed + ", prestamos=" + prestamos + "]";
	}

}

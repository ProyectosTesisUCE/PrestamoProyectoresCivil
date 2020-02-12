package com.labcivil.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.labcivil.app.models.entity.Estudiante;
import com.labcivil.app.models.entity.Materia;
import com.labcivil.app.models.entity.Profesor;
import com.labcivil.app.models.entity.Registro;


public interface IRegistroService {
	
	public List<Registro> findAll();
	
	public List<Registro> findAllOrder();
	
	public Page<Registro> findAll(Pageable pageable);
	
	public void save(Registro registro);
	
	public Registro findOne(Long id);
	
	public void delete(Long id);
	
	public List<Estudiante> findByApellidoE(String term);
	
	public Estudiante findEstudianteById(Long id);
	
	public List<Profesor> findByApellidoP(String term);
	
	public Profesor findProfesorById(Long id);
	
	public List<Materia> findByNombreM(String term);
	
	public Materia findMateriaById(Long id);

}

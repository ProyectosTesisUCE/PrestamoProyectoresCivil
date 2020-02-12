package com.labcivil.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.labcivil.app.models.dto.ProfPreDto;
import com.labcivil.app.models.entity.Profesor;

public interface IProfesorService {
	
	public List<Profesor> findAll();
	
	public List<Profesor> findAllOrder();
	
	public Page<Profesor> findAll(Pageable pageable);
	
	public void save(Profesor profesor);
	
	public Profesor findOne(Long id);
	
	public void delete(Long id);
	
	public List<ProfPreDto> findGrafica1(Date startDate, Date endDate);

}

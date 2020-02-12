package com.labcivil.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.labcivil.app.models.dto.MatPreDto;
import com.labcivil.app.models.entity.Materia;

public interface IMateriaService {
	
	public List<Materia> findAll();
	
	public List<Materia> findAllOrder();
	
	public Page<Materia> findAll(Pageable pageable);
	
	public void save(Materia materia);
	
	public Materia findOne(Long id);
	
	public void delete(Long id);
	
	public List<MatPreDto> findGrafica1(Date startDate, Date endDate);

}

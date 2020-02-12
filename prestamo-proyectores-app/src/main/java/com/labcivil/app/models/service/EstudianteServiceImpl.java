package com.labcivil.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labcivil.app.models.dao.IEstudianteDao;
import com.labcivil.app.models.dao.IPrestamoDao;
import com.labcivil.app.models.dto.EstPreDto;
import com.labcivil.app.models.entity.Estudiante;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteDao estudianteDao;
	
	@Autowired
	private IPrestamoDao prestamoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Estudiante> findAll() {
		return estudianteDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Estudiante> findAll(Pageable pageable) {
		return estudianteDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Estudiante estudiante) {
		estudianteDao.save(estudiante);
	}

	@Override
	@Transactional(readOnly=true)
	public Estudiante findOne(Long id) {
		return estudianteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		estudianteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Estudiante> findAllOrder() {
		return estudianteDao.findByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly=true)
	public List<EstPreDto> findGrafica1(Date startDate, Date endDate) {
		return prestamoDao.fetchPreEstDataInnerJoin(startDate, endDate);
	}

}

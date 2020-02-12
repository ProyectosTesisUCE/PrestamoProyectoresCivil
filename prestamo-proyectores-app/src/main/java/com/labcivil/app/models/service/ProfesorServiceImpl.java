package com.labcivil.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labcivil.app.models.dao.IPrestamoDao;
import com.labcivil.app.models.dao.IProfesorDao;
import com.labcivil.app.models.dto.ProfPreDto;
import com.labcivil.app.models.entity.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	private IProfesorDao profesorDao;
	
	@Autowired
	private IPrestamoDao prestamoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Profesor> findAll() {
		return profesorDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Profesor> findAll(Pageable pageable) {
		return profesorDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Profesor profesor) {
		profesorDao.save(profesor);
	}

	@Override
	@Transactional(readOnly=true)
	public Profesor findOne(Long id) {
		return profesorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		profesorDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Profesor> findAllOrder() {
		return profesorDao.findByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly=true)
	public List<ProfPreDto> findGrafica1(Date startDate, Date endDate) {
		return prestamoDao.fetchPreProfDataInnerJoin(startDate, endDate);
	}

}

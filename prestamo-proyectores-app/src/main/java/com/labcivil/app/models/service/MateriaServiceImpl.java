package com.labcivil.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labcivil.app.models.dao.IMateriaDao;
import com.labcivil.app.models.dao.IPrestamoDao;
import com.labcivil.app.models.dto.MatPreDto;
import com.labcivil.app.models.entity.Materia;

@Service
public class MateriaServiceImpl implements IMateriaService {
	
	@Autowired
	private IMateriaDao materiaDao;
	
	@Autowired
	private IPrestamoDao prestamoDao;

	@Override
	@Transactional(readOnly=true)
	public List<Materia> findAll() {
		
		return materiaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Materia materia) {
	
		materiaDao.save(materia);
	}

	@Override
	@Transactional(readOnly=true)
	public Materia findOne(Long id) {
		
		return materiaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		materiaDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly=true)
	public Page<Materia> findAll(Pageable pageable) {
		
		return materiaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Materia> findAllOrder() {
		return materiaDao.findByOrderByIdAsc();
	}

	@Override
	public List<MatPreDto> findGrafica1(Date startDate, Date endDate) {
		return prestamoDao.fetchPreMatDataInnerJoin(startDate, endDate);
	}

}

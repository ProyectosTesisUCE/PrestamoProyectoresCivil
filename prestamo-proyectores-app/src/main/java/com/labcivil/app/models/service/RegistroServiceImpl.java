package com.labcivil.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labcivil.app.models.dao.IEstudianteDao;
import com.labcivil.app.models.dao.IMateriaDao;
import com.labcivil.app.models.dao.IProfesorDao;
import com.labcivil.app.models.dao.IRegistroDao;
import com.labcivil.app.models.entity.Estudiante;
import com.labcivil.app.models.entity.Materia;
import com.labcivil.app.models.entity.Profesor;
import com.labcivil.app.models.entity.Registro;

@Service
public class RegistroServiceImpl implements IRegistroService {
	
	@Autowired
	private IRegistroDao registroDao;
	
	@Autowired
	private IEstudianteDao estudianteDao;
	
	@Autowired
	private IProfesorDao profesorDao;
	
	@Autowired
	private IMateriaDao materiaDao;

	@Override
	@Transactional(readOnly=true)
	public List<Registro> findAll() {
		
		return registroDao.findAll();
	}

	@Override
	@Transactional
	public void save(Registro registro) {
	
		registroDao.save(registro);
	}

	@Override
	@Transactional(readOnly=true)
	public Registro findOne(Long id) {		
		return registroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		registroDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Registro> findAll(Pageable pageable) {
		
		return registroDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Estudiante> findByApellidoE(String term) {
		
		return estudianteDao.findByApellidoE(term);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Estudiante findEstudianteById(Long id) {
		return estudianteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Profesor> findByApellidoP(String term) {
		
		return profesorDao.findByApellidoP(term);
	}

	@Override
	@Transactional(readOnly=true)
	public Profesor findProfesorById(Long id) {
		return profesorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Materia> findByNombreM(String term) {
		return materiaDao.findByNombreM(term);
	}

	@Override
	@Transactional(readOnly=true)
	public Materia findMateriaById(Long id) {
		return materiaDao.findById(id).orElse(null);
	}

	@Override
	public List<Registro> findAllOrder() {
	
		return registroDao.findAllOrder();
	}

}

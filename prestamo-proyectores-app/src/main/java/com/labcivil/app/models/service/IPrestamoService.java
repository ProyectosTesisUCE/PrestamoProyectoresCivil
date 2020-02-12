package com.labcivil.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.labcivil.app.models.entity.Prestamo;
import com.labcivil.app.models.entity.Proyector;
//import com.labcivil.app.models.entity.Proyector;
import com.labcivil.app.models.entity.Registro;

public interface IPrestamoService {
	
	public List<Prestamo> findAll();
	
	public List<Prestamo> findAllP();
	
	public List<Prestamo> findAllEstadoAct();
	
	public List<Prestamo> findAllEstadoTer();
	
	public List<Prestamo> findAllBetween(Date fecha1, Date fecha2);
	
	public List<Prestamo> findAllBetweenTime(Date fecha, Date time1, Date time2);
	
	public Page<Prestamo> findAll(Pageable pageable);
	
	public void save(Prestamo prestamo);
	
	public Prestamo findOne(Long id);
	
	public void delete(Long id);
	
	public void send(String fromAddress, String toAddress, String subject, String content) throws Exception;
	
	public List<Registro> findByDescripcion(String term);
	
	public Registro findRegistroById(Long id);
	
	public List<Proyector> findByEstatusAndAlta(String term);
	
	public Proyector findProyectorById(Long id);

}

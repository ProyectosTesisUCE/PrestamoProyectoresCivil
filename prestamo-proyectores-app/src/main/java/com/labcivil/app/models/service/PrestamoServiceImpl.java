package com.labcivil.app.models.service;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labcivil.app.models.dao.IPrestamoDao;
import com.labcivil.app.models.dao.IProyectorDao;
import com.labcivil.app.models.dao.IRegistroDao;
import com.labcivil.app.models.entity.Prestamo;
import com.labcivil.app.models.entity.Proyector;
import com.labcivil.app.models.entity.Registro;

@Service
public class PrestamoServiceImpl implements IPrestamoService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private IPrestamoDao prestamoDao;
	
	@Autowired
	private IRegistroDao registroDao;
	
	@Autowired
	private IProyectorDao proyectorDao;

	@Override
	@Transactional(readOnly=true)
	public List<Prestamo> findAll() {
		
		return prestamoDao.findAll();
	}
 
	@Override
	@Transactional
	public void save(Prestamo prestamo) {
	
		prestamoDao.save(prestamo);
	}

	@Override
	@Transactional(readOnly=true)
	public Prestamo findOne(Long id) {
		
		return prestamoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		prestamoDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly=true)
	public Page<Prestamo> findAll(Pageable pageable) {
		
		return prestamoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Registro> findByDescripcion(String term) {
		return registroDao.findByDescripcion(term);
	}

	@Override
	@Transactional(readOnly=true)
	public Registro findRegistroById(Long id) {
		return registroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Proyector> findByEstatusAndAlta(String term) {
		return proyectorDao.findByEstatusAndAlta(term);
	}

	@Override
	@Transactional(readOnly=true)
	public Proyector findProyectorById(Long id) {
		return proyectorDao.findById(id).orElse(null);
	}

	@Override
	public void send(String fromAddress, String toAddress, String subject, String content) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setFrom(fromAddress);
		mimeMessageHelper.setTo(toAddress);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(content, true);
		mimeMessageHelper.setSentDate(new Date());
		
		javaMailSender.send(mimeMessage);		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Prestamo> findAllP() {
		return prestamoDao.findByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Prestamo> findAllEstadoAct() {
		return prestamoDao.findByEstadoOrderByIdAsc("Activo");
	}

	@Override
	@Transactional(readOnly=true)
	public List<Prestamo> findAllEstadoTer() {		
		return prestamoDao.findByEstadoOrderByIdAsc("Terminado");
	}

	@Override
	@Transactional
	public List<Prestamo> findAllBetween(Date fecha1, Date fecha2) {
		
		return prestamoDao.findByCreatePrBetweenOrderByIdAsc(fecha1, fecha2);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Prestamo> findAllBetweenTime(Date fecha, Date time1, Date time2) {
		return prestamoDao.findByCreatePrAndHoraInBetweenOrderByIdAsc(fecha, time1, time2);
	}
}

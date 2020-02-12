package com.labcivil.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.labcivil.app.models.entity.Registro;

@Repository
public interface IRegistroDao extends JpaRepository<Registro, Long>{
	
	@Query("select r from Registro r where r.estado='Activo' and r.descripcion like %?1% order by r.id")	
	public List<Registro> findByDescripcion(String term);
	
	@Query("select r from Registro r order by r.id")
	public List<Registro> findAllOrder();

}

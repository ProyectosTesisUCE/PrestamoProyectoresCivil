package com.labcivil.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.labcivil.app.models.entity.Profesor;

@Repository
public interface IProfesorDao extends JpaRepository<Profesor, Long>{
	
	@Query("select p from Profesor p where p.apellido like %?1% order by p.id")
	public List<Profesor> findByApellidoP(String term);

	public List<Profesor> findByOrderByIdAsc();
}

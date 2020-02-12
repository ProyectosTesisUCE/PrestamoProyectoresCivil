package com.labcivil.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.labcivil.app.models.entity.Estudiante;

@Repository
public interface IEstudianteDao extends JpaRepository<Estudiante, Long>{	
	
	@Query("select e from Estudiante e where e.apellido like %?1% order by e.id")
	public List<Estudiante> findByApellidoE(String term);
	
	public List<Estudiante> findByOrderByIdAsc();
	
}

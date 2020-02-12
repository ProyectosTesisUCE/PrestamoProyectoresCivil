package com.labcivil.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.labcivil.app.models.entity.Materia;

@Repository
public interface IMateriaDao extends JpaRepository<Materia, Long>{
	
	@Query("select m from Materia m where m.nombre like %?1% order by m.id")
	public List<Materia> findByNombreM(String term);

	public List<Materia> findByOrderByIdAsc();

}

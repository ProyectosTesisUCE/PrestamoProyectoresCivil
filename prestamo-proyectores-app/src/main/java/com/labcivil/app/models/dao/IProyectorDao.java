package com.labcivil.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.labcivil.app.models.dto.ProyEstatusDto;
import com.labcivil.app.models.entity.Proyector;

@Repository
public interface IProyectorDao extends JpaRepository<Proyector, Long> {

	@Query("select p from Proyector p where p.estatus='Disponible' and p.alta like %?1%")
	public List<Proyector> findByEstatusAndAlta(String term);

	public List<Proyector> findByOrderByIdAsc();
	
	@Query("SELECT new com.labcivil.app.models.dto.ProyEstatusDto (p.estatus, count(*)) "
			+ "FROM Proyector p group by p.estatus order by p.estatus")
	public List<ProyEstatusDto> estatusProyData();
	
}

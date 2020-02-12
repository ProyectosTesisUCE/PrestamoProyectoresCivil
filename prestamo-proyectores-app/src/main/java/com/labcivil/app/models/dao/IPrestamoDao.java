package com.labcivil.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.labcivil.app.models.dto.EstPreDto;
import com.labcivil.app.models.dto.MatPreDto;
import com.labcivil.app.models.dto.ProfPreDto;
import com.labcivil.app.models.entity.Prestamo;
import com.labcivil.app.models.dto.ProyPreDto;

@Repository
public interface IPrestamoDao extends JpaRepository<Prestamo, Long>{
		
	public List<Prestamo> findByOrderByIdAsc();
		
	public List<Prestamo> findByEstadoOrderByIdAsc(String estado);

	public List<Prestamo> findByCreatePrBetweenOrderByIdAsc(Date fecha1, Date fecha2);
	
	public List<Prestamo> findByCreatePrAndHoraInBetweenOrderByIdAsc(Date fecha,Date timeStart, Date timeEnd);
	
	@Query("SELECT new com.labcivil.app.models.dto.ProyPreDto (p.id, p.alta, count(*)) "
			+ "FROM Proyector p INNER JOIN p.prestamos e where e.createPr between ?1 and ?2 group by p.id order by p.id")
	public List<ProyPreDto> fetchPreProyDataInnerJoin(Date startDate, Date endDate);
	
	@Query("SELECT new com.labcivil.app.models.dto.EstPreDto (e.id, e.apellido, count(*)) "
			+ "FROM Estudiante e INNER JOIN e.registros r INNER JOIN r.prestamos p where p.createPr between ?1 and ?2 group by e.id")
	public List<EstPreDto> fetchPreEstDataInnerJoin(Date startDate, Date endDate);
	
	@Query("SELECT new com.labcivil.app.models.dto.MatPreDto (m.id, m.nombre, count(*)) "
			+ "FROM Materia m INNER JOIN m.registros r INNER JOIN r.prestamos p where p.createPr between ?1 and ?2 group by m.id")
	public List<MatPreDto> fetchPreMatDataInnerJoin(Date startDate, Date endDate);
	
	@Query("SELECT new com.labcivil.app.models.dto.ProfPreDto (f.id, f.apellido, count(*)) "
			+ "FROM Profesor f INNER JOIN f.registros r INNER JOIN r.prestamos p where p.createPr between ?1 and ?2 group by f.id")
	public List<ProfPreDto> fetchPreProfDataInnerJoin(Date startDate, Date endDate);
	
}

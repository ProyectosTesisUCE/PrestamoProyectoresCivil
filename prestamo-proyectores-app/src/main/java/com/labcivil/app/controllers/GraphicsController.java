package com.labcivil.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.labcivil.app.models.dto.EstPreDto;
import com.labcivil.app.models.dto.MatPreDto;
import com.labcivil.app.models.dto.ProfPreDto;
import com.labcivil.app.models.entity.Estudiante;
import com.labcivil.app.models.entity.Materia;
import com.labcivil.app.models.entity.Profesor;
import com.labcivil.app.models.dto.ProyPreDto;
import com.labcivil.app.models.dto.ProyEstatusDto;
import com.labcivil.app.models.entity.Proyector;
import com.labcivil.app.models.service.IEstudianteService;
import com.labcivil.app.models.service.IMateriaService;
import com.labcivil.app.models.service.IProfesorService;
import com.labcivil.app.models.service.IProyectorService;

@Controller
public class GraphicsController {
	
	@Autowired
	private IProyectorService proyectorService;
	
	@Autowired
	private IMateriaService materiaService;
	
	@Autowired
	private IEstudianteService estudianteService;
	
	@Autowired
	private IProfesorService profesorService;
	
	private String dateStart = "";
	private String dateEnd = "";
	List<ProyPreDto> listaProy;
	List<MatPreDto> listaMat;
	List<EstPreDto> listaEst;
	List<ProfPreDto> listaProf;
		
	@GetMapping(value = "/graficas")
	public String graficos(Map<String, Object> model, RedirectAttributes flash) {

		model.put("titulo", "Gráficas");
		return "grafico";
	}
	
	@GetMapping(value = "/graficaHoursUsed")
	public String graficaVidaUtil(Map<String, Object> model, RedirectAttributes flash) {

		model.put("titulo", "Gráficas");
		return "graficaHours";
	}
	
	@GetMapping(value = "/cargar-proyectoresg", produces = { "application/json" })
	public @ResponseBody List<Proyector> cargarProyectores() {
		return proyectorService.findAllOrder();
	}
	
	@GetMapping(value = "/cargar-materiasg", produces = { "application/json" })
	public @ResponseBody List<Materia> cargarMaterias() {
		return materiaService.findAllOrder();
	}
	
	@GetMapping(value = "/cargar-estudiantesg", produces = { "application/json" })
	public @ResponseBody List<Estudiante> cargarEstudiantes() {
		return estudianteService.findAllOrder();
	}
	
	@GetMapping(value = "/cargar-profesoresg", produces = { "application/json" })
	public @ResponseBody List<Profesor> cargarProfesores() {
		return profesorService.findAllOrder();
	}
	
	@RequestMapping(value = "/sendFechaGrafica")
	public String sendDate(Model model,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate ){
		
		dateStart = startDate;
		dateEnd = endDate;
		
		model.addAttribute("titulo", "Gráficas por periodo");		
		return "sendFechaGrafica";
	}
	
	@GetMapping(value = "/cargar-proyectoresg1", produces = { "application/json" })
	public @ResponseBody List<ProyPreDto> cargarProyectores1() throws ParseException {
		
		if(dateStart!=null && dateEnd!=null) {
			listaProy= proyectorService.findGrafica1(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart),new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd) );
		}else {
			listaProy=null;
			}
		return listaProy;
//		return proyectorService.findGrafica1(new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-02"),new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-09") );
	}
	
	@GetMapping(value = "/cargar-materiasg1", produces = { "application/json" })
	public @ResponseBody List<MatPreDto> cargarMaterias1() throws ParseException {
		
		if(dateStart!=null && dateEnd!=null) {
			listaMat= materiaService.findGrafica1(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart),new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd) );
		}else {
			listaMat=null;
		}
		return listaMat;
	}
	
	@GetMapping(value = "/cargar-estudiantesg1", produces = { "application/json" })
	public @ResponseBody List<EstPreDto> cargarEstudiantes1() throws ParseException {
		
		if(dateStart!=null && dateEnd!=null) {
			listaEst= estudianteService.findGrafica1(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart),new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd) );
		}else {
			listaEst=null;
		}
		return listaEst;
	}
	
	@GetMapping(value = "/cargar-profesoresg1", produces = { "application/json" })
	public @ResponseBody List<ProfPreDto> cargarProfesores1() throws ParseException {
		
		if(dateStart!=null && dateEnd!=null) {
			listaProf= profesorService.findGrafica1(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart),new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd) );
		}else {
			listaProf=null;
		}
		return listaProf;
	}
	
	@GetMapping(value = "/cargar-estatusproyg", produces = { "application/json" })
	public @ResponseBody List<ProyEstatusDto> estatusProyector() {
		return proyectorService.graficaEstatus();
	}
	
//	@GetMapping("/innerProyectores")
//	public ResponseEntity<List<ProyPreDto>> getDeptEmployeesInnerJoin() {
//		return new ResponseEntity<List<ProyPreDto>>(proyectorService.findGrafica1(), HttpStatus.OK);
//	}	
	
	@GetMapping(value = { "/lab-civil", "/" })
//	@GetMapping(value = "/lab-civil")
	public String labCivil(Map<String, Object> model, RedirectAttributes flash) {

		model.put("titulo", "LABORATORIO DE CÓMPUTO DE INGENIERÍA CIVIL - PRÉSTAMO DE PROYECTORES");
		return "labcivil";
	}

}

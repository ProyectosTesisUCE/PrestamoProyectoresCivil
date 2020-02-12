package com.labcivil.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.labcivil.app.models.entity.Estudiante;
import com.labcivil.app.models.entity.Materia;
import com.labcivil.app.models.entity.Profesor;
import com.labcivil.app.models.entity.Registro;
import com.labcivil.app.models.service.IRegistroService;

@Controller
@RequestMapping("/registro")
@SessionAttributes("registro")
public class RegistroController {

	@Autowired
	private IRegistroService registroService;

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Registro registro = registroService.findOne(id);

		if (registro == null) {
			flash.addFlashAttribute("error", "El registro  no existe en la base de datos");
			return "redirect:/registro/listar";
		}

		model.put("registro", registro);
		model.put("titulo", "Detalle Carta de Compromiso: " + registro.getDescripcion());
		return "registro/ver";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Cartas de Compromiso");
		model.addAttribute("registros", registroService.findAllOrder());
		return "registro/listar";
	}

	@GetMapping(value = "/cargar-estudiantes/{term}", produces = { "application/json" })
	public @ResponseBody List<Estudiante> cargarEstudiantes(@PathVariable String term) {

		return registroService.findByApellidoE(term);
	}

	@GetMapping(value = "/cargar-profesores/{term}", produces = { "application/json" })
	public @ResponseBody List<Profesor> cargarProfesores(@PathVariable String term) {

		return registroService.findByApellidoP(term);
	}

	@GetMapping(value = "/cargar-materias/{term}", produces = { "application/json" })
	public @ResponseBody List<Materia> cargarMaterias(@PathVariable String term) {

		return registroService.findByNombreM(term);
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Registro registro = new Registro();
		model.put("registro", registro);
		model.put("titulo", "Formulario de Carta de Compromiso");
		return "registro/form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Registro registro, BindingResult result, Model model, 
			@RequestParam(name = "buscar_estudiante_id", required = false) Long estId,
			@RequestParam(name = "estudiante_email") String emailEs,
			@RequestParam(name = "buscar_estudiante") String estAp, @RequestParam(name = "buscar_estudiante_nombre") String estNom,
			@RequestParam(name = "buscar_profesor_id", required = false) Long proId,
			@RequestParam(name = "buscar_profesor") String profAp, @RequestParam(name = "buscar_profesor_nombre") String profNom,
			@RequestParam(name = "buscar_materia_id", required = false) Long matId, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Carta de Compromiso");
			return "/registro/form";
		}
		
		String descripcion = "Est: "+estAp+" "+estNom+" - Prof: "+profAp+" "+profNom;

		Estudiante estudiante = registroService.findEstudianteById(estId);
		Profesor profesor = registroService.findProfesorById(proId);
		Materia materia = registroService.findMateriaById(matId);

		registro.setDescripcion(descripcion);
		registro.setEmailEs(emailEs);
		registro.setEstudiante(estudiante);
		registro.setProfesor(profesor);
		registro.setMateria(materia);

		registroService.save(registro);
		status.setComplete();
		flash.addFlashAttribute("success", "Carta de Compromiso creada con éxito");
		return "redirect:/registro/listar";

	}

	@RequestMapping(value = "/form1/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Registro registro = null;

		if (id > 0) {
			registro = registroService.findOne(id);
			if (registro == null) {
				flash.addFlashAttribute("error", "El ID del registro no existe en la BBDD!");
				return "redirect:/registro/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del registro no puede ser cero!");
			return "redirect:/registro/listar";
		}
		model.put("registro", registro);
		model.put("titulo", "Editar Carta de compromiso");
		return "registro/form1";
	}
	
	@PostMapping("/form1")
	public String guardar1(@Valid Registro registro, BindingResult result, Model model, 
		  RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Carta de Compromiso");
			return "form";
		}

		registroService.save(registro);
		status.setComplete();
		flash.addFlashAttribute("success", "Carta de Compromiso editada con éxito");
		return "redirect:/registro/listar";

	}

	// Eliminar 
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {

			registroService.delete(id);
			flash.addFlashAttribute("success", "Carta de Compromiso eliminada con éxito");
		}
		return "redirect:/registro/listar";
	}

}

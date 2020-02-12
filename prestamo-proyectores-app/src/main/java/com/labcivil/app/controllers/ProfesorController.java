package com.labcivil.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.labcivil.app.models.entity.Profesor;
import com.labcivil.app.models.service.IProfesorService;

@Controller
@RequestMapping("/profesor")
@SessionAttributes("profesor")
public class ProfesorController {
	
	@Autowired
	private IProfesorService profesorService;
		
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Profesor profesor = profesorService.findOne(id);
		
		if (profesor == null) {
			flash.addFlashAttribute("error", "El docente no existe en la base de datos");
			return "redirect:/profesor/listar";
		}

		model.put("profesor", profesor);
		model.put("titulo", "Detalle docente: " + profesor.getNombre() +" "+ profesor.getApellido());
		return "profesor/ver";
	}
	
	//Sin paginar --> listar todos funciona
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Docentes");
		model.addAttribute("profesores", profesorService.findAll());
		return "profesor/listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Profesor profesor = new Profesor();
		model.put("profesor", profesor);
		model.put("titulo", "Formulario de Docente");
		return "profesor/form";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Profesor profesor = null;

		if (id > 0) {
			profesor = profesorService.findOne(id);
			if(profesor==null) {
				flash.addFlashAttribute("error", "El ID del docente no existe en la BBDD!");
				return "redirect:/profesor/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del docente no puede ser cero!");
			return "redirect:/profesor/listar";
		}
		model.put("profesor", profesor);
		model.put("titulo", "Editar Docente");
		return "profesor/form";
	}
	
	//Método guardar
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Profesor profesor, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Docente");
			return "/profesor/form";
		}

		String mensajeFlash = (profesor.getId() != null)? "Docente editado con éxito!" : "Docente creado con éxito!";
		
		profesorService.save(profesor);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/profesor/listar";
	}
	
	//Eliminar
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			
			profesorService.delete(id);
			flash.addFlashAttribute("success", "Docente eliminado con éxito");
		}
		return "redirect:/profesor/listar";
	}
	
}

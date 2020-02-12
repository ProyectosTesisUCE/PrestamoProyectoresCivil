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

import com.labcivil.app.models.entity.Materia;
import com.labcivil.app.models.service.IMateriaService;

@Controller
@RequestMapping("/materia")
@SessionAttributes("materia")
public class MateriaController {
	
	@Autowired
	private IMateriaService materiaService;
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Materia materia = materiaService.findOne(id);
		
		if (materia == null) {
			flash.addFlashAttribute("error", "El registro de materia no existe en la base de datos");
			return "redirect:/materia/listar";
		}

		model.put("materia", materia);
		model.put("titulo", "Detalle materia: " + materia.getNombre());
		return "materia/ver";
	}
	
	//Sin paginar --> listar todos funciona
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Materias");
		model.addAttribute("materias", materiaService.findAll());
		return "materia/listar";
	}
	
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Materia materia = new Materia();
		model.put("materia", materia);
		model.put("titulo", "Formulario de Materia");
		return "materia/form";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Materia materia = null;

		if (id > 0) {
			materia = materiaService.findOne(id);
			if(materia==null) {
				flash.addFlashAttribute("error", "El ID de la materia no existe en la BBDD!");
				return "redirect:/materia/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de la materia no puede ser cero!");
			return "redirect:/materia/listar";
		}
		model.put("materia", materia);
		model.put("titulo", "Editar Materia");
		return "materia/form";
	}
	
	//Método guardar 
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Materia materia, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Materia");
			return "/materia/form";
		}

		String mensajeFlash = (materia.getId() != null)? "Materia editada con éxito!" : "Materia creada con éxito!";
		
		materiaService.save(materia);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/materia/listar";
	}
	
	//Eliminar 
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			
			materiaService.delete(id);
			flash.addFlashAttribute("success", "Registro de materia eliminada con éxito");
		}
		return "redirect:/materia/listar";
	}
	
}

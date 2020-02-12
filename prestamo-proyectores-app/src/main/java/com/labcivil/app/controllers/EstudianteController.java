package com.labcivil.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.labcivil.app.models.entity.Estudiante;
import com.labcivil.app.models.service.IEstudianteService;
import com.labcivil.app.models.service.IUploadFileService;

@Controller
@RequestMapping("/estudiante")
@SessionAttributes("estudiante")
public class EstudianteController {
	
	@Autowired
	private IEstudianteService estudianteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Estudiante estudiante = estudianteService.findOne(id);
		
		if (estudiante == null) {
			flash.addFlashAttribute("error", "El estudiante no existe en la base de datos");
			return "redirect:/estudiante/listar";
		}

		model.put("estudiante", estudiante);
		model.put("titulo", "Detalle estudiante: " + estudiante.getNombre() +" "+ estudiante.getApellido());
		return "estudiante/ver";
	}
	
	//Sin paginar --> listar todos funciona
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Estudiantes");
		model.addAttribute("estudiantes", estudianteService.findAll());
		return "estudiante/listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Estudiante estudiante = new Estudiante();
		model.put("estudiante", estudiante);
		model.put("titulo", "Formulario de Estudiante");
		return "estudiante/form";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Estudiante estudiante = null;

		if (id > 0) {
			estudiante = estudianteService.findOne(id);
			if(estudiante==null) {
				flash.addFlashAttribute("error", "El ID del estudiante no existe en la BBDD!");
				return "redirect:/estudiante/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del estudiante no puede ser cero!");
			return "redirect:/estudiante/listar";
		}
		model.put("estudiante", estudiante);
		model.put("titulo", "Editar Estudiante");
		return "estudiante/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Estudiante estudiante, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Estudiante");
			return "/estudiante/form";
		}
		
		if(!foto.isEmpty()) {
			
			if(estudiante.getId() != null && estudiante.getId() > 0 && estudiante.getFoto() != null && estudiante.getFoto().length() >0) {
				
				uploadFileService.delete(estudiante.getFoto());
			}
			
			String uniqueFileName = null;
			try {
				uniqueFileName = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFileName + "'");
			
			estudiante.setFoto(uniqueFileName);	
		}

		String mensajeFlash = (estudiante.getId() != null)? "Estudiante editado con éxito!" : "Estudiante creado con éxito!";
		
		estudianteService.save(estudiante);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/estudiante/listar";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			Estudiante estudiante = estudianteService.findOne(id);
			
			estudianteService.delete(id);
			flash.addFlashAttribute("success", "Estudiante eliminado con éxito");

				if(uploadFileService.delete(estudiante.getFoto())) {
					flash.addFlashAttribute("info", "Foto " + estudiante.getFoto() + "eliminada con éxito!");
				}
		}
		return "redirect:/estudiante/listar";
	}

}

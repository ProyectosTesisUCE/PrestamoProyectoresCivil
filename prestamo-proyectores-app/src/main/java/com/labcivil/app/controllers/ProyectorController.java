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

import com.labcivil.app.models.entity.Proyector;
import com.labcivil.app.models.service.IProyectorService;
import com.labcivil.app.models.service.IUploadFileService;


@Controller
@RequestMapping("/proyector")
@SessionAttributes("proyector")
public class ProyectorController {

	@Autowired
	private IProyectorService proyectorService;
	
	@Autowired
	private IUploadFileService uploadFileService;

	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	
		@GetMapping(value = "/ver/{id}")
		public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Proyector proyector = proyectorService.findOne(id);
		
		if (proyector == null) {
			flash.addFlashAttribute("error", "El proyector no existe en la base de datos");
			return "redirect:/proyector/listar";
		}

		model.put("proyector", proyector);
		model.put("titulo", "Detalle proyector: " + proyector.getAlta());
		return "proyector/ver";
	}
	
	
	//Sin paginar --> listar todos funciona
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Proyectores");
		model.addAttribute("proyectores", proyectorService.findAll());
		return "proyector/listar";
	}
	

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Proyector proyector = new Proyector();
		model.put("proyector", proyector);
		model.put("titulo", "Formulario de Proyector");
		return "proyector/form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Proyector proyector = null;

		if (id > 0) {
			proyector = proyectorService.findOne(id);
			if(proyector==null) {
				flash.addFlashAttribute("error", "El ID del proyector no existe en la BBDD!");
				return "redirect:/proyector/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del proyector no puede ser cero!");
			return "redirect:/proyector/listar";
		}
		model.put("proyector", proyector);
		model.put("titulo", "Editar Proyector");
		return "proyector/form";
	}

	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Proyector proyector, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Proyector");
			return "/proyector/form";
		}
		
		if(!foto.isEmpty()) {
			
			if(proyector.getId() != null && proyector.getId() > 0 && proyector.getFoto() != null && proyector.getFoto().length() >0) {
				
				uploadFileService.delete(proyector.getFoto());
			}
			
			String uniqueFileName = null;
			try {
				uniqueFileName = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFileName + "'");
			
			proyector.setFoto(uniqueFileName);
	
		}

		String mensajeFlash = (proyector.getId() != null)? "Proyector editado con éxito!" : "Proyector creado con éxito!";
		
		proyectorService.save(proyector);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/proyector/listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			Proyector proyector = proyectorService.findOne(id);
			
			proyectorService.delete(id);
			flash.addFlashAttribute("success", "Proyector eliminado con éxito");
			
				if(uploadFileService.delete(proyector.getFoto())) {
					flash.addFlashAttribute("info", "Foto " + proyector.getFoto() + "eliminada con éxito!");
				}
		}
		return "redirect:/proyector/listar";
	}

}

package com.labcivil.app.controllers;

import java.util.Map;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.labcivil.app.models.dao.IPersonaDao;
import com.labcivil.app.models.entity.Role;
import com.labcivil.app.models.entity.Usuario;
import com.labcivil.app.models.service.IUserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UsuarioController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value= "/listar", method=RequestMethod.GET)
	public String listarUser(Model model) {
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("users", userService.findAll());
		return "user/listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		Usuario user = new Usuario();
		model.put("user", user);
		model.put("titulo", "Formulario de Usuario Estándar");
		return "user/form";
	}
		
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(Usuario user, Model model,
			@RequestParam(name = "userName", required = false) String userNm,
			@RequestParam(name = "password", required = false) String passwd,
			 RedirectAttributes flash, SessionStatus status) {
		
		Usuario usuarioName = userService.findByUserName(userNm);
		
		if (usuarioName == null) {
		
		Role role = new Role();
		
		String bcryptPassword = passwordEncoder.encode(passwd);

		user.setUsername(userNm);
		user.setPassword(bcryptPassword);				
		userService.save(user);
		
		Long idUser = user.getId();
		
		role.setUserId(idUser);
		role.setAuthority("ROLE_USER");
		
		userService.saveR(role);
		status.setComplete();
		flash.addFlashAttribute("success", "Usuario Estándar creado");
		}else {
			flash.addFlashAttribute("error", "Username ya existe, intente con otro nombre de usuario");
		}
		
		return "redirect:/user/listar";
	}
	
	@RequestMapping(value="/form1/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Usuario user = null;
		
		if(id>0) {
			user = userService.findOne(id);
			if (user == null) {
				flash.addFlashAttribute("error", "El ID del usuario no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del usuario no puede ser cero!");
			return "redirect:/user/listar";
		}
		Long idUsuario = user.getId();
		model.put("idUsuario", idUsuario);
		model.put("user", user);
		model.put("titulo", "Editar Usuario Estándar");		
		return "user/form1";
	}
	
	@PostMapping("/form1")
	public String guardar1(Usuario user, Model model,
			@RequestParam(name = "idUsuario", required = false) Long idUsuario,
			@RequestParam(name = "userA", required = false) String userA,
			@RequestParam(name = "userDes", required = false) Boolean userDes,
			RedirectAttributes flash, SessionStatus status) {
		
		Usuario user1 = userService.findOne(idUsuario);
		String userName1 = user1.getUsername();

		if(userDes != null) {
			user1.setEnabled(userDes);
			userService.save(user1);
			status.setComplete();
			flash.addFlashAttribute("success", "Usuario "+userName1+" esta desabilitado del sistema");
		}
		
		if(userA != null) {
			
			Role role = new Role();
			role.setUserId(idUsuario);
			role.setAuthority(userA);
			userService.saveR(role);
			
			user1.setRolad("Administrador");
			userService.save(user1);
			status.setComplete();
			flash.addFlashAttribute("success", "Usuario "+userName1+" es ahora Administrador");
			
		}
//		else {			
//			flash.addFlashAttribute("info", "Usuario sin cambios ");
//		}
		
		if(userDes==null & userA==null) {
			flash.addFlashAttribute("info", "Usuario sin cambios ");
		}		

		return "redirect:/user/listar";
	}
	

	@RequestMapping(value="/form2")
	public String login(Map<String, Object> model) {
		Usuario user = new Usuario();
		model.put("user", user);
		model.put("titulo", "Formulario de Usuario Estándar");
		return "user/form2";
	}
		
	
	@RequestMapping(value="/form2", method=RequestMethod.POST)
	public String guardarLogin(Usuario user, Model model,
			@RequestParam(name = "userName", required = false) String userNm,
			@RequestParam(name = "password", required = false) String passwd,
			 RedirectAttributes flash, SessionStatus status) {
		
		Usuario usuarioName = userService.findByUserName(userNm);
		
		if (usuarioName == null) {
		
		Role role = new Role();
		
		String bcryptPassword = passwordEncoder.encode(passwd);

		user.setUsername(userNm);
		user.setPassword(bcryptPassword);				
		userService.save(user);
		
		Long idUser = user.getId();
		
		role.setUserId(idUser);
		role.setAuthority("ROLE_USER");
		
		userService.saveR(role);
		status.setComplete();
		flash.addFlashAttribute("success", "Usuario Estándar creado");
		}else {
			flash.addFlashAttribute("error", "Username ya existe, intente con otro nombre de usuario");
		}
		
		return "redirect:/login";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			userService.delete(id);
		}
		return "redirect:/user/listar";
	}
}

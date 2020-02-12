package com.labcivil.app.models.service;

import java.util.List;

import com.labcivil.app.models.entity.Role;
import com.labcivil.app.models.entity.Usuario;

public interface IUserService {
	
	public List<Usuario> findAll();
	
	public List<Usuario> findAllOrder();
	
	public void save(Usuario usuario);
	
	public void saveR(Role role);
	
	public Usuario findOne(Long id);
	
	public Role findOneR(Long id);
	
	public void delete(Long id);
	
	public Usuario findByUserName(String username);

}

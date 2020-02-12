package com.labcivil.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labcivil.app.models.dao.IRoleDao;
import com.labcivil.app.models.dao.IUsuarioDao;
import com.labcivil.app.models.entity.Role;
import com.labcivil.app.models.entity.Usuario;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public List<Usuario> findAllOrder() {
		return null;
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void saveR(Role role) {
		roleDao.save(role);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Role findOneR(Long id) {
		return roleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByUserName(String username) {
		return usuarioDao.findByUsername(username);
	}

}

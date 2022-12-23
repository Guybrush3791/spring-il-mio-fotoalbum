package spring.il.mio.fotoalbum.app.service.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.il.mio.fotoalbum.app.pojo.security.Role;
import spring.il.mio.fotoalbum.app.repo.security.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	public Role save(Role role) {
		
		return roleRepo.save(role);
	}
	public void delete(Role role) {
		
		roleRepo.delete(role);
	}
	public Optional<Role> findById(int id) {
		
		return roleRepo.findById(id);
	}
	public List<Role> findAll() {
		
		return roleRepo.findAll();
	}
}

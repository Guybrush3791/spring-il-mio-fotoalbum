package spring.il.mio.fotoalbum.app.service.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.il.mio.fotoalbum.app.pojo.security.User;
import spring.il.mio.fotoalbum.app.repo.security.UserRepo;
import spring.il.mio.fotoalbum.app.security.DatabaseUserDetails;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	public User save(User user) {
		
		return userRepo.save(user);
	}
	public void delete(User user) {
		
		userRepo.delete(user);
	}
	public Optional<User> findById(int id) {
		
		return userRepo.findById(id);
	}
	public List<User> findAll() {
		
		return userRepo.findAll();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if (userOpt.isEmpty()) throw new UsernameNotFoundException("Username not found: " + username);
		
		return new DatabaseUserDetails(userOpt.get());
	}
}

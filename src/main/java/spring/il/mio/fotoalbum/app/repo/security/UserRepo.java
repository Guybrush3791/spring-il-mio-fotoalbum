package spring.il.mio.fotoalbum.app.repo.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.il.mio.fotoalbum.app.pojo.security.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
}

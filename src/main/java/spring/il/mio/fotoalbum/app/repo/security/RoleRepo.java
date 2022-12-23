package spring.il.mio.fotoalbum.app.repo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.il.mio.fotoalbum.app.pojo.security.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}

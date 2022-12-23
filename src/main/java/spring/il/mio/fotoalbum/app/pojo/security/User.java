package spring.il.mio.fotoalbum.app.pojo.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import spring.il.mio.fotoalbum.app.pojo.abs.ComparablePojo;

@Entity
public class User extends ComparablePojo {

	private String username;
	private String pws;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	public User() { }
	public User(String username, String pws, Role... roles) {
		
		setUsername(username);
		setPws(pws);
		setRoles(roles);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPws() {
		return pws;
	}
	public void setPws(String pws) {
		this.pws = "{noop}" + pws;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setRoles(Role[] roles) {
		
		setRoles(new HashSet<>(Arrays.asList(roles)));		
	}
	
	@Override
	public String toString() {
		
		return super.toString() + getUsername()
			+ roles.stream().map(Object::toString).collect(Collectors.joining(" - "));
	}
}

package spring.il.mio.fotoalbum.app.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import spring.il.mio.fotoalbum.app.pojo.security.User;

public class DatabaseUserDetails implements UserDetails {

	private static final long serialVersionUID = 1252067694834255008L;
	
	private final User user;
	
	public DatabaseUserDetails(User user) {

		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return user.getRoles().stream() // open a stream on all user roles
			.map(r -> new SimpleGrantedAuthority(r.getName())) // map every role with name in a SimpleGrantedAuthority
		.collect(Collectors.toList()); // return result as List
	}

	@Override
	public String getPassword() {

		return user.getPws();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
}

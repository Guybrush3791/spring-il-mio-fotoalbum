package spring.il.mio.fotoalbum.app.pojo.security;

import jakarta.persistence.Entity;
import spring.il.mio.fotoalbum.app.pojo.abs.ComparablePojo;

@Entity
public class Role extends ComparablePojo {

	private String name;
	
	public Role() { }
	public Role(String name) {
		
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		
		return super.toString() + getName();
	}
}

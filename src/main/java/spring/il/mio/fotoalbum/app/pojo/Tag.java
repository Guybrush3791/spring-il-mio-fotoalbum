package spring.il.mio.fotoalbum.app.pojo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import spring.il.mio.fotoalbum.app.pojo.abs.ComparablePojo;

@Entity
public class Tag extends ComparablePojo {

	@NotNull(message = "Tag name can't be empty")
	@NotEmpty(message = "Tag name can't be empty")
	@Size(min = 3, max = 64, message = "Category name must be between 3 and 64 charters")
	private String name;
	
	public Tag() { }
	public Tag(String name) {
		
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

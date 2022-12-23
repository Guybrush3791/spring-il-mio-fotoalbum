package spring.il.mio.fotoalbum.app.pojo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import spring.il.mio.fotoalbum.app.pojo.abs.ComparablePojo;

@Entity
public class Category extends ComparablePojo {

	@NotNull(message = "Category name can't be empty")
	@NotEmpty(message = "Category name can't be empty")
	@Size(min = 3, max = 64, message = "Category name must be between 3 and 64 charters")
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	@JsonIgnore
	Set<Photo> photos;
	
	public Category() { }
	public Category(String name) {
		
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	
	@Override
	public String toString() {
		
		return super.toString() + getName();
	}
}

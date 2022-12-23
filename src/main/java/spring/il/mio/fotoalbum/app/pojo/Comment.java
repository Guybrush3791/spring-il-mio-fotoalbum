package spring.il.mio.fotoalbum.app.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import spring.il.mio.fotoalbum.app.pojo.abs.ComparablePojo;

@Entity
public class Comment extends ComparablePojo {

	@NotNull(message = "Username can't be empty")
	@NotEmpty(message = "Username can't be empty")
	@Size(min = 3, max = 64, message = "Username must be between 3 and 64 charters")
	private String username;
	
	@Lob
	@Size(min = 3, message = "Text must be at least 3 charters")
	private String text;
	
	@ManyToOne
	@JsonIgnore
	private Photo photo;
	
	public Comment() { }
	public Comment(String username, String text) {
		
		setUsername(username);
		setText(text);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	@Override
	public String toString() {
		
		return super.toString() + getUsername() + " - " + getText();
	}
	
}

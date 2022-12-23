package spring.il.mio.fotoalbum.app.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import spring.il.mio.fotoalbum.app.pojo.abs.ComparablePojo;

@Entity
public class Photo extends ComparablePojo {
	
	@NotNull(message = "Title can't be empty")
	@NotEmpty(message = "Title can't be empty")
	@Size(min = 3, max = 64, message = "Title must be between 3 and 64 charters")
	private String title;
	
	@Lob
	private String description;
	
	@NotNull(message = "Url can't be empty")
	@NotEmpty(message = "Url can't be empty")
	private String url;
	
	// set default visibility to TRUE
	private boolean visible = true;
	
	@OneToMany(mappedBy = "photo")
	private Set<Comment> comments;
	
	@ManyToMany
	private Set<Tag> tags;
	
	@ManyToMany
	private Set<Category> categories;
	
	public Photo() { }
	public Photo(String title, String description, String url) {
		
		setTitle(title);
		setDescription(description);
		setUrl(url);
	}
	
	public String getTitle() {
		return title;
	}
	public Photo setTitle(String title) {
		
		this.title = title;
		
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Photo setDescription(String description) {
		
		this.description = description;
		
		return this;
	}
	public String getUrl() {
		return url;
	}
	public Photo setUrl(String url) {
		
		this.url = url;

		return this;
	}
	public boolean isVisible() {
		return visible;
	}
	public Photo setVisible(boolean visible) {
		
		this.visible = visible;
		
		return this;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public Photo setComments(Set<Comment> comments) {
		
		this.comments = comments;
		
		return this;
	}
	// this method use `fluent interface` to gain possibility
	// to use chain method calls: 
	// obj.meth1().meth2().meth3();
	// check Spring...Application.java for more info
	public Photo addComment(Comment comment) {
		
		if (getComments() == null) setComments(new HashSet<>());
		
		getComments().add(comment); 
		
		return this;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	// this method is useful for MVC front-end
	// but it must be excluded from JSON serialization
	// to avoid redundanci of information in client-server
	// comunications
	@JsonIgnore
	public String getTagsStr() {
		
		return getStrFromRelation(getTags(), Tag::getName, "NO TAGS");
	}
	public Photo setTags(Set<Tag> tags) {
		
		this.tags = tags;
		
		return this;
	}
	// this method use `fluent interface` to gain possibility
	// to use chain method calls: 
	// obj.meth1().meth2().meth3();
	// check Spring...Application.java for more info
	public Photo addTags(Tag... tags) {
		
		if (getTags() == null) setTags(new HashSet<>());
		
		getTags().addAll(Arrays.asList(tags));
		
		return this;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	// same as getTagsStr()
	@JsonIgnore
	public String getCategoriesStr() {
		
		return getStrFromRelation(getCategories(), Category::getName, "NO CATEGORY");
	}
	public Photo setCategories(Set<Category> categories) {
		
		this.categories = categories;
		
		return this;
	}
	public Photo addCategories(Category... categories) {
		
		if (getCategories() == null) setCategories(new HashSet<>());
		
		getCategories().addAll(Arrays.asList(categories));
		
		return this;
	}
	public Photo removeCategory(Category category) {
		
		if (getCategories() != null) 
			getCategories().remove(category);
		
		return this;
	}
	
	@Override
	public String toString() {
		
		return super.toString() + getTitle() 
			  + "\nDescription: " + getDescription()
			  + "\nURL: " + getUrl()
			  + "\nis visible: " + isVisible()
		;
	}
}

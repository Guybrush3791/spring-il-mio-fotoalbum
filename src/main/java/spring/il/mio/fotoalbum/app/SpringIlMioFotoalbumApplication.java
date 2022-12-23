package spring.il.mio.fotoalbum.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spring.il.mio.fotoalbum.app.pojo.Category;
import spring.il.mio.fotoalbum.app.pojo.Comment;
import spring.il.mio.fotoalbum.app.pojo.Photo;
import spring.il.mio.fotoalbum.app.pojo.Tag;
import spring.il.mio.fotoalbum.app.pojo.security.Role;
import spring.il.mio.fotoalbum.app.pojo.security.User;
import spring.il.mio.fotoalbum.app.service.CategoryService;
import spring.il.mio.fotoalbum.app.service.CommentService;
import spring.il.mio.fotoalbum.app.service.PhotoService;
import spring.il.mio.fotoalbum.app.service.TagService;
import spring.il.mio.fotoalbum.app.service.security.RoleService;
import spring.il.mio.fotoalbum.app.service.security.UserService;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	// SERVICE PER DATI FAKE	
	@Autowired
	private RoleService roleService; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private PhotoService photoService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// SALVATAGGIO DATI FAKE IN DB
		Role adminRole = new Role("ADMIN");
		roleService.save(adminRole);
		
		User owner = new User("Guybrush", "pws", adminRole);
		userService.save(owner);
		
		// create a list of tags
		// make a forEach on it
		// statically call tagService's save method
		// passing each single tag as arguments
		Arrays.asList(
			new Tag("tag 1"), 
			new Tag("mio"), 
			new Tag("tagg"), 
			new Tag("new tag")
		).forEach(tagService::save);
		List<Tag> tags = tagService.findAll();		
		
		// same as tags
		Arrays.asList(
			new Category("cat1"), 
			new Category("cat2"), 
			new Category("cat3"), 
			new Category("cat4")
		).forEach(categoryService::save);
		List<Category> categories = categoryService.findAll();
		
		// same as tag
		Arrays.asList(
			
			// `fluent interface` chain method calls
			// Go to Photo class for more info
			new Photo("MEME 1", "Desc photo 1", "https://img.devrant.com/devrant/rant/r_141679_jRF8A.jpg")
				.addCategories(categories.get(0), categories.get(1))
				.addTags(tags.get(0), tags.get(1), tags.get(2), tags.get(3))
				.setVisible(false), 
			new Photo("MEME 2", "Desc photo 2", "https://codinginfinite.com/wp-content/uploads/2019/05/maxresdefault-1024x492.jpg")
				.addCategories(categories.get(2), categories.get(3))
				.addTags(tags.get(3)), 
			new Photo("MEME 3", "Desc photo 3", "https://i.redd.it/7pp55ytrrpy31.png")
				.addCategories(categories.get(0), categories.get(1), categories.get(3)),
			new Photo("MEME 4", "Desc photo 4", "https://starecat.com/content/wp-content/uploads/finally-monitor-that-will-fit-the-entire-name-of-my-java-classes-panoramic-very-long.jpg")
				.addTags(tags.get(0), tags.get(3))
		).forEach(photoService::save);
	}
}

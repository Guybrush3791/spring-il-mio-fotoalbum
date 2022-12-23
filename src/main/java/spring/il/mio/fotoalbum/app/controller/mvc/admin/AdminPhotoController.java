package spring.il.mio.fotoalbum.app.controller.mvc.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import spring.il.mio.fotoalbum.app.pojo.Category;
import spring.il.mio.fotoalbum.app.pojo.Photo;
import spring.il.mio.fotoalbum.app.pojo.Tag;
import spring.il.mio.fotoalbum.app.service.CategoryService;
import spring.il.mio.fotoalbum.app.service.PhotoService;
import spring.il.mio.fotoalbum.app.service.TagService;

//MVC PHOTO CONTROLLER

@Controller
@RequestMapping("/admin/photo")
public class AdminPhotoController {

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping
	public String getPhotos(
			Model model, 
			@RequestParam(required = false) String q
	) {
	 
		// load list query-research based
		List<Photo> photos =
				q == null // no search?
				? photoService.findAll() // get all photos
				: photoService.findByTitleOrTag(q) // get only matching photo
		;
		
		// load photo list and query
		model.addAttribute("photos", photos);		
		model.addAttribute("q", q);
		
		return "admin/admin-photos";
	}
	
	@GetMapping("/show/{id}")
	@PostMapping("/show/{id}")
	public String getPhotoDetails(
		@PathVariable("id") int id,
		Model model
	) {
		
		Optional<Photo> photoOpt = photoService.findById(id);
		
		if (photoOpt.isEmpty()) return "redirect:/admin";
		
		Photo photo = photoOpt.get();
		model.addAttribute("photo", photo);
		
		return "admin/admin-photo-show";
	}

	@GetMapping("/new")
	public String getPhotoNewForm(Model model) {
		
		Photo photo = new Photo();
		model.addAttribute("photo", photo);
		
		// useful private method for decouple complexity
		// and avoid duplicate code
		loadCategoriesAndTags(model);
		
		return "admin/admin-photo-edit";
	}
	
	@GetMapping("/edit/{id}")
	public String getPhotoEditForm(
		@PathVariable("id") int id,
		Model model
	) {
		
		Optional<Photo> photoOpt = photoService.findById(id);	
		if (photoOpt.isEmpty()) return "redirect:/admin";
		
		Photo photo = photoOpt.get();
		model.addAttribute("photo", photo);
		
		// useful private method for decouple complexity
		// and avoid duplicate code
		loadCategoriesAndTags(model);
		
		return "admin/admin-photo-edit";
	}
	
	@PostMapping("/edit/{id}")
	public String savePhotoEdit(
		@PathVariable("id") int id,
		@Valid Photo photo,
		BindingResult bindingResult, 
		RedirectAttributes redirectAttributes
	) {
		
		if(bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/admin/photo/edit/" + id;
		}
		
		photo = photoService.save(photo);
		
		return "redirect:/admin/photo/show/" + id;
	}
	
	@GetMapping("/delete/{id}")
	@PostMapping("/delete/{id}")
	public String deletePhoto(
		@PathVariable("id") int id
	) {
		
		Optional<Photo> photoOpt = photoService.findById(id);
		
		if (photoOpt.isPresent())
			photoService.delete(photoOpt.get());
		
		return "redirect:/admin";
	}
	
	// provide a convenient method to load all categories and tags
	// in model for thymeleaf
	private void loadCategoriesAndTags(Model model) {
		
		List<Category> categories = categoryService.findAll();
		List<Tag> tags = tagService.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("tags", tags);
	}
}

package spring.il.mio.fotoalbum.app.controller.mvc.admin;

import java.util.HashSet;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import spring.il.mio.fotoalbum.app.pojo.Category;
import spring.il.mio.fotoalbum.app.pojo.Photo;
import spring.il.mio.fotoalbum.app.service.CategoryService;
import spring.il.mio.fotoalbum.app.service.PhotoService;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping
	public String getCategories(Model model) {
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		return "admin/admin-categories";
	}
	@GetMapping("/new")
	public String getCategoryNewForm(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		
		return "admin/admin-category-edit";
	}
	@GetMapping("/edit/{id}")
	public String getCategoryEditForm(
		@PathVariable("id") int id,
		Model model
	) {
		
		Optional<Category> categoryOpt = categoryService.findById(id);
		
		if (categoryOpt.isEmpty()) return "redirect:/admin/category";
		
		Category category = categoryOpt.get();
		model.addAttribute("category", category);
		
		return "admin/admin-category-edit";
	}
	@PostMapping("/edit/{id}")
	public String saveCategoryEdit(
		@PathVariable("id") int id,
		@Valid Category category,
		BindingResult bindingResult, 
		RedirectAttributes redirectAttributes
	) {
		
		if(bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/admin/category/edit/" + id;
		}
		
		categoryService.save(category);
		
		return "redirect:/admin/category";
	}
	
	@GetMapping("/delete/{id}")
	@PostMapping("/delete/{id}")
	public String deleteCategory(
		@PathVariable("id") int id
	) { 
		
		Optional<Category> categoryOpt = categoryService.findById(id);
		
		if (categoryOpt.isPresent()) {
			
			Category category = categoryOpt.get();
			
			category.getPhotos().forEach(p -> {
			
				p.removeCategory(category);
				photoService.save(p);
			});
			
			categoryService.delete(categoryOpt.get());
		}
		
		return "redirect:/admin/category";
	}
}

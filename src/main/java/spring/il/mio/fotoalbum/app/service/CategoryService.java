package spring.il.mio.fotoalbum.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.il.mio.fotoalbum.app.pojo.Category;
import spring.il.mio.fotoalbum.app.repo.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public Category save(Category category) {
		
		return categoryRepo.save(category);
	}
	public void delete(Category category) {
		
		categoryRepo.delete(category);
	}
	public Optional<Category> findById(int id) {
		
		return categoryRepo.findById(id);
	}
	public List<Category> findAll() {
		
		return categoryRepo.findAll();
	}
}

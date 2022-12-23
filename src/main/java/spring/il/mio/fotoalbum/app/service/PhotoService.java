package spring.il.mio.fotoalbum.app.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Transient;
import spring.il.mio.fotoalbum.app.pojo.Photo;
import spring.il.mio.fotoalbum.app.repo.PhotoRepo;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepo photoRepo;
	
	public Photo save(Photo photo) {
		
		return photoRepo.save(photo);
	}
	public void delete(Photo photo) {
		
		photoRepo.delete(photo);
	}
	public Optional<Photo> findById(int id) {
		
		return photoRepo.findById(id);
	}
	public List<Photo> findAll() {
		
		return photoRepo.findAll();
	}
	public List<Photo> findByTitleOrTag(String query) {
		
		return photoRepo.findByTitleContainingOrTagsNameContaining(query, query);
	}
	public List<Photo> findVisible() {
		
		return photoRepo.findByVisibleTrue();
	}
}

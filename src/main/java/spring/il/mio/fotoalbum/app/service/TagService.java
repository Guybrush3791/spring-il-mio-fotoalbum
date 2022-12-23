package spring.il.mio.fotoalbum.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.il.mio.fotoalbum.app.pojo.Tag;
import spring.il.mio.fotoalbum.app.repo.TagRepo;

@Service
public class TagService {

	@Autowired
	private TagRepo tagRepo;
	
	public Tag save(Tag tag) {
		
		return tagRepo.save(tag);
	}
	public void delete(Tag tag) {
		
		tagRepo.delete(tag);
	}
	public Optional<Tag> findById(int id) {
		
		return tagRepo.findById(id);
	}
	public List<Tag> findAll() {
		
		return tagRepo.findAll();
	}
}

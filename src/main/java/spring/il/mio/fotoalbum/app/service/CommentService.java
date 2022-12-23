package spring.il.mio.fotoalbum.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.il.mio.fotoalbum.app.pojo.Comment;
import spring.il.mio.fotoalbum.app.repo.CommentRepo;

@Service
public class CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	public Comment save(Comment comment) {
		
		return commentRepo.save(comment);
	}
	public void delete(Comment comment) {
		
		commentRepo.save(comment);
	}
	public Optional<Comment> findById(int id) {
		
		return commentRepo.findById(id);
	}
	public List<Comment> findAll() {
		
		return commentRepo.findAll();
	}
}

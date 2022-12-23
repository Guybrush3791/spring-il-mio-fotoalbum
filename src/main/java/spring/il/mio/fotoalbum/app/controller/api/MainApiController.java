package spring.il.mio.fotoalbum.app.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.il.mio.fotoalbum.app.pojo.Comment;
import spring.il.mio.fotoalbum.app.pojo.Photo;
import spring.il.mio.fotoalbum.app.service.CommentService;
import spring.il.mio.fotoalbum.app.service.PhotoService;

// API CONTROLLER

@RestController
@RequestMapping("/api/1")
@CrossOrigin("http://localhost:8081/") // cross origin restricted to localhost
public class MainApiController {

	@Autowired
	private PhotoService photoService;
	
	@Autowired 
	private CommentService commentService;
	
	@GetMapping("/photo/all")
	public List<Photo> getAllPhotos() {
		
		List<Photo> photos = photoService.findVisible();
		return photos;
	}
	
	@PostMapping("/photo/{id}/comment/post")
	public Comment postComment(
			@PathVariable("id") int id,
			@Valid @RequestBody Comment comment
			
	) {
		
		Optional<Photo> photoOpt = photoService.findById(id);
		
		if (photoOpt.isEmpty()) return null;
		
		Photo photo = photoOpt.get();
		comment.setPhoto(photo);
		
		return commentService.save(comment);
	}
}

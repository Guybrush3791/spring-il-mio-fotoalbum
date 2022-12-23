package spring.il.mio.fotoalbum.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.il.mio.fotoalbum.app.pojo.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

}

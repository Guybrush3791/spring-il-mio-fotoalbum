package spring.il.mio.fotoalbum.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.il.mio.fotoalbum.app.pojo.Tag;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer> {

}

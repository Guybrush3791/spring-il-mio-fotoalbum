package spring.il.mio.fotoalbum.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.il.mio.fotoalbum.app.pojo.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

package spring.il.mio.fotoalbum.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import spring.il.mio.fotoalbum.app.pojo.Photo;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer> {

	// this is a convenient way to explicit custo query
	// note language is JPQL and NOT SQL
	// is possible to use native SQL, setting an additional option
	// native = true, but this result in longest query
	// compare this join with SQL one
	@Query("""
		SELECT p
		FROM Photo p JOIN p.tags t
		WHERE
			p.title LIKE %:query%
			OR
			t.name LIKE %:query%
	""")
	public List<Photo> findByTitleOrTags(String query);
	
	// in this case is still possible get same result with more classic property expressions
	public List<Photo> findByTitleContainingOrTagsNameContaining(String title, String name);
	
	public List<Photo> findByVisibleTrue();
}

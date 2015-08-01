package test.root.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Filip on 2015-07-31.
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findOneById(long id);
    Book findOneByIsbn(String isbn);
}

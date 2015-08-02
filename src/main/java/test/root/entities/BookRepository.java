package test.root.entities;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Filip on 2015-07-31.
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findOneById(long id);
    Book findOneByIsbn(String isbn);
    @Query(value = "SELECT * FROM book WHERE book.id NOT IN (SELECT book_id FROM service)", nativeQuery = true)
    List<Book> findAvailableBooks();

    @Query(value = "SELECT * FROM book WHERE book.id IN (SELECT book_id FROM service WHERE user_id = ?1)", nativeQuery = true)
    List<Book> findUnavailableBooks(long userid);
}

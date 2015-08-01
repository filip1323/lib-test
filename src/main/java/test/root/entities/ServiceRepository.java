package test.root.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Filip on 2015-07-31.
 */
public interface ServiceRepository extends CrudRepository<Service, Long> {
   //TODO findby isbn of book with bookId
    Service findOneById(long id);
}

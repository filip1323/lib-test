package test.root.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Filip on 2015-07-31.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneById(long id);
    User findOneByLogin(String login);

}

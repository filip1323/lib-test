package test.root.entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import test.root.entities.User;
import test.root.entities.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Filip on 2015-08-01.
 */
@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public Collection<User> getAllUsers() {
        //TODO encapsulate
        Collection<User> list = new ArrayList<User>();
        for (User item : userRepository.findAll()) {
            list.add(item);
        }
        return list;
    }

    public User create(User.Role role, String firstname, String lastname, String login, String password) {
       return userRepository.save(new User(role,  firstname,  lastname,  login,  new BCryptPasswordEncoder().encode(password)));
    }

}

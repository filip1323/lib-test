package test.root.entities.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import test.root.entities.User;

import java.util.Collection;

/**
 * Created by Filip on 2015-08-01.
 */
public class SpringUserImpl extends org.springframework.security.core.userdetails.User {

    private final User user;

    public SpringUserImpl(User user){
        super(user.getLogin(),user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public User.Role getRole() {
        return user.getRole();
    }

}

package ru.mipt.restaurant.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.service.OwnerService;

@Configuration
public class OwnerDetailService implements UserDetailsService {

    @Autowired
    private OwnerService ownerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerService.get(username);

        if (owner != null) {
            return new User(owner.getEmail(), owner.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList(Roles.OWNER.name()));
        } else {
            throw new UsernameNotFoundException("could not find the user '"
                    + username + "'");
        }
    }
}

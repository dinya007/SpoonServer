package ru.mipt.restaurant.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.service.OwnerService;

import java.util.stream.Collectors;

@Service
public class OwnerDetailService implements UserDetailsService {

    @Autowired
    private OwnerService ownerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerService.get(username);

        if (owner != null) {
            String[] roles = owner.getRoles().stream().map(Role::name).collect(Collectors.toList()).toArray(new String[]{});
            return new User(owner.getLogin(), owner.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList(roles));
        } else {
            throw new UsernameNotFoundException("could not find the user '"
                    + username + "'");
        }
    }
}

package fr.sncf.d2d.up2dev.tortycolis.users.persistence;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import fr.sncf.d2d.up2dev.tortycolis.users.models.ApplicationUserDetails;
import fr.sncf.d2d.up2dev.tortycolis.users.models.User;

@Repository
public class UsersRepository implements UserDetailsService {

    private final List<User> users;

    public UsersRepository(UsersConfigurationProperties configuration){
        this.users = configuration.getUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = this.users.stream()
            .filter(u -> u.getEmail().equals(username))
            .findFirst()
            .orElseThrow(() -> new UsernameNotFoundException(String.format("%s is not a known username", username)));
        return new ApplicationUserDetails(user);
    }   
}
